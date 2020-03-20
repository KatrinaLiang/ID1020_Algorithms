/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-01
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley
Dependencies: SymbolGraph.java Graph.java Stack.java

This program implements depth-first search to find a path between two vertices in an undirected graph.

Execution examples:
test.txt
A B
A C
B D
C D
D A
E C
F G
$ java upg1DepthFirstPaths test.txt "A" "E"
A to E: A-D-C-E
$ java upg1DepthFirstPaths test.txt "A" "F"
A to F: not connected

$ java upg1DepthFirstPaths database.txt "AL" "FL"
AL to FL: AL-TN-VA-NC-SC-GA-FL
$ java upg1DepthFirstPaths database.txt "FL" "NY"
FL to NY: FL-GA-TN-VA-WV-PA-NY
 */

public class upg1DepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there a path between s and v, or did we visit the vertices before
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    //compute a Graph G with a path between source vertex s and very other reachable vertex
    public upg1DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;            //label the vertex v as visited
        for (int w : G.adj(v)) {    //for all directed edges from v to w that are in adjacent list adj(v)
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);          //recursively visit vertices that has not been visited before
            }
        }
    }

    //check if there is a path between the source vertex s and the given vertex v
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    //return a path between the source vertex s and the vertex v
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();         //LIFO stack
        for (int x = v; x != s; x = edgeTo[x])
            path.pushEl(x);
        path.pushEl(s);
        return path;
    }

    public static void main (String[] args){
        String filename = args[0];
        String from = args[1];  //source vertex
        String to = args[2];    //destination

        SymbolGraph sg = new SymbolGraph(filename, " "); 
        Graph G = sg.graph();

        int start = sg.indexOf(from);       //source vertex
        int end = sg.indexOf(to);           //destination
        upg1DepthFirstPaths dfs = new upg1DepthFirstPaths(G,start);

        if(dfs.hasPathTo(end)){
            System.out.printf("%s to %s: ", sg.nameOf(start), sg.nameOf(end));
            for(int x : dfs.pathTo(end)){
                if(x == start)  StdOut.print(sg.nameOf(x));
                else            StdOut.print("-" + sg.nameOf(x));
            }
            System.out.println();
        }
        else{
            System.out.printf("%s to %s: not connected ", sg.nameOf(start), sg.nameOf(end));
        }
    }
}

