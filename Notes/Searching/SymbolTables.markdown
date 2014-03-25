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

Doing this preserves the order since there are no keys of magnitude in between the root and the successor. (pp. 410)

Replacing `x` by its successor can be done in 4 steps:

1) Save a link to the node to be deleted in `t`
2) Set `x` to point to its successor `min(t.right)`
3) Set the right link of `x` (which is supposed to point to the BST containing all the keys larger than `x.key`) to `deleteMin(t.right)`, the link to the BST containing all the keys that are larger than `x.key` after the deletion.
4) Set the left link of `x` (which was null) to `t.left`

Hash Tables
-----------

- Also known as Hash Maps
General idea:

- Use an array of Key, Value pairs. Locating where the key is requires its array index. Compute the array index using a hash function and modular arithmetic
- Each array element is called a bucket
- If multiple keys hash to the same array index, it is a hash collision; which can be dealt with through separate chaining, or linear probing, for example.

Hash function:

- Transform a given key into an index for the array. 
- Typically: use modular hashing; choose a prime numbered array size `M`, and for a positive integer `k`, compute `k % m`.

- So, `hash(key) == (key.hashCode() & 0x7fffffff) % M`
- We mask the sign bit off with 0x7fffffff to make sure the number is positive

Reasons M should be prime:

1) Lower probability of hash collisions
    - Suppose you're hashing area codes; 905, 519, 416, etc., typically the middle digit is 0 or 1 for historical reasons, so if the array size is 10^2 == 100, then only the last two digits matter, and you get a greater concentration of entries from array indices 0 through 19.
    - Similarly, for IP addresses, which are binary numbers, M should not be of the form 2^k (e.g., if M=64, then 128, 256, 512, would all map to index 0). 

2) In the hash resolution method of linear probing, when an entry is already in a bucket, the next bucket tried is the original bucket's index, OBI,  PLUS some step size, SS. Then the next bucket is OBI + 2*SS, then it's OBI + 2*SS (all of these are mod M). In order to cover all the buckets eventually, SS and M should be co-prime (aka relatively prime).

Ideally, we want all the bits of the input to play an equal role in hashCode().

Hashing different types:
------------------------

Floats: Use modular hashing on the binary rep. of the key
Strings: 
Recall that charAt() returns a char value in Java, which is a 16-bit nonnegative integer. If R is greater than any character value, this computation would be equivalent to treating the String as an N-digit base-R integer, computing the remainder that results when dividing that number by M. R should be sufficiently small so overflow doesn't occur.

`int hash = 0;
for (int i = 0; i < s.length(); i++) {
    hash = (R * hash + s.charAt(i)) % M; // R is some small positive int
}`

Compound types:

    private final String who;
    private final Date when;
    private final double amount;

    public int hashCode()
    {
        int hash = 17;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash
            + ((Double) amount).hashCode();
        return hash;
    }

Caching:
--------

If computing hashCode() is somewhat expensive; an instance variable can be assigned the value of hashCode() the first time its computed; and it can be returned on subsequent calls of hashCode() (assuming the object's properties haven't changed. If so, a boolean flag can be set to say that the cached hashCode is outdated.).

Summary:

- Consistent: equal keys must produce the same hash value
- Should be efficient to compute
- Should uniformly distribute the keys.

Separate chaining:
------------------

When 2 or more keys to be inserted hash to the same index; one way to deal with it is "Separate Chaining".

- Items that collide are chained together in separate linked lists.
- Thus, we choose M to be sufficiently large that the lists are sufficiently short to enable efficient search through the 2-step process of hashing to find the list that could contain the key, and then sequentially searching the linked list for the key (check through `.equals()`).
- With M lists (i.e., M 'buckets'), and N keys, the average length of the lists is ALWAYS N/M, so it's decently fast to search the map.

Linear Probing:
---------------

Another method is called open-addressing; it relies on empty entries in the table to help with collision resolution.

- When there is a collision, check the next entry in the table (wrap around if necessary)
