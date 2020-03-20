/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-04
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley

Compilation:  javac P2A2DirectedCycle.java
Execution:    java P2A2DirectedCycle input.txt
Dependencies: Digraph.java Stack.java

This program determines if there is a directed cycle in a digraph. If so, fins such a cycle and find it.
This implementation uses depth-first search.

test.txt
A B
A C
B D
C D
D A
E C
F G
$ java P2A2DirectedCycle test.txt
Directed cycle: D A C D

$ java P2A2DirectedCycle database.txt
No directed cycle

$ java P2A2DirectedCycle newdatabase.txt
Directed cycle: AL FL NY LA AL
*/

public class P2A2DirectedCycle {
    private boolean[] marked;        // marked[v]: has vertex v been visited
    private int[] edgeTo;            // edgeTo[v]: previous vertex on path to v
    private Stack<Integer> cycle;    // vertices that form a directed cycle
    private boolean[] onStack;       // onStack[v]: mark the vertex on the stack

    //Determines whether the digraph G has a directed cycle
    public P2A2DirectedCycle(Digraph G) {
        marked  = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null)  //keep searching until a cycle is found/all vertices are visited
                dfs(G, v);
    }

    private void dfs(Digraph G, int v) { //a directed path from the source vertex to v
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null)      //return if a directed cycle found
                return;

            else if (!marked[w]) {  //found new vertex that has not been visited,recursively call dfs
                edgeTo[w] = v;
                dfs(G, w);
            }

            else if (onStack[w]) {   //backtrace the directed cycle
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.pushEl(x);
                }
                cycle.pushEl(w);
                cycle.pushEl(v);
            }
        }
        onStack[v] = false;
    }

    //Check if the digraph have a directed cycle
    public boolean hasCycle() {
        return cycle != null;
    }

    //Returns a directed cycle if the digraph has a directed cycle, and null otherwise
    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        String filename = args[0];
        SymbolDigraph sg = new SymbolDigraph(filename, " ");    //creates a SymbolDigraph with a input file. Delimiter is a blank space
        Digraph G = sg.digraph();

        P2A2DirectedCycle find = new P2A2DirectedCycle(G);
        if (find.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : find.cycle()) {
                System.out.print(sg.nameOf(v) + " ");
            }
            System.out.println();
        }
        else {
            System.out.println("No directed cycle");
        }
        System.out.println();
    }

}
