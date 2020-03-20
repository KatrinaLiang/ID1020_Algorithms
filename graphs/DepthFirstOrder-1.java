/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-05
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley
Dependencies: Digraph.java Queue.java Stack.java  DirectedEdge.java

The DepthFirstOrder class represents a data type for determining depth-first search
ordering (reverse postorder) of the vertices in a digraph.
*/

public class DepthFirstOrder {
    private boolean[] marked;          //marked[v] = has v been marked in dfs?
    private Stack<Integer> reverse;     //reverse of finishing times of vertices

    //Determines a depth-first order for the digraph G
    public DepthFirstOrder(Digraph G) {
        reverse = new Stack<Integer>();
        marked    = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    //run DFS in digraph G from vertex v and compute reverse postorder by putting vertex on a stack
    //after the recursive calls
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        reverse.pushEl(v);
    }

    //Return the vertices in reverse postorder
    public Iterable<Integer> reversePost() {
        return reverse;
    }
}