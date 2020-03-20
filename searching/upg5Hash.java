/*
Author: Keying Liang
Generated on: 2019-09-26
Last updated: 2019-09-30

Compilation: javac upg5Hash.java
Execution: java upg5Hash < filterad.txt

This program will hash all the distinct words and count how many words got the same hash.

*/

public class upg5Hash {
    public static void main(String args[]){
        int[] arr = new int[100];

        upg2BST<String, Integer> st = new upg2BST<>(); //create a Binary search ST
        while (!StdIn.isEmpty()) {                  // compute frequency counts
            String key = StdIn.readString();
            if (st.contains(key)) { //if the key is already in the ST, value+1 the word appears one more time
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);     //if key not found in the ST, put it in the table
            }
        }

        for(String s: st.keys()) { //hash every distinct words in the text
            int hash = (s.hashCode() & 0x7fffffff) % 100;  //hash table array size M=97
            arr[hash]++;
        }

        for(int i: arr)
            System.out.println(i);
    }
}
