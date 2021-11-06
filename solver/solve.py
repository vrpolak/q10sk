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


# TODO: Add docstrings.

class Node(object):

    def __call__(self, argument):
        if self == SK and argument != K:
            print(f"`SK with nonstandard argument: {argument}")
        if self == K and argument == I:
            print("`KI -> `SK")
            return SK
        if self == I:
            return argument
        return Inner(self, argument)


class Leaf(Node):

    def __init__(self, letter):
        self.letter = letter

    def __str__(self):
        return self.letter

    def __eq__(self, other):
        return isinstance(other, Leaf) and self.letter == other.letter

    def __ne__(self, other):
        return not self == other

    def contains(self, leaf):
        return leaf.letter == self.letter

    def extracted(self, leaf):
        if self.contains(leaf):
            return I
        else:
            return K(self)

    def lhs_x(self):
        return self


K = Leaf('K')
S = Leaf('S')


class Inner(Node):

    def __init__(self, function, argument):
        self.function = function
        self.argument = argument
        self.searched = ''
        self.found = True

    def __str__(self):
        return '`' + str(self.function) + str(self.argument)

    def __eq__(self, other):
        return isinstance(other, Inner) and self.function == other.function and self.argument == other.argument

    def __ne__(self, other):
        return not self == other

    def lhs_x(self):
        if isinstance(self.argument, Leaf):
            x = self.function.lhs_x()
            if not x:
                return None
            if self.argument.letter != x.letter:
                return x
        return None

    def contains(self, leaf):
        if self.searched == leaf.letter:
            return self.found
        self.searched = leaf.letter
        self.found = self.argument.contains(leaf) or self.function.contains(leaf)
        return self.found

    def extracted(self, leaf):
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
    return char.lower() in "`01abcdefghijklmnopqrtsuvwxyz"


def parse_hand(orig_text):
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
    text = str(orig_text)
    rhs, text = parse_hand(text)
    lhs, text = parse_hand(text)
    return lhs, rhs


def solve(lhs, rhs):
    x = lhs.lhs_x()
    if not x:
        raise RuntimeError(f"lhs not canonic: {lhs}")
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
    with open("solve.in") as fi:
        text = fi.read()
    lhs, rhs = parse_string(text)
    x = lhs.lhs_x()
    if not x:
        raise RuntimeError(f"left hand side not in canonic form: {lhs}")
    with open("solve.out", "w") as fo:
        fo.write(lhs.lhs_x().letter + " = ")
        fo.write(str(solve(lhs, rhs)))
        fo.write('\n')

if __name__ == "__main__":
    main()
