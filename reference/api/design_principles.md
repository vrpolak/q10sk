# TODO: Add formatting.

The simplest implementation would use binary trees
while looking up the leftmost leaf to determine the rule to apply.

This API is not using that approach, as the lookup would be relatively slow.

Instead, inner nodes near leafs are typed (using java interfaces as markers)
which allows for easy rule detection at the expense of more boilerplate code.

The tranditional distinction between API an implementation
is concerned with external users, which usually means
calls from outside of the implementation package tree.

Q10sk reference API goes beyond that.
Even internal calls (except calls to constructors) have to be defined in the API.
This means the implementation never needs
to declare variable with type given by an implementation class
(as opposed to type given by an API interface).

Implemented classes should be units, every dependency should be explicitly injected.
As contructors are special, this boils down to a simple rule:
never call a constructor directly from an implementation class.
That means that factory objects need to be injected, to be used instead of contructors.
So, factory objectcs are allowed to call constructors of the objects they are creating.
If the factory object itself needs a constructor, factoryfactory object is needed,
otherwise a singleton is the recommended solution.

If dependencies contain a cycle, even more convoluted setup is needed,
but that should not be the case for Q10skHlwnpo classes.

But even without cycles, the dependency graph is not trivial,
as apply operation implies nodes need to contain result factories.
For example S node needs to know how to construct Sx node, so it needs a Sx node factory.
Similarly, the constructed Sx node needs a Sxy node factory, Sxy needs Sxyz factory,
and Sxyz node needs inner node factory.

In general, callers have freedom to construct such future factories
anyway they please, but most users will wish to stick with a small set of factories.
For that reason, API defines also a "wiring" interface,
sole purpose of which is to create and store the five leaf node objects.
That way callers can use just the leafs and apply operation,
while all the factory business remains hidden in the wiring implementation.
