/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-01
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley

The Edge class represents a weighted edge in an EdgeWeightedGraph. Each edge consists of two integers
(associate the two vertices) and a real-value weight.
*/

public class Edge implements Comparable<Edge> {
    private final int v;     //one of the vertex in a pair
    private final int w;     //the other vertex
    private final double weight;     //the weight of the edge

    //initialize an edge between the given vertices v and w
    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    //returns the weight of this edge
    public double weight(){
        return weight;
    }

    //returns either endpoint of this edge
    public int either() {
        return v;
    }

    //Returns the endpoint of this edge that is different from the given vertex
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    //Compares one edge with the other edge (that) by weight
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    //return a string representation of an edge
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    //Unit testing
    /*public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        System.out.println(e);
    }*/
}
