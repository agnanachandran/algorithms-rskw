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


    //We can't just set the key's table position to null, 
    //since searches for a key may prematurely stop 
    //(since they stop once seeing a null key),
    //and keys arent' necessarily at hash(key)
    //(due to how linear probing sequentially tries elements).
    //Thus, we need to reinsert into the table all of the keys
    //in the cluster to the right of the deleted key 
    //(wrapping around as necessary; just stop once you hit a null key)
    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        // Now i is the index that matches the key
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToReput = keys[i];
            Value valToReput = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToReput, valToReput);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M/8) {
            resize(M/2);
        }
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
        st.put("Yo", 9);
        System.out.println("Value of 'Yo': " + st.get("Yo"));
        System.out.println("Size: " + st.size());
        st.delete("Yo");
        System.out.println(st.get("Yo"));
    }
}
