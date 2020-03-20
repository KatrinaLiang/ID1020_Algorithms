/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-01
Last updated: 2019-10-08

Dependencies: Graph.java Queue.java Stack.java StdOut.java
Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley

This program runs breadth first search on an undirected graph. The BreadthFirstPaths class
represents a data type for finding the shortest path from a source vertex "X"
to another vertex "Y" in an undirected graph.

test.txt
A B
A C
B D
C D
D A
E C
F G
$ java upg2BreadthFirstPaths test.txt "A" "E"
A to E: A-C-E
$ java upg2BreadthFirstPaths test.txt "A" "F"
A to F: not connected

$ java upg2BreadthFirstPaths database.txt "AL" "FL"
AL to FL: AL-FL
$ java upg2BreadthFirstPaths database.txt "FL" "NY"
FL to NY: FL-GA-TN-VA-WV-PA-NY
 */

public class upg2BreadthFirstPaths {
    private boolean[] marked;       // marked[v] = is there a path between s and v, or did we visit the vertices before
    private int[] edgeTo;           //previous edge on shortest s-v path
    private final int s;            //the source vertex

    //Computes the shortest path between the source vertex s and every other vertex in the graph G
    public upg2BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int [G.V()];
        this.s = s;
        bfs(G,s);
    }

    //Breadth first search the graph from a single source s
    private void bfs(Graph G, int s){
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;           //mark the source as visited
        queue.enqueue(s);

        while(!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w: G.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    // check if there is a path between the source vertex and vertex v
    public boolean hasPathTo (int v){
        return marked[v];
    }

    // the number of edges in a shortest path from the source vertex to vertex v;
    public Iterable<Integer> pathTo (int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.pushEl(x);
        path.pushEl(s);
        return path;
    }

    public static void main (String[] args){
        String file = args[0];
        String from = args[1];      //source vertex
        String to = args[2];        //destination

        SymbolGraph sg = new SymbolGraph(file, " "); //creates a SymbolGraph with a input file. Delimiter is a blank space
        Graph G = sg.graph();

        int start = sg.indexOf(from);
        int end = sg.indexOf(to);
        upg2BreadthFirstPaths bfs = new upg2BreadthFirstPaths(G,start); //Breadth first search the graph G from a single source start

        // print the path between the source vertex and destination
        if(bfs.hasPathTo(end)){
            System.out.printf("%s to %s: ", sg.nameOf(start), sg.nameOf(end));
            for(int x : bfs.pathTo(end)){
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
