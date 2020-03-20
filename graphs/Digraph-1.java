/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-04
Last updated: 2019-10-08

Dependencies: Bag.java
Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley

The Digraph class represents a directed graph which is a set of
vertices and a collection of directed edges.
This implementation uses an adjacency-lists representation, which
is a vertex-indexed array of Bag objects.
*/

public class Digraph {
    private final int V;           // number of vertices in this digraph
    private int E;                 // number of edges in this digraph
    private Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v

    //create a V-vertex digraph with V vertices and 0 edge
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; //adj, an array of bag objects
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    //Returns the number of vertices
    public int V() {
        return V;
    }

    //Returns the number of edges
    public int E() {
        return E;
    }

    //Add edge v -> w to the digraph
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    //Returns the vertices connected to v by edges pointing from v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    //Returns a string representation of the graph
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + '\n');
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append('\n');
        }
        return s.toString();
    }

    //Unit tests the Digraph data type
    /*public static void main(String[] args) {
        String in = args[0];
        SymbolDigraph sg = new SymbolDigraph(in, args[1]);
        Digraph G = sg.digraph();
        StdOut.println(G);
    }*/

}
