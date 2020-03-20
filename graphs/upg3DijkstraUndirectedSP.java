/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-01
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley
Dependencies: EdgeWeightedGraph.java IndexMinPQ.java Stack.java Edge.java

This program implements Dijkstra's algorithm to find the shortest path between two vertices in an edge-weighted
undirected graph, and print the vertices traversed and the associated sum of the weights of the path.
Unique nonnegative weights will be assigned to the edges (1,2,3...E) in the graph.
The algorithm picks the unvisited vertex with the low distance, calculates the distance through it to each
unvisited neighbor, and updates the neighbor's distance if smaller. Mark visited when done with neighbors.

test.txt
A B
A C
B D
C D
D A
E C
F G
$ java upg3DijkstraUndirectedSP test.txt "A" "E"
A to E (658758):
D <--> A (643970)
D <--> C (13907)
E <--> C (881)

$ java upg3DijkstraUndirectedSP database.txt "AL" "FL"
AL to FL (391770):
TN <--> AL (389119)
TN <--> GA (918)
GA <--> FL (1733)

*/

public class upg3DijkstraUndirectedSP {
    private double[] distTo;          // distTo[v]: distance of the shortest path from source to v
    private Edge[] edgeTo;            // edgeTo[v]: last edge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices

    //Build a shortest-paths graph from the source vertex s to every other vertex in the edge-weighted graph G
    public upg3DijkstraUndirectedSP(upg3EdgeWeightedGraph G, int s) {
        for (Edge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];

        distTo[s] = 0.0;                        //initialize distTo[source vertex] to 0
        for (int v = 0; v < G.V(); v++)         //initialize distTo[] of other vertices to positive infinity
            distTo[v] = Double.POSITIVE_INFINITY;


        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();    //v = associated index of the minimum key in the PQ
            for (Edge e : G.adj(v)) //for all neighbors vertices of v
                relax(e, v);        //relax vertices in order of distance from s
            //Reachable vertices are relaxed in order of the weight of their shortest path from s
        }
    }

    //relax edge e and update pq if changed
    //Undersök om det finns en billigare väg via nod v till närliggande noder.
    private void relax(Edge e, int v) {
        int w = e.other(v);         //the other edge of the edge
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    //Returns the length of a shortest path between the source vertex s and vertex v
    public double distTo(int v) {return distTo[v];}

    //check if there is a path between the source vertex s and v
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    //Returns a shortest path between the source vertex s and vertex v
    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.pushEl(e);
            x = e.other(x);
        }
        return path;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String from = args[1];      //source vertex
        String to = args[2];        //destination

        SymbolGraph sg = new SymbolGraph(filename, " ");        //creates a SymbolGraph with a input file. Delimiter is a blank space
        Graph G  = sg.graph();
        upg3EdgeWeightedGraph ewg = new upg3EdgeWeightedGraph(sg.size());  //Initialize an edge weighted graph with the size of the symbol graph

        int start = sg.indexOf(from);
        int end = sg.indexOf(to);

        //Random r = new Random();
        for(int i = 0; i < sg.size(); i++) {        //assign unique weights to the edges
            long x = System.nanoTime();
            for (Object inbag : G.adj(i)) {              //plocka alla kanter som ligger i bag
                int in = (int) inbag;
                //int random = r.nextInt(1000);
                long y = System.nanoTime();
                double checkWeight = (double) y - x;

                for (Edge e : ewg.edges()) {        //v->w and w->v should have the same weight
                    int one = e.either();
                    if (e.either() == in && e.other(one) == i) {
                        checkWeight = (int) e.weight();
                    }
                }
                Edge e = new Edge(i, in, checkWeight);
                ewg.addEdge(e);
            }
        }
        upg3DijkstraUndirectedSP sp = new upg3DijkstraUndirectedSP(ewg, start);

        // print the path between the source vertex and destination
        if (sp.hasPathTo(end)) {
            System.out.printf("%s to %s (%d): ", sg.nameOf(start), sg.nameOf(end), (int) sp.distTo[end]);
            System.out.println();
            for (Edge e : sp.pathTo(end)) {
                System.out.printf("%s <--> %s (%d) ", sg.nameOf(e.either()), sg.nameOf(e.other(e.either())), (int) e.weight());
                System.out.println();
            }
        }
        else {
            System.out.printf("%s to %s         no path\n", sg.nameOf(start), sg.nameOf(end));
        }
    }
}
