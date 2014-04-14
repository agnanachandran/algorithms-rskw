package ca.pluszero.graphs;

import java.util.*;

public class SymbolGraph {
    private Map<String, Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String sp) {
        st = new HashMap<String, Integer>();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[] a = in.nextLine().split(sp);
            for (int i = 0; i < a.length; i++) {
                if (!st.containsKey(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }

        keys = new String[st.size()];
        for (String name : st.keySet()) {
            keys[st.get(name)] = name;
        }
        G = new Graph(st.size());
        in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[] a = in.nextLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++)
                G.addEdge(v, st.get(a[i]));
        }
    }

    public boolean contains(String s) { 
        return st.containsKey(s);
    }

    public int index(String s) { 
        return st.get(s); 
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            SymbolGraph sg = new SymbolGraph(args[0]);
            
        }
    }

}
