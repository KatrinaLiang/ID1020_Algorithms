/*
Authors: Robert Sedgewick, Kevin Wayne
Editor: Keying Liang
Generated on: 2019-10-04
Last updated: 2019-10-08

Source: Algoritms 4th edition, Robert Sedgewick, Kevin Wayne, 2011, Addison-Wesley
Dependencies: BST.java Digraph.java

The SymbolDigraph class represents a directed graph, a wrapper around the Digraph data type which assumes
the vertex names are integers.
This implementation uses an BST to map vertex names from strings to integers bewteen 0 to V-1,
where V is the number of vertices in the graph.

 */

import java.io.*;
import java.util.Scanner;

public class SymbolDigraph {
    private BST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Digraph graph;           // the underlying digraph

    //create a digraph from in input file using the specified delimiter
    public SymbolDigraph(String filename, String delimiter) {
        st = new BST<String, Integer>();
        File file = new File(filename);
        //Assign indices to vertices by reading strings to associate distinct strings with an index
        try {
            Scanner in = new Scanner(file);

            while (in.hasNextLine()) {
                String[] a = in.nextLine().split(delimiter);
                for (int i = 0; i < a.length; i++) {
                    if (!st.contains(a[i]))
                        st.put(a[i], st.size());
                }
            }

            //map from integer to string
            keys = new String[st.size()];
            for (String name : st.keys()) {
                keys[st.get(name)] = name;
            }

            //build the digraph by connecting first vertex on each line to all others
            graph = new Digraph(st.size());
            in = new Scanner(filename);
            while (in.hasNextLine()) {
                String[] a = in.nextLine().split(delimiter);
                int v = st.get(a[0]);
                for (int i = 1; i < a.length; i++) {
                    int w = st.get(a[i]);
                    graph.addEdge(v, w);
                }
            }
        }
        catch(FileNotFoundException e){ System.out.println("File not found");}
    }

    //Returns the integer associated with the vertex named s
    public int indexOf(String s) {
        return st.get(s);
    }

    //Returns the name of the vertex associated with the integer v
    public String nameOf(int v) {
        return keys[v];
    }

    //Returns the digraph assoicated with the symbol graph
    public Digraph digraph() {
        return graph;
    }

    // Unit tests the SymbolDigraph data type.
    /*public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Digraph graph = sg.digraph();
        while (!StdIn.isEmpty()) {
            String t = StdIn.readLine();
            for (int v : graph.adj(sg.indexOf(t))) {
                System.out.println("   " + sg.nameOf(v));
            }
        }
    }*/
}