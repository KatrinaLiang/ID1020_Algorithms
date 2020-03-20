/*
Author: Keying Liang
Generated on: 2019-09-13
Updated on: 2019-09-22

This program implements quick sort algorithm to sort int numbers of an array in ascending order.
The code is taken from algs4.cs.princeton.edu, and small changes are made.

Result:
100 thousand inputs:
Execution time for Merge sort: 23ms
Execution time for Quick sort: 24ms

1 million inputs:
Execution time for Merge sort: 144ms
Execution time for Quick sort: 148ms

10 million inputs:
Execution time for Merge sort: 1640ms
Execution time for Quick sort: 1276ms

There was not significant difference between merge sort and quick sort when the input size was less
than 10 million. But still, merge sort ran slightly faster than quick sort.
However, with 10 million inputs, quick sort ran 22% faster than merge sort.

 */

public class upg8Quick {
    public static void sort(int[] a) {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }


    // is v < w ?
    private static boolean less(int v, int w) {
        if (v == w) return false;   // optimization when reference equals
        return v < w;
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        int[] array = StdIn.readAllInts();      //Reads in a sequence of int from standard input
        long cl = System.currentTimeMillis();
        sort(array);                            //Count the execution time taken by the sort() method
        long clEnd = System.currentTimeMillis();
        System.out.println("Execution time for Quick Sort:" + (clEnd-cl) + "ms");

        //for(int nr: array)                    //Print the sorted array if desired
            //System.out.print(nr + " ");

    }
}

