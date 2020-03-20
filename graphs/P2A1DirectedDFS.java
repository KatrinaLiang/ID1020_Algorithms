/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-04
Last updated: 2019-10-08

Dependencies: Digraph.java Bag.java

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley

The program determines reachability from a given source vertex to another vertex in a digraph
using depth-first search.

test.txt
A B
A C
B D
C D
D A
E C
F G
$ java P2A1DirectedDFS test.txt "A" "D"
Is there a path between A and D? Yes

$ java P2A1DirectedDFS test.txt "A" "E"
Is there a path between A and E? No

$ java P2A1DirectedDFS database.txt "AL" "FL"
Is there a path between AL and FL? Yes

$ java P2A1DirectedDFS database.txt "AL" "NY"
Is there a path between AL and NY? No
*/

public class P2A1DirectedDFS {
    private boolean[] marked;  // marked[v] = true iff v is reachable from source(s)

    //Computes the vertices in digraph G that are reachable from the source vertex s
    public P2A1DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Digraph G, int v) {        //depth first search from vertex v
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    //Check if there is a directed path from the source vertex to vertex v
    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        String filename = args[0];
        String start = args[1];
        String end = args[2];

        SymbolDigraph sg = new SymbolDigraph(filename, " ");//creates a SymbolDigraph with a input file. Delimiter is a blank space
        Digraph G = sg.digraph();

        int s = sg.indexOf(start);
        int e = sg.indexOf(end);

        P2A1DirectedDFS dfs = new P2A1DirectedDFS(G, s); //find all vertices that are reachable from the source vertex

        StdOut.printf("Is there a path between %s and %s? ", sg.nameOf(s), sg.nameOf(e));
        if(dfs.marked(e)) {             //check if there is a path from start to end
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    }
}

