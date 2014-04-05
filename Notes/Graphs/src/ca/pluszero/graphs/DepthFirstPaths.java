package ca.pluszero.graphs;

import java.util.*;

public class DepthFirstPaths {

    private boolean[] marked; // Has dfs() been called for this vertex?
    private int[] edgeTo;     // last vertex on known path to this vertex
    private final int s;      // source

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null; // No path to v
        Stack<Integer> path = new Stack<Integer>();
        int currentVertex = v;
        while (s != currentVertex) {
            System.out.println(edgeTo[currentVertex]);
            path.push(currentVertex);
            currentVertex = edgeTo[currentVertex];
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph g = Graph.createDefaultGraph();
        System.out.println(g);
        DepthFirstPaths dfp = new DepthFirstPaths(g, 0);
        System.out.println(dfp.pathTo(8));
    }

}
