/*
Author: Keying Liang
Generated on: 2019-09-25
Last updated: 2019-09-30

 Dependencies: StdIn.java StdOut.java

 Sources:   https://algs4.cs.princeton.edu/34hash/LinearProbingHashST.java.
 https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/FrequencyCounter.java.html


In this program, a symbol table is implemented with a linear probing hash table.

$ java upg7LinearProbingHashST 0 < filterad.txt
Execution time of the algorithm: 5 ms
The most frequent word: the 7577
distinct = 10982
words    = 141491
 */

public class upg7LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;           // number of key-value pairs in the symbol table
    private int m = 16;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values


    //Initializes an empty symbol table.
    public upg7LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    //Initializes an empty symbol table with the specified initial capacity.
    public upg7LinearProbingHashST(int capacity) {//int capacity
        m = capacity;
        n = 0;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }

    //Returns the number of key-value pairs in this symbol table.
    public int size() {
        return n;
    }

    //Returns true if this symbol table is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    //Returns true if this symbol table contains the specified key.
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        upg7LinearProbingHashST<Key, Value> temp = new upg7LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }

    //Inserts the specified key-value pair into the symbol table
    public void put(Key key, Value val) {
        // double table size if 50% full
        if (n >= m/2) resize(2*m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    //Returns the value associated with the specified key.
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    //To iterate over all of the keys in the symbol table
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

    public static void main(String[] args) { 
        upg7LinearProbingHashST<String, Integer> str = new upg7LinearProbingHashST<String, Integer>();
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        //int firstNwords = Integer.parseInt(args[1]);

        //long cl = System.currentTimeMillis();
        while (!StdIn.isEmpty()) {                  // count frequencies
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            words++;
            //if(words == firstNwords) break;
            if (str.contains(key)) {
                str.put(key, str.get(key) + 1);
            }
            else {
                str.put(key, 1);
                distinct++;
            }
        }
        long cl = System.currentTimeMillis();
        // Find the key with the highest frequency counted of the first N words.
        String max = "";
        str.put(max, 0);
        for (String word : str.keys()) {
            if (str.get(word) > str.get(max))
                max = word;
        }
        long clend = System.currentTimeMillis();
        StdOut.println("Execution time of the algorithm: "+ (clend-cl)+" ms");

        StdOut.println("The most frequent word: " + max + " " + str.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);

    }
}
