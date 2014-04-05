package ca.pluszero.graphs;

public class Search {

    private boolean[] marked;
    private int count;

    public Search(Graph g, int src) {
        marked = new boolean[g.V()];
        dfs(g, src);
        System.out.println("is Connected: " + isConnected(g));
    }

    private void dfs(Graph g, int src) {
        System.out.println("Visiting: " + src);
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

    public boolean isConnected(Graph g) {
        System.out.println(count() + " " + g.V());
        return count() == g.V();
    }

}

