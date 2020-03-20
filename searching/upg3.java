/*
Author: Keying Liang
Generated on: 2019-09-24
Last updated: 2019-09-28

Dependencies: StdIn.java StdOut.java upg2BinarySearchST.java
Sources: https://algs4.cs.princeton.edu/31elementary/BinarySearchST.java.html
https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/FrequencyCounter.java.html

This program extends the BinarySearchST of the assignment 2 so that it allows the user to query the
system on which are the nth to the n+xth most frequent words in the full text.

Execution examples:
$ java upg3 2 3 < filterad.txt
2th to 5th most frequent words:
and 4921
of 4102
to 3601
a 2864

$ java upg3 10 2 < filterad.txt
10th to 12th most frequent words:
was 1766
it 1747
he 1460

$ java upg3 1 5 < filterad.txt
1th to 6th most frequent words:
the 7577
and 4921
of 4102
to 3601
a 2864
in 2541

 */

class upg3{
    public static void printNtoNpX(int nth, int x, upg2BinarySearchST<String, Integer> st) {
        for (int i = 1; i < nth; i++) {  //ignore the 0 to (n-1)th most frequent words
            String max = "";
            st.put(max, 0);
            for (String s : st.keys()) {
                if (st.get(s) > st.get(max))
                    max = s;
            }
            st.put(max, st.get(max) * (-1)); //multiply the values of 0 to (n-1)th most frequent keys with -1
        }

        for (int i = nth; i <= (nth+x); i++) { //print the nth to (n+x)th most frequent words
            String max = "";
            st.put(max, 0);
            for (String s : st.keys()) {
                if (st.get(s) > st.get(max))
                    max = s;
            }
            StdOut.println(max + " " + st.get(max));
            st.put(max, st.get(max) * (-1)); //value*(-1) to find the next most frequent word in the next iteration
        }

        for(int i=1; i<=(nth+x); i++){  //restore the values of 0 to (n-1)th most frequent keys
            for(String s: st.keys()){
                if(st.get(s) < 0)
                    st.put(s, st.get(s)*(-1));
            }
        }
    }
    public static void main(String[] args) {
        int nth = Integer.parseInt(args[0]);
        int x = Integer.parseInt(args[1]);

        upg2BinarySearchST<String, Integer> st = new upg2BinarySearchST<>(); //create a binary search ST
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
            }
        }
        StdOut.println(nth+"th to "+(nth+x)+"th most frequent words:");
        printNtoNpX(nth, x, st);
        }
}
