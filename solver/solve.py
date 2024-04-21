# Project q10sk, a minimalistic model of computation.
# Copyright (C) 2017  Vratko Polak
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as published
# by the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.
#
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.


"""
This script is a solver for implicitly defined combinators,
useful for programming in q10sk project, a minimalistic model of computation.

This scipt parses an input file, solves the problem, and writes the solution to an output file.
The input contains an equation, both sides in unlambda format.
The left hand side must be in form of an unknown function applied to several arguments.
The symbols for the function and arguments mus be unique and different from Q, 1, 0, S, K.
The right hand side than contains arbitrary expression
with any number of copies of left hand side symbols and Q, 1, 0, S, K symbols.
The solution is also an equation, but with left hand side being only the unknown function
and the right hand side is an expression containing only Q, 1, 0, S, K symbols.
"""


class Node(object):
    """Base class for nodes in the computation tree."""

    def __call__(self, argument):
        """Apply this node to an argument."""
        if self == SK and argument != K:
            print(f"`SK with nonstandard argument: {argument}")
        if self == K and argument == I:
            print("`KI -> `SK")
            return SK
        if self == I:
            return argument
        return Inner(self, argument)


class Leaf(Node):
    """Represents a leaf node in the computation tree."""

    def __init__(self, letter):
        """ Initialize a leaf node with a given letter."""
        self.letter = letter

    def __str__(self):
        """Return a string representation of the leaf node."""
        return self.letter

    def __eq__(self, other):
        """Check if this leaf node is equal to another node."""
        return isinstance(other, Leaf) and self.letter == other.letter

    def __ne__(self, other):
        """Check if this leaf node is not equal to another node."""
        return not self == other

    def contains(self, leaf):
        """Check if this leaf node contains another leaf.

        For leaves this is the same as equality,
        but is it useful to have same method as inner nodes.
        """
        return leaf.letter == self.letter

    def extracted(self, leaf):
        """Extract the specified leaf from this leaf node."""
        if self.contains(leaf):
            return I
        else:
            return K(self)

    def lhs_x(self):
        """If left hand side parsing arrives here, this is the leaf for the unknown function."""
        return self


K = Leaf('K')
S = Leaf('S')


class Inner(Node):
    """Represents an inner node in the computation tree."""

    def __init__(self, function, argument):
        """Initialize an inner node with a function and an argument."""
        self.function = function
        self.argument = argument
        self.searched = ''
        self.found = True

    def __str__(self):
        """Return a string representation of the inner node."""
        return '`' + str(self.function) + str(self.argument)

    def __eq__(self, other):
        """Check if this inner node is equal to another node."""
        return isinstance(other, Inner) and self.function == other.function and self.argument == other.argument

    def __ne__(self, other):
        """Check if this inner node is not equal to another node."""
        return not self == other

    def lhs_x(self):
        """Return the unknown function leaf, None if left hand side is not valid."""
        if isinstance(self.argument, Leaf):
            x = self.function.lhs_x()
            if not x:
                return None
            if self.argument.letter == x.letter:
                return None
            if self.argument.letter in "Q10SK" or x.letter in "Q10SK":
                return None
            return x
        return None

    def contains(self, leaf):
        """Check if this inner node contains a given leaf."""
        if self.searched == leaf.letter:
            return self.found
        self.found = self.argument.contains(leaf) or self.function.contains(leaf)
        self.searched = leaf.letter
        return self.found

    def extracted(self, leaf):
        """Extract a leaf from this inner node."""
        if not self.contains(leaf):
            return K(self)
        if not self.function.contains(leaf):
            if isinstance(self.argument, Leaf):  # self == `Fl already
                return self.function
        return S(self.function.extracted(leaf))(self.argument.extracted(leaf))


# Avoid __call__ which need these two defined.
SK = Inner(S, K)
I = Inner(SK, K)
SII = S(I)(I)


def valid_char(char):
    """Check if a given character is valid."""
    return char.lower() in "`01abcdefghijklmnopqrtsuvwxyz"


def parse_hand(orig_text):
    """Parse one hand side from a given text.

    This is a common block for parsing both left and right hand side.
    """
    text = str(orig_text)
    stack = []
    while len(text) > 0:
        char = text[-1]
        text = text[:-1]
        if char == '=':
            break
        if not valid_char(char):
            continue
        if char == '`':
            combined = stack[-1](stack[-2])
            del stack[-1]
            stack[-1] = combined
        elif char == 'K':
            stack.append(K)
        elif char == 'S':
            stack.append(S)
        else:
            stack.append(Leaf(char))
    stack_size = len(stack)
    if stack_size != 1:
        raise RuntimeError(f"stack size {stack_size}, orig {orig_text}")
    return stack[0], text


def parse_string(orig_text):
    """Parse a string into left-hand side and right-hand side nodes."""
    text = str(orig_text)
    rhs, text = parse_hand(text)
    lhs, text = parse_hand(text)
    return lhs, rhs


def solve(lhs, rhs, x):
    """Solve the problem given the left-hand side and right-hand side nodes.

    Arguments are extracted and eliminated one by one.
    If the resulting right hand side still refers to the unknown function,
    the unknown function is extracted, and the remaining combinator F
    is used to contruct an explicit solution.
    """
    while not isinstance(lhs, Leaf):
        arg = lhs.argument
        lhs = lhs.extracted(arg)
        rhs = rhs.extracted(arg)
    if not rhs.contains(x):
        return rhs
    # X = `FX
    # X = xx
    # xc = `F`cc = `F```SIIc = ```KFc```SIIc = ```S`KF``SIIc
    # x = ``S`KF``SII
    # X = xx = ```SIIx = ```SII``S`KF``SII
    return SII(S(K(rhs.extracted(x)))(SII))


def main():
    """Main function that reads the input file, solves the problem, and writes the solution to the output file."""
    with open("solve.in") as fi:
        text = fi.read()
    lhs, rhs = parse_string(text)
    x = lhs.lhs_x()
    if not x:
        raise RuntimeError(f"lhs not canonic: {lhs}")
    with open("solve.out", "w") as fo:
        fo.write(x.letter + " = ")
        fo.write(str(solve(lhs, rhs, x)))
        fo.write('\n')


if __name__ == "__main__":
    main()
