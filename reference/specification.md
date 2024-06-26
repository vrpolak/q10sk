# TODO: Add formatting.

This is an informal specification of q10sk.

Q10sk is primarily a model of computation;
but the intention is to create a family of programming languages
with semantics directly tied to that model of computation.
Of course, when a program written in such a language is interpreted,
the interpreter is free to choose its model of computation
(presumably to achive better preformance)
as long as the input-output behavior remains the same.

For input and output, the computation itself (called "agent" or "program")
can read or write one bit at a time.
The other side (receiving writes and providing reads) is called
"system" or "environment".

There is no way for the system to forcefully extract outputs
or forcefully insert inputs into uncooperating program.
As one bit is not enough for reasonable stateless protocols,
the system itself can be expected to contain a state,
communicating with the program using a stateful protocol.
This way programs can indirectly communicate
with other computational entities the system is interacting with,
for example filesystem, shared memory, network, other running q10sk programs.
System may also let program know about its source code
or other metadata this way.

This document does not assume any specific system behavior,
aside of the fact that is should always produce or consume the bit.
Of couse, in practice the system can stall or fail,
but the program cannot detect that.

Q10sk is an extension of SK combinator calculus.
https://en.wikipedia.org/wiki/SKI_combinator_calculus
As noted, SKI calculus does not need I combinator,
so q10sk does not have I as a primitive, but it has K and S primitives.

This document uses the same rules as the SKI combinator wikipage:
"Although the most formal representation of the objects in this system requires binary trees,
they are usually represented, for typesetability, as parenthesized expressions,
either with all the subtrees parenthesized, or only the right-side children subtrees parenthesized.
So, the tree whose left subtree is the tree KS and whose right subtree is the tree SK
is usually typed as ((KS)(SK)), or more simply as KS(SK),
instead of being fully drawn as a tree (as formality and readability would require)."

A programming language narrowed down to SK calculus
can be purely functional, we assume q10sk languages narrowed down are purely functional indeed.
In purely functional languages, order of evaluation does not mater,
but most implementations are assumed to be lazy,
which means only the function (not the argument) is brought to normal form
before function application is executed.
This applied recursively from the root node implies that
lazy evaluation is only based on achieving weakly normalized form.

A common exception to lazyness rule is to apply K rule as soon as possible,
as that is guaranteed to save space.

Aside of K, S and function application, q10sk contains 0, 1 and Q,
which are combinators (supercombinators in fact) with a special meaning.

Reduction rules:

<code>0xy = 0(xy)</code>

<code>1xy = 1(xy)</code>

<code>Qxyz = Q(xz)(yz)</code>

Similarly to K, these rules can be applied as soon as possible,
without worrying about execution getting stuck.
The result of Q rule is not simpler as a tree,
but it moves Q closer to the root.

Eager application of every rule except the S rule is called
half lazy evaluation in this document.
It will be used in the reference interpreter,
but of course other interpreters may chose other evaluations,
as long as they do not get stuck on programs which are halting in lazy evaluation.

The special meaning of 0, 1 and Q applies to the whole tree
(as opposed to subree) causing the following side-effects:
If the tree has form "0x", bit zero is written to output and execution continues with tree "x".
If the tree has form "1x", bit one is written to output and execution continues with tree "x".
If the tree has form "Qxy", a bit is read from input,
if the bit is zero, execution continues with tree "x",
if the bit is one, execution continues with tree "y".

Reduction rules imply the IO can happen as early as 0, 1 or Q become the leftmost leaf
(the top-most function), the only requirement is that 0, 1 and Q
never apply their side-effects when present in an argument (when not the top-most function).

When the tree reaches its weakly normal form, execution is halted.
The system cannot distingush the halted state from a running state which happeded
to require no IO for a period of time (unless the system can get such info from the interpreter).

That is it. Here are the reduction rules in unlambda syntax:

<code>``Kxy = x</code>

<code>```Sxyz = ``xz`yz</code>

<code>``0xy = `0`xy</code>

<code>``1xy = `1`xy</code>

<code>```Qxyz = ``Q`xz`yz</code>

An interesting alternative to half lazy evaluation is half eager evaluation.
There, z argument is weakly normalized before applying S rule.
This gets stuck if z argument does not have a weakly normalized form,
but reasonable programs should be able to avoid such z arguments
in S expressions never executing the z argument.
So half eager evaluation would run faster on the reasonable programs,
but it would not be suited for randomly generated programs,
or programs iterating over all source codes (such as resource limited Solomonoff induction).
