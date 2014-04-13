package ca.pluszero.graphs;

import java.util.*;
import java.io.*;

public class Graph {
    private final int V; // # of vertices
    private int E; // # of edges
    private Set<Integer>[] adj; // adjacency lists; an array of sets; adj[i] contains all the vertices adjacent to vertex i

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


    public static Graph createDefaultGraph() {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 6);
        g.addEdge(0, 5);
        g.addEdge(6, 4);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(6, 7);
        return g;
    }

    public static void main(String[] args) {
        Graph g = createDefaultGraph();
        System.out.println(g);
        System.out.println();
        Search s = new Search(g, 0);
    }

}
