# TODO: Add formatting.

The simplest implementation would use binary trees
while loking up the leftmost leaf to determine the rule to apply.

This API is not using this approach, as the lookup would be relatively slow.

Instead, inner nodes near leafs are marked (using java interfaces as merkers)
which alows for easy rule detection at the expense of more boilerplate code.

Tranditional distinction betwee API an implementation
is concerned with external users, which usually means
calls from outside of the implementation package tree.

Q10sk reference API goes farther than that.
Even internal calls (except calls to constructors) have to be defined in the API.
This means implementation never needs
to declare variable with type given by implementation class
(as opposed to type given by API interface).

Implemented classes should be units, every dependency should be explicitly injected.
As contructors are special, this boild down to a simple rule:
never call a constructor directly from implementation class.
That means that factory objects need to be injected, to be used instead of contructors.
So, factory objectcs are allowed to call constructors of the objects they are creating.
If the factory object itself needs a constructor, factoryfactory object is needed,
otherwise a singleton is the recommended solution.

If dependencies contain a cycle, even more convoluted setup is needed,
but that should not be the case for Q10skHlwnpo classes.