public class LinearProbingHashST<Key, Value> {
    private int N; // # of pairs
    private int M = 16; // size of table
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() % 0x7fffffff) % M;
    }

    public int size() {
        return N;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>();
        t = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M/2) {
            resize(2*M);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) { // step size of 1
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null; // Key not found
    }

    public boolean contains(Key key) {
        if (key == null) return false;
        if (get(key) == null) return false;
        return true;

    }

    public void delete(Key key) {
        if (!contains(key)) return;
        
    }
    // TODO: deletion
}
