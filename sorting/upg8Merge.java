/*
Author: Keying Liang
Generated on: 2019-09-13
Updated on: 2019-09-22

This program implements merge sort algorithm to sort int numbers of an array in ascending order.
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

public class upg8Merge {

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
    public static void sort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length-1);
        //assert isSorted(a);
    }

    // is v < w ?
    private static boolean less(int v, int w) {
        return v < w;
    }

    public static void main(String[] args) {
        int[] array = StdIn.readAllInts();      //Reads in a sequence of int from standard input
        long cl = System.currentTimeMillis();
        sort(array);                            //Count the execution time taken by the sort() method
        long clEnd = System.currentTimeMillis();
        System.out.println("Execution time for Merge Sort:" + (clEnd-cl) + "ms");

        //for(int nr: array)                   //Print the sorted array if desired
            //System.out.print(nr + " ");

    }
}
