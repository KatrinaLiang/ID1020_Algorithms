/*
Author: Keying Liang
Generated on: 2019-09-24
Last updated: 2019-09-29

Dependencies: StdIn.java StdOut.java Queue.java
Sources: https://algs4.cs.princeton.edu/32bst/BST.java.html
https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/FrequencyCounter.java.html

In this program, a symbol table is implemented with a binary search tree.

$ java upg2BST 0 100 < filterad.txt
Execution time of the algorithm: 52 ms
The most frequent word: of 6
distinct = 71
words    = 100

$ java upg2BST 3 100 < filterad.txt
Execution time of the algorithm: 54 ms
The most frequent word: the 5
distinct = 77
words    = 100


$ java upg2BST 0 1000 < filterad.txt
Execution time of the algorithm: 71 ms
The most frequent word: the 56
distinct = 505
words    = 1000

$ java upg2BST 0 5000 < filterad.txt
Execution time of the algorithm: 92 ms
The most frequent word: the 373
distinct = 1545
words    = 5000

 */

import java.util.NoSuchElementException;

public class upg2BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of the tree
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    //Initializes an empty BST symbol table.
    public upg2BST() { }

    //Check if the symbol table is empty. Returns true if it is.
    public boolean isEmpty() { return size() == 0; }


    //Check the number of key-value pairs in this symbol table.
    public int size() {
            return size(root);
        }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
            else return x.size;
    }

    //Check if this symbol table contains the given key?
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    //Returns the value associated with the given key.
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    //Put key-value pair into the symbol table. Deletes the specified key from this symbol table if the value is null
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

        //Removes the smallest key and associated value from the symbol table.
        private Node deleteMin(Node x) {
            if (x.left == null) return x.right;
            x.left = deleteMin(x.left);
            x.size = size(x.left) + size(x.right) + 1;
            return x;
        }


        //Removes the specified key and its associated value from this symbol table(if the key is in this symbol table).
        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
            root = delete(root, key);
        }

        private Node delete(Node x, Key key) {
            if (x == null) return null;

            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x.left  = delete(x.left,  key);
            else if (cmp > 0) x.right = delete(x.right, key);
            else {
                if (x.right == null) return x.left;
                if (x.left  == null) return x.right;
                Node t = x;
                x = min(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
            x.size = size(x.left) + size(x.right) + 1;
            return x;
        }


        //Find the smallest key in the symbol table.
        public Key min() {
            if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
            return min(root).key;
        }

        private Node min(Node x) {
            if (x.left == null) return x;
            else                return min(x.left);
        }

        //Find the largest key in the symbol table.
        public Key max() {
            if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
            return max(root).key;
        }

        private Node max(Node x) {
            if (x.right == null) return x;
            else                 return max(x.right);
        }

        //Find the largest key in the symbol table less than or equal to (Key key).
        public Key floor(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to floor() is null");
            if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
            Node x = floor(root, key);
            if (x == null) return null;
            else return x.key;
        }

        private Node floor(Node x, Key key) {
            if (x == null) return null;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            if (cmp <  0) return floor(x.left, key);
            Node t = floor(x.right, key);
            if (t != null) return t;
            else return x;
        }


        //Find the smallest key in the symbol table greater than or equal to (Key key).
        public Key ceiling(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
            if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
            Node x = ceiling(root, key);
            if (x == null) return null;
            else return x.key;
        }

        private Node ceiling(Node x, Key key) {
            if (x == null) return null;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            if (cmp < 0) {
                Node t = ceiling(x.left, key);
                if (t != null) return t;
                else return x;
            }
            return ceiling(x.right, key);
        }

        //Return the key in the symbol table whose rank is {@code k}.This is the (k+1)st smallest key in the symbol table.
        public Key select(int k) {
            if (k < 0 || k >= size()) {
                throw new IllegalArgumentException("argument to select() is invalid: " + k);
            }
            Node x = select(root, k);
            return x.key;
        }

        // Return key of rank k.
        private Node select(Node x, int k) {
            if (x == null) return null;
            int t = size(x.left);
            if      (t > k) return select(x.left,  k);
            else if (t < k) return select(x.right, k-t-1);
            else            return x;
        }
        //Returns the number of keys in this symbol table strictly less than (Key key).
        public int rank(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to rank() is null");
            return rank(key, root);
        }

        // Number of keys in the subtree less than key.
        private int rank(Key key, Node x) {
            if (x == null) return 0;
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) return rank(key, x.left);
            else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
            else              return size(x.left);
        }


         //Returns all keys in the symbol table as an {@code Iterable}.
         //To iterate over all of the keys in the symbol table named {@code st},
        public Iterable<Key> keys() {
            if (isEmpty()) return new Queue<Key>();
            return keys(min(), max());
        }

        //Returns all keys in the symbol table in the given range
        public Iterable<Key> keys(Key lo, Key hi) {
            if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
            if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

            Queue<Key> queue = new Queue<Key>();
            keys(root, queue, lo, hi);
            return queue;
        }

        private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
            if (x == null) return;
            int cmplo = lo.compareTo(x.key);
            int cmphi = hi.compareTo(x.key);
            if (cmplo < 0) keys(x.left, queue, lo, hi);
            if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
            if (cmphi > 0) keys(x.right, queue, lo, hi);
        }

        // Returns the number of keys in the symbol table in the given range.
        public int size(Key lo, Key hi) {
            if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
            if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

            if (lo.compareTo(hi) > 0) return 0;
            if (contains(hi)) return rank(hi) - rank(lo) + 1;
            else              return rank(hi) - rank(lo);
        }

        //Returns the height of the BST
        public int height() {
            return height(root);
        }
        private int height(Node x) {
            if (x == null) return -1;
            return 1 + Math.max(height(x.left), height(x.right));
        }

    public static void main(String[] args) {
            int distinct = 0, words = 0;
            int minlen = Integer.parseInt(args[0]);
            int firstNwords = Integer.parseInt(args[1]);

            long cl = System.currentTimeMillis();
            upg2BST<String, Integer> st = new upg2BST<String, Integer>();   //create a new BST
            while (!StdIn.isEmpty()) {                  // count frequencies
                String key = StdIn.readString();
                if (key.length() < minlen) continue;    //ignore words shorter than minlen
                words++;
                if(words == firstNwords) break;         //only reads the first N words
                if (st.contains(key)) { //if the key is already in the ST, value+1 the word appears one more time
                    st.put(key, st.get(key) + 1);
                }
                else {
                    st.put(key, 1);     //if key not found in the ST, put it in the table
                    distinct++;
                }
            }
            //long cl = System.currentTimeMillis();  //count time for assignment 4
            // Find the key with the highest frequency counted of the first N words.
            String max = "";
            st.put(max, 0);
            for (String word : st.keys()) {
                if (st.get(word) > st.get(max))
                    max = word;
            }

            long clend = System.currentTimeMillis();
            StdOut.println("Execution time of the algorithm: "+ (clend-cl)+" ms");

            StdOut.println("The most frequent word: " + max + " " + st.get(max));
            StdOut.println("distinct = " + distinct);
            StdOut.println("words    = " + words);
        }
}