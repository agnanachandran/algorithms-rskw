import java.util.*;
import java.io.*;

public class Graph {
    private final int V; // # of vertices
    private int E; // # of edges
    private Set<Integer>[] adj; // adjacency lists

    public Graph(int V) {
        this.V = V; this.E = 0;
        adj = (Set<Integer>[]) new Set[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new TreeSet<Integer>();
        }
    }

    public Graph(Scanner in) {
        this(in.nextInt()); // Read V and construct the graph.
        int E = in.nextInt();
        for (int i = 0; i < E; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(v, w);
        }
    }

    public int V() { 
        return V;
    }

    public int E() { 
        return E;
    }

    public void addEdge(int v, int w) {
        // Note, if there is a self loop, the same index will be added twice
        adj[v].add(w);
        if (v != w) {
            adj[w].add(v);
        }
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < V; i++) {
            output += "Vertex: " + i + " is adjacent to: " + adj(i);
            output += "\n";
        }
        return output;
    }

    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 6);
        g.addEdge(0, 5);
        g.addEdge(6, 4);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(7, 8);
        System.out.println(g);
    }

    public static class Search {

        private boolean[] marked;
        private int count;

        public Search(Graph g, int src) {
            marked = new boolean[g.V()];
            dfs(g, src);
        }

        public void dfs(Graph g, int src) {
            marked[src] = true;
            count++;
            for (int vertex : g.adj(src)) {
                if (!marked[vertex]) {
                    dfs(g, vertex);
                }
            }
        }

        //public static boolean isGraphConnected(Graph g) {
            //Search search = new Search(g, 0);
            //return search.count() == g.V();
        //}

        public boolean marked(int w) {
            return marked[w];
        }

        public int count() {
            return count;
        }
        
    }
}
