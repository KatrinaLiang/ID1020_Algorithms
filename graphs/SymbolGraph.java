/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-01
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley
Dependencies: ST.java Graph.java StdIn.java

This program represents an undirected graph, a wrapper around the Graph data type which assumes
the vertex names are integers.
This implementation uses an BST to map vertex names from strings to integers bewteen 0 to V-1,
where v is the number of vertices in the graph.
 */

import java.io.*;
import java.util.Scanner;

public class SymbolGraph {
    private BST<String, Integer> st;  //use BST to map from string to index
    private String[] keys;           //use an array to map from index to string
    private Graph graph;             //store the underlying graph

    //create a graph from in input file using the specified delimiter
    public SymbolGraph(String filename, String delimiter) {
        st = new BST<String, Integer>();    //a symbol table of BST
        File file = new File(filename);
        //Assign indices to vertices by reading strings to associate distinct strings with an index
        try {
            Scanner in = new Scanner(file);

            while (in.hasNext()) {
                String[] a = in.nextLine().split(delimiter);
                for (int i = 0; i < a.length; i++) {
                    if (!st.contains(a[i]))
                        st.put(a[i], st.size());   //st.size() = number of vertex in the ST. map vertex name to integer(array index)
                }
            }

            //map from integer to string
            keys = new String[st.size()];
            for (String name : st.keys()) {
                keys[st.get(name)] = name;
            }

            //build the graph by connecting first vertex on each line to all other vertices
            graph = new Graph(st.size());
            in = new Scanner(file);
            while (in.hasNextLine()) {
                String[] a = in.nextLine().split(delimiter);
                int v = st.get(a[0]);
                for (int i = 1; i < a.length; i++) {
                    int w = st.get(a[i]);
                    graph.addEdge(v, w);
                }
            }
        }catch(FileNotFoundException e){ System.out.println("File not found");}
    }

    //Check if the graph contain the vertex named s
    public boolean contains(String s) {
        return st.contains(s);
    }


    //Returns the integer associated with the vertex named s
    public int indexOf(String s) {
        return st.get(s);
    }


    //Returns the name of the vertex associated with the integer v
    public String nameOf(int v) {
        return keys[v];
    }

    //Returns the graph assoicated with the symbol graph
    public Graph graph() {
        return graph;
    }

    //Returns the size of the ST
    public int size() {
        return st.size();
    }

    //Unit tests the SymbolGraph data type.
    /*public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (int v : graph.adj(s)) {
                    System.out.println("   " + sg.nameOf(v));
                }
            }
            else {
                System.out.println("input not contain '" + source + "'");
            }
        }
    }*/

}
