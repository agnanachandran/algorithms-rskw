Bgs, Queues, and Stacks (1.3)
=============================

Bags
----

- a collection where removing items is unsupported
- iterate through a collection of items
- unspecified iteration order

Queues
------

- FIFO (First in, first out)

Stacks
------

- LIFO (Last in, first out)
- Can be used to evaluate fully parenthesized mathematical expressions with the following algo:
Dijkstra’s two-stack arithmetic expression-evaluation algorithm:
■ Push operands onto the operand stack.
■ Push operators onto the operator stack.
■ Ignore left parentheses.
■ On encountering a right parenthesis, pop an operator, pop the requisite number
of operands, and push onto the operand stack the result of applying that opera-
tor to those operands.

Dynamic Connectivity (1.5)
==========================

Consider the following problem:

- The input is a sequence of pairs of integers, where each integer represents an object of some type
- Each pair, `p q` means `p is connected to q`.
- Assume 'is connected to' is an equivalence relation (thus, it is reflexive, symmetric, and transitive)
- Two objects are in the same equivalence class iff they are connected.
- Goal: when the program reads a pair `p q` from the input, it should write the pair to the output only if the pairs it has seen to that point do NOT imply that p is connected to q; otherwise, the pair should be ignored, and the next input should be read in.

We will refer to the objects as 'sites', the pairs as 'connections', and the equivalence classes as 'connected components'

Both sites and components will be identified by int values between 0 and N-1

Refer to UF.py for the Union find algorithm

