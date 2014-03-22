public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key; // key
        private Value val; // associated value
        private Node left, right; // links to subtrees
        private int N; // # nodes in subtree rooted here

        public Node(Key key, Value val, int N) {
            this.key = key; this.val = val; this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public Value get(Key key) {
        return get(root, key);   
    }

    // Return value associated with key in the subtree rooted at x;
    // return null if key not present in subtree rooted at x.
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        }
        return x.val;
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        // Change key's value to val if key in subtree rooted at x.
        // Otherwise add new node to subtree associating key with val.
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val); // x.left is assigned here; if this call to put doesn't immediately result in returning the new node, x.left's reference will not change; if the call does immediately result in return a new node; i.e., x.left is null, then x.left will be useful, it'll be assigned the new node. Similar logic is used for the x.right branch
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1; // Calls will percolate up after the puts; the size of the child nodes will be updated recursively through this statement, and then the current node's size needs to be updated.
        return x;
    }

}
