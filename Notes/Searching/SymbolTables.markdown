Symbol Tables (i.e. Maps)
=========================

General idea:
- A client can insert key, value pairs into a symbol table, and later access the value through the key.

Some tidbits first:
Deletion: typically implemented in one of 2 ways:
- Lazy deletion: associate keys in the table with null, then perhaps remove all such keys at some later time
- Eager deletion: remove the key from the table immediately.

calling `put (key, null)` is an easy (lazy) implementation of delete


Binary Search Trees
-------------------

Deletion:

Deleting a node with zero children:
    - set node to null
Deleting a node with 1 child:
    - Replace this node with its child
Deleting a node with 2 children:

Delete a node `x` by replacing it with its successor. Because x has a right child, its successor is the node with the smallest key in the right subtree (The successor is the minimum key in the right subtree, but since it's in the right subtree, it's still larger than the root key).

Doing this preserves the order since there are no keys of of magnitude in between the root and the successor.

Replacing `x` by its successor can be done in 4 steps:

1) Save a link to the node to be deleted in `t`
2) 