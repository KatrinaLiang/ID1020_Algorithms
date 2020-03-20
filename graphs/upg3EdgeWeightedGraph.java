/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-03
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley
Dependencies: Bag.java Edge.java

This class implements an edge-weighted undirected graph, implemented using adjacency lists.
The EdgeWeightedGraph class represents an edge-weighted
graph of vertices named 0 through V – 1, where each
undirected edge is of type Edge and has a real-valued weight.

 */

public class upg3EdgeWeightedGraph {
    private final int V;            //number of vertices
    private int E;                  //number of edges
    private Bag<Edge>[] adj;        //adjacency lists, a vertex-indexed array of Bag objects

    //Initializes an empty edge-weighted graph with V vertices and 0 edges
    public upg3EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for(int v=0; v<V; v++){
            adj[v] = new Bag<Edge>();  //array with Bag objects on every index
        }
    }

    //number of vertices
    public int V(){ return V; }

    //number of edges
    public int E(){ return E; }

    //Adds the undirected edge e to this edge-weighted graph
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    //return the edges incident on vertex
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    //Iterates the edge-weighted graph and returns all edges
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();       //puts all the edges in a Bag
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                else if (e.other(v) == v) {     // add only one copy of each self loop
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    //return a string representation of the edge weighted graph
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + '\n');
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append('\n');
        }
        return s.toString();
    }
}

