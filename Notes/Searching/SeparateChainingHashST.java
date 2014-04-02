public class SeparateChainingHashST<Key, Value> {

    private int N; // # of key-value pairs
    private int M; // # of buckets (hash table size)
    private SequentialSearchST<Key, Value>[] st; // Basicaly a linked list of key value pairs which can be sequentially searched

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST(); 
        }
    }

    private int hash(Key key) {
        return key.hashCode() & 0x7fffffff % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
        N++;
    }

    public void delete(Key key) {
        st[hash(key)].delete(key);
        N--;
    }

    public int size() {
        // TODO: implement this later?
        throw new UnsupportedOperationException();
    }
    
    public static void main(String[] args) {
        SeparateChainingHashST<Integer, String> st = new SeparateChainingHashST<Integer, String>();
        st.put(5, "LOL");
        st.put(3, "YO");
        st.delete(5);
        st.delete(3);
    }

    private class SequentialSearchST<Key, Value>
    {
        private Node first; // first node in linked list

        private class Node {
            Key key;
            Value val;
            Node next;
            public Node(Key key, Value val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        public Value get(Key key)
        {
            // Search for key, return associated value.
            for (Node x = first; x != null; x = x.next) {
                if (x.key.equals(key)) {
                    return x.val;
                }
            }
            return null;
        }

        public void put(Key key, Value val) {
            for (Node x = first; x != null; x = x.next) {
                if (x.key.equals(key)) { // key already exists; update it
                    x.val = val;
                    return;
                }
            }
            first = new Node(key, val, first); // insert new node at beginning
        }

        public void delete(Key key) {
            Node prev = null;
            Node current = null;
            for (Node x = first; x != null; x = x.next) {
                if (x.key.equals(key)) {
                    current = x;
                    break;
                }
                prev = x;
            }
            if (current == null) return;
            if (prev == null) { // first key was correct
                first = current.next;
            } else {
                prev.next = current.next;
            }
        }

        public Node retrieveMiddle() {
            if (first == null) {
                return null;
            }

            Node slow = first;
            Node fast = first.next;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
}
