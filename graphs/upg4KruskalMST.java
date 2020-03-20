/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-03
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley
Dependencies: upg3EdgeWeightedGraph.java Edge.java Queue.java MinPQ.java UF.java

This program computes a minimum spanning tree using Kruskal's algorithm in an edge-weighted graph.
This implementation uses union-find data type and

$ java upg4KruskalMST database.txt
There is/are 1 connected component(s)in the graph
MA <--> VT (413)
NE <--> WY (421)
IA <--> WI (429)
KY <--> WV (442)
NJ <--> PA (453)
OH <--> WV (459)
WI <--> MN (478)
OH <--> IN (481)
WV <--> VA (483)
PA <--> WV (483)
CO <--> WY (487)
NV <--> UT (510)
WY <--> UT (511)
DE <--> PA (531)
IL <--> WI (534)
CT <--> RI (553)
AZ <--> UT (592)
CA <--> OR (617)
MD <--> WV (684)
DC <--> VA (703)
OR <--> WA (703)
OK <--> TX (949)
LA <--> TX (1012)
NY <--> VT (1039)
NH <--> VT (1261)
TN <--> VA (1276)
GA <--> TN (1474)
MS <--> TN (1620)
FL <--> GA (1699)
AR <--> TX (6434)
IA <--> SD (28096)
NV <--> CA (28108)
WI <--> MI (30799)
ID <--> WA (33046)
MI <--> IN (35348)
GA <--> SC (35498)
KS <--> NE (37037)
NE <--> SD (38306)
ME <--> NH (39342)
TX <--> NM (39837)
NY <--> CT (42257)
MS <--> LA (46743)
MO <--> OK (49995)
VA <--> NC (50235)
MN <--> ND (59813)
MT <--> ND (68432)
NY <--> PA (78667)
AL <--> TN (452748)
Total weight: 1222038
*/

//import java.util.Random;
public class upg4KruskalMST {
    private double weight;                          //weight of MST
    private Queue<Edge> mst = new Queue<Edge>();        //edges in MST

    //Compute a minimum spanning tree (or forest) of an edge-weighted graph.
    public upg4KruskalMST(upg3EdgeWeightedGraph G) {
        //build heap by passing array of edges
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        // run greedy algorithm
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {  //as long as v-w does not create a cycle
                uf.union(v, w);         //merge v and w
                mst.enqueue(e);         //add edge e to the mst
                weight += e.weight();
            }
        }
        System.out.println("There is/are "+uf.count()+" connected component(s)"+"in the graph");
    }

    //Returns the edges in a minimum spanning tree (or forest).
    public Iterable<Edge> edges() {
        return mst;
    }

    //Returns the weight of the minimum spanning tree (or forest).
    public double weight() {
        return weight;
    }

    //Unit tests the {@code KruskalMST} data type.
    public static void main(String[] args) {
        String filename = args[0];
        SymbolGraph sg = new SymbolGraph(filename, " ");
        upg3EdgeWeightedGraph ewg = new upg3EdgeWeightedGraph(sg.size());
        Graph G  = sg.graph();

        //Random r = new Random();
        for(int i = 0; i < sg.size(); i++){     //assign unique weights to the edges
            long x = System.nanoTime();
            for(Object inbag : G.adj(i)) {              //plocka alla kanter som ligger i bag
                int in = (int) inbag;
                //int random = r.nextInt(1000);
                long y = System.nanoTime();
                double checkWeight = (double) y - x;

                for(Edge e: ewg.edges()){       //v->w and w->v should have the same weight
                    int one = e.either();
                    if(e.either() == in && e.other(one) == i){
                        checkWeight = (int) e.weight();
                    }
                }
                Edge e = new Edge(i, in, checkWeight);
                ewg.addEdge(e);
            }
        }
        //System.out.println("check: "+ ewg.toString());

        upg4KruskalMST mst = new upg4KruskalMST(ewg);

        for (Edge e : mst.edges()) {
            System.out.printf("%s <--> %s (%d) ", sg.nameOf(e.either()), sg.nameOf(e.other(e.either())), (int) e.weight());
            System.out.println();
        }
        System.out.printf("Total weight: "+"%d\n", (int) mst.weight());
    }

}
