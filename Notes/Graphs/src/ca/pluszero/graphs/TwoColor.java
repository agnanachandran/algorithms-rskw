package ca.pluszero.graphs;

import java.util.*;

public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph g) {
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]) {
                dfs(g, s);
            }
        }
    }

    public void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[w] == color[v]) {
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }

    public static void main(String[] args) {
        TwoColor tc = new TwoColor(Graph.createDefaultGraph());
        System.out.println(tc.isBipartite());
    }

}
// Determines if a graph G is bipartite (is 2 colorable)
