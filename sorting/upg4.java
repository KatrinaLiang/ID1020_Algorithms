/*
Author: Keying Liang
Generated on: 2019-09-12
Updated on: 2019-09-21

This program implements selection sort algorithm. It includes a method printInversions() which counts the number
of inversions in the input array and prints a list of all inversions on the format [i,a[i]], [j, a[j]]
where i and j are indices and a[i], a[j] are the values of the elements.
The method is called from main() before the array is sorted.

sort() is taken from Algorithms page 249, less(), exch() from Algorithms on page 245.

Time complexity: O(N^2). There are 2 for loops in the method printInversions(), each for loop go through the
array with length N.
 */

import java.util.Scanner;

public class upg4 {

    private static void printInversions(int[] a) {
        System.out.println("Inversions");
        int l = a.length;
        int count = 0;
        for (int i = 0; i < l; i++) {
            int min = i;
            for (int j = i + 1; j < l; j++) {
                if (less(a[j], a[min])) {
                    count++;
                    System.out.print("[" + min + "," + a[min] + "]");
                    System.out.println("[" + j + "," + a[j] + "]");
                }
            }
        }
        System.out.println("Number of inversions:" + count);
    }

    public static void sort(int[] a){  // Sort a[] into increasing order.
        int l = a.length;               // array length
        for (int i = 0; i < l; i++) {  // Exchange a[i] with smallest element in a[i+1...N).
            int min = i;                 // index of minimal element.
            for (int j = i + 1; j < l; j++)
                if (less(a[j], a[min]))
                    min = j;
              if(min!=i)
                exch(a, i, min);
        }
    }

    private static boolean less(int v, int w){
        return v < w;
    }

    private static void exch(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
        printInversions(array);
        sort(array);

    }
}
/*
Inputs:
1 2 4 3 5 0

Outputs:
Inversions
[0,1][5,0]
[1,2][5,0]
[2,4][3,3]
[2,4][5,0]
[3,3][5,0]
[4,5][5,0]
Number of inversions:6
 */

