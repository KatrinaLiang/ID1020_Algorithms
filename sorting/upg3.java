/*
Author: Keying Liang
Generated on: 2019-09-13
Updated on: 2019-09-20

This program implements selection sort algorithm to sort numbers of an array in ascending order.
It will also print the number of swaps performed when sorting the array.

sort() is taken from Algorithms page 249, less(), exch() from Algorithms on page 245.

 */

import java.util.Scanner;

public class upg3 {
    private static void show(int[] arr){
        System.out.println("Sorting..");
        for(int a: arr){
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void sort(int[] a){  // Sort a[] into increasing order.
        int l = a.length;

        for (int i = 0; i < l; i++) {  // Exchange a[i] with smallest element in a[i+1...N).
            int min = i;                 // index of minimal element.
            int swap = 0;
            for (int j = i + 1; j < l; j++)
                if (less(a[j], a[min])) {
                    min = j;
                }
            exch(a, i, min);
            if(min!=i)
                swap++;
            show(a);
            System.out.println("Number of swaps in the iteration:" + swap);
            System.out.println();
        }
    }

    private static boolean less(int v, int w){
        return v < w;
    }

    private static void exch(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main (String args[]){
        System.out.println("Enter the size of the input:");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] array = new int[size];

        System.out.println("Enter integers:");
        for(int i = 0; i < size; i++){
            array[i]=in.nextInt();
        }
        sort(array);
    }
}
/*
Inputs:
1 2 4 3 5 0

Outputs:
Sorting..
0 2 4 3 5 1
Number of swaps in the iteration:1

Sorting..
0 1 4 3 5 2
Number of swaps in the iteration:1

Sorting..
0 1 2 3 5 4
Number of swaps in the iteration:1

Sorting..
0 1 2 3 5 4
Number of swaps in the iteration:0

Sorting..
0 1 2 3 4 5
Number of swaps in the iteration:1

Sorting..
0 1 2 3 4 5
Number of swaps in the iteration:0
 */