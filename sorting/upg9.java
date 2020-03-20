/*
Author: Keying Liang
Generated on: 2019-09-13
Updated on: 2019-09-22

This program implements quick sort algorithm, optimized with cutoff to insertion sort to sort smaller sub-arrays.
The code is taken from algs4.cs.princeton.edu, and optimized with insertion sort(code taken from Algorithms
on page 251.
The range for cut-off values to test with was [0-30].
Result:
Execution time with cutoff[0]:171ms
Execution time with cutoff[1]:132ms
Execution time with cutoff[2]:125ms
Execution time with cutoff[3]:124ms
Execution time with cutoff[4]:122ms
Execution time with cutoff[5]:122ms
Execution time with cutoff[6]:119ms
Execution time with cutoff[7]:118ms
Execution time with cutoff[8]:118ms
Execution time with cutoff[9]:123ms
Execution time with cutoff[10]:125ms
Execution time with cutoff[11]:119ms
Execution time with cutoff[12]:114ms
Execution time with cutoff[13]:115ms
Execution time with cutoff[14]:114ms
Execution time with cutoff[15]:113ms
Execution time with cutoff[16]:114ms
Execution time with cutoff[17]:114ms
Execution time with cutoff[18]:115ms
Execution time with cutoff[19]:113ms
Execution time with cutoff[20]:113ms
Execution time with cutoff[21]:113ms
Execution time with cutoff[22]:117ms
Execution time with cutoff[23]:114ms
Execution time with cutoff[24]:113ms
Execution time with cutoff[25]:114ms
Execution time with cutoff[26]:114ms
Execution time with cutoff[27]:113ms
Execution time with cutoff[28]:114ms
Execution time with cutoff[29]:113ms
Execution time with cutoff[30]:114ms

 */

public class upg9 {

        public static void sort(int[] a, int cutoff) {
            StdRandom.shuffle(a);
            sort(a, 0, a.length - 1, cutoff);
        }

        public static void insertionsort(int[] a, int lo, int hi)
        {  // Sort a[] into increasing order.
            int N = a.length;
            for (int i = lo; i <= hi; i++) {  // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
                for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                    exch(a, j, j-1);
            }
        }

        // quicksort the subarray from a[lo] to a[hi]
        private static void sort(int[] a, int lo, int hi, int cutoff) {
            while(lo < hi){
                if((hi - lo) < cutoff){
                    insertionsort(a, lo, hi);
                    break;
                }

                else {
                    int pivot = partition(a, lo, hi);
                    if((pivot - lo) < (hi - pivot)){
                        sort(a, lo, pivot-1, cutoff);
                        lo = pivot +1;
                    }
                    else{
                        sort(a, pivot+1, hi, cutoff);
                        hi = pivot - 1;
                    }
                }
            }
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

            for(int cutoff=0; cutoff<=30; cutoff++) {
                long cl = System.currentTimeMillis();
                sort(array, cutoff);
                long clEnd = System.currentTimeMillis();
                System.out.println("Execution time with cutoff[" + cutoff + "]:" + (clEnd - cl) + "ms");  //Count the execution time taken by the sort() method
            }

            //for(int nr: array)                    //Print the sorted array if desired
              //System.out.print(nr + " ");
        }
}
