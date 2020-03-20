/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-05
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley
Dependencies: Digraph.java DepthFirstOrder.java P2A2DirectedCycle.java SymbolDigraph.java

Run DFs and output reverse of finishing times of vertices, by putting the vertex on a stack
after the recursive calls (after the dfs call on the vertex is done).


test.txt
A B
A C
B D
C D
D A
E C
F G
$ java P2A3Topological test.txt " "
This digraph does not have a topological order

$ java P2A3Topological smalldatabase.txt "-"
This digraph has a topological order
TJ
TK
TL
TM
TI
TH
TG
TD
TC
TA
TB
TF
TE
 */

public class P2A3Topological {
    private Iterable<Integer> order;  // topological order

    //Determines whether the digraph G has a topological order and, if so, finds such a topological order
    public P2A3Topological(Digraph G) {
        P2A2DirectedCycle finder = new P2A2DirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    //Returns a topological order of the vertices if the digraph has a topological order,and null otherwise.
    public Iterable<Integer> order() {
        return order;
    }

    //check if the digraph have a topological order
    public boolean hasOrder() {
        return order != null;
    }

    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        P2A3Topological topological = new P2A3Topological(sg.digraph());
        if(topological.hasOrder()) {
            System.out.println("This digraph has a topological order");
            for (int v : topological.order()) {
                System.out.println(sg.nameOf(v));
            }
        }
        else
            System.out.println("This digraph does not have a topological order");
    }

}
