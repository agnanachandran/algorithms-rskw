public class SeparateChainingHashST<Key, Value>
{
    private int N; // # of key-value pairs
    private int M; // # of buckets (hash table size)
    private SequentialSearchST<Key, Value>[] st; // Basicaly a linked list of key value pairs which can be sequentially searched

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SeparateChainingHashST<Key, Value>[]) new SequentialSearchST[M];
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
    }

    public void delete(Key key) {
        st[hash(key)].delete();
    }
}
