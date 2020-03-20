/*
Author: Keying Liang
Generated on: 2019-09-24
Last updated: 2019-09-29

Dependencies: StdIn.java StdOut.java
Sources: https://algs4.cs.princeton.edu/31elementary/BinarySearchST.java.html
https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/FrequencyCounter.java.html

In this program, a symbol table is implemented with binary search in an ordered array.

$ java upg2BinarySearchST 0 100 < filterad.txt
Execution time of the algorithm: 55 ms
The most frequent word: of 6
distinct = 71
words    = 100

$ java upg2BinarySearchST 3 100 < filterad.txt
Execution time of the algorithm: 54 ms
The most frequent word: the 5
distinct = 77
words    = 100

$ java upg2BinarySearchST 0 1000 < filterad.txt
Execution time of the algorithm: 72 ms
The most frequent word: the 56
distinct = 505
words    = 1000

$ java upg2BinarySearchST 0 5000 < filterad.txt
Execution time of the algorithm: 107 ms
The most frequent word: the 373
distinct = 1545
words    = 5000

 */

import java.util.NoSuchElementException;

public class upg2BinarySearchST<Key extends Comparable, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    //Initializes an empty binary search symbol table.
    public upg2BinarySearchST() {
        this(INIT_CAPACITY);
    }

    //Initializes an empty symbol table with the specified initial capacity.
    public upg2BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    //resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= n;
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    //Check the number of key-value pairs in this symbol table.
    public int size() {
        return n;
    }

    //Returns true if this symbol table is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    //Check if this symbol table contains the given key?
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    //Get the value associated with the given key in this symbol table.
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    //Returns the number of keys in this symbol table that are smaller than (Key key).
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");

        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    //Put key-value pair into the symbol table. Deletes the specified key from this symbol table if the value is null
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        //if the key is already in table
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        //Insert new key-value pair. Resize the keys array if needed
        if (n == keys.length) resize(2 * keys.length);

        for (int j = n; j > i; j--) {       //move all keys and values to the next index
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;                      //insert the new key-value pair
        vals[i] = val;
        n++;

        assert check();
    }

    //Removes the specified key and associated value from this symbol table(if the key is in the symbol table).
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        n--;
        keys[n] = null;  // to avoid loitering
        vals[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);

        assert check();
    }

    //Return the kth smallest key in this symbol table.
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }

    //Returns the largest key in this symbol table.
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[n-1];
    }

   public Iterable<Key> keys() {
       return keys(min(), max());
   }

    //Returns all keys in this symbol table in the given range
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0)
            return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
            return queue;
    }

    private boolean check() { return isSorted() && rankCheck(); }

    // are the items in the array in ascending order?
    private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i - 1]) < 0) return false;
        return true;
    }

    // check that rank(select(i)) = i
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        int firstNwords = Integer.parseInt(args[1]);


        upg2BinarySearchST<String, Integer> st = new upg2BinarySearchST<>(); //create a Binary search ST
        while (!StdIn.isEmpty()) {                  // count frequencies
            String key = StdIn.readString();
            if (key.length() < minlen) continue;        //ignore words shorter than minlen
            words++;
            if(words == firstNwords) break;             //only reads the first N words
            if (st.contains(key)) { //if the key is already in the ST, value+1 the word appears one more time
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);     //if key not found in the ST, put it in the table
                distinct++;
            }
        }
        long cl = System.currentTimeMillis();
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