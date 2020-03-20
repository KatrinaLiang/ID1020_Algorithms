/*
Author: Keying Liang
Generated on: 2019-09-25
Last updated: 2019-09-30

 Compilation:  javac upg7upg7SeparateChainingHashST.java
 Execution:    java upg7SeparateChainingHashST < input.txt
 Dependencies: SequentialSearchST.java StdIn.java StdOut.java
 Sources: https://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html
 https://algs4.cs.princeton.edu/34hash/SequentialSearchST.java.html
 https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/FrequencyCounter.java.html

 In this program, a symbol table is implemented with a separate-chaining hash table.

 $ java upg7SeparateChainingHashST 0 < filterad.txt
Execution time of the algorithm: 4 ms
The most frequent word: the 7577
distinct = 10982
words    = 141491
 */

public class upg7SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables


    //Initializes an empty symbol table.
    public upg7SeparateChainingHashST() {
        this(997);
    } // hash table size m= 997

    //Initializes an empty symbol table with m chains.
    public upg7SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    } 

    // resize the hash table to have the given number of chains, then rehashing all of the keys
    private void resize(int chains) {
        upg7SeparateChainingHashST<Key, Value> temp = new upg7SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m  = temp.m;
        this.n  = temp.n;
        this.st = temp.st;
    }

    // hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    } 

    //Returns the number of key-value pairs in this symbol table.
    public int size() {
        return n;
    } 

    //Returns true if this symbol table is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    //Returns the value associated with the specified key in this symbol table.
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    }

    //Put key-value pair into the symbol table
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (n >= 10*m) resize(2*m);

        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, val);
    } 

    //Removes the specified key and its associated value from this symbol table
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
    }

    //Check if this symbol table contains the given key
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    } 

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        //int firstNwords = Integer.parseInt(args[1]);

        upg7SeparateChainingHashST<String, Integer> str = new upg7SeparateChainingHashST<>();

        //long cl = System.currentTimeMillis();
        while (!StdIn.isEmpty()) {                      // count frequencies
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            words++;
            //if(words == firstNwords) break;             //only reads the first N words
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
