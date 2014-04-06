package ca.pluszero.graphs;

import java.util.*;

public class BreadthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;     // last vertex on known path to this vertex
    private final int s;      // source

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph g, int v) {

        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(v);
        marked[v] = true;
        while (!q.isEmpty()) {
            int curr = q.remove();
            for (int adjVertex : g.adj(curr)) {
                if (!marked[adjVertex]) {
                    q.add(adjVertex);
                    marked[adjVertex] = true;
                    edgeTo[adjVertex] = curr;
                }
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
        BreadthFirstPaths bfp = new BreadthFirstPaths(g, 0);
        System.out.println(bfp.pathTo(8));
    }

}

