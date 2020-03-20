/*
Author: Keying Liang
Generated on: 2019-09-12
Updated on: 2019-09-20

In this program, selection sort algorithm is implemented to sort an array.
In main() the user is allowed to define the size of the input (N) and then
enter N integers from the command line which is to be sorted.
All the content of the array that is being sorted is printed after each inner loop iteration.

sort() is taken from Algorithms page 249, less(), exch() from Algorithms on page 245.

*/

import java.util.Scanner;

public class upg1SelSort{

    private static void show(int[] arr){  //print the array
        System.out.println("Sorting..");
        for(int a: arr){
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void sort(int[] a){  // Sort a[] into increasing order.
        int l = a.length;
        for (int i = 0; i < l; i++) {  // Exchange a[i] with smallest element in a[i+1...N).
            int min = i;                 // index of minimal element
            for (int j = i + 1; j < l; j++)
                if (less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
            show(a);
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
Sorting..
0 1 4 3 5 2
Sorting..
0 1 2 3 5 4
Sorting..
0 1 2 3 5 4
Sorting..
0 1 2 3 4 5
Sorting..
0 1 2 3 4 5
 */