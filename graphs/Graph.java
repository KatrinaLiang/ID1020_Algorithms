/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-01
Last updated: 2019-10-08

Dependencies: Bag.java Stack.java
Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley

This Graph class implemented using an array of Bag. The class represents an undirected graph of vertices of
integers 0 to (V-1).
This implementation uses an adjacency-lists representation, which is a vertex-indexed array of Bag objects.

 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;    //number of vertices
    private int E;          //number of edges
    private Bag<Integer>[] adj;     //adjacency lists, the graph representation

    public Graph(int V){  //constructor
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for(int v =0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){  //add two edges into the graph since the graph is undirected
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    //Unit testing
    /*public static void main(String[] args) {
        String in = args[0];
        SymbolGraph sg = new SymbolGraph(in, args[1]);
        Graph G = sg.graph();
        StdOut.println(G);
    }*/
}
