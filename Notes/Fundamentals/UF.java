import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class UF {
    private int[] id;
    private int count;
    private int[] sz; // size of component for roots (site indexed) (for the Weighted Quick Union algo.)

    public UF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // Number of components
    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // These implement weighted quick-union with path compression (flatten the trees by making each node point to the root (after finding out what the root actually is of course, by traversing the links to find the root. Weighted quick union alone gives an order of growth of lg N for each of the union and find methods
    // Weighted quick union with path compression is very nearly, but not quite 1 (it's amortized constant time).
    public int find(int p) {
        // Follow links to find a root.
        List<Integer> visited = new ArrayList<Integer>();
        while (p != id[p]) {
            visited.add(p);
            p = id[p];
        }
        // root is at id[p]
        for (int index : visited) {
            id[index] = p; 
        }
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // Make smaller root point to larger one.
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    // Quick find algo:
    // Two sites are connected if they have the same component; i.e., id[p] == id[q]. Thus to connect two components, we make them have the same id. This is done by changing all id[i] such that id[i] == id[p] to id[q]. After this, id[p] == id[q], and everything that was connected to p is now also connected to q, and vice versa. pp 222
    public int quickFind(int p) {
        return id[p];
    }

    public void unionWithQuickFind(int p, int q) {
        int pID = quickFind(p);
        int qID = quickFind(q);

        if (pID == qID) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
    }

    // Another approach: each element in the id[] array is the name of another site in the same component (possibily itself). For find, we following the links until a site links to itself. (pp 224)
    public int findWithQuickUnion(int p) {
        while (p != id[p]) { // While the link is not to itself, keep traversing links
            p = id[p];
        }
        return p;
    }

    public void quickUnion(int p, int q) {
        int pRoot = findWithQuickUnion(p);
        int qRoot = findWithQuickUnion(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        UF uf = new UF(N);
        while (s.hasNext()) {
            int p = s.nextInt();
            int q = s.nextInt();
            if (uf.connected(p, q)) continue; // Ignore if connected
            // Union elements and print them
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
}
