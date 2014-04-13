package ca.pluszero.graphs;

import java.util.*;

public class ConnectedComponents {

    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectedComponents(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++) { // account for all vertices; only looking at one vertex will only find vertices connected to that vertex
            if (!marked[s]) { // Every time a vertex has not been marked (i.e., everytime we hit this), that implies it has not been found through a previous DFS, i.e., it is part of some new component
                dfs(G, s);
                count++;
            }
            
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    // # of connected components
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph g = Graph.createDefaultGraph();
        System.out.println(g);
        ConnectedComponents cc = new ConnectedComponents(g);
        for (int i = 0; i < g.V(); i++) {
            System.out.println("Vertex: " + i + " is connected to:");
            for (int j = 0; j < g.V(); j++) {
                System.out.println(j + ": " + cc.connected(i, j));
            }
            System.out.println();
        }
    }

}

