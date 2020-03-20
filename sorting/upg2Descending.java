/*
Author: Keying Liang
Generated on: 2019-09-13
Updated on: 2019-09-20

This program implements selection sort algorithm to sort numbers of an array in descending order.
No change is meade in the sorting method. In main() we multiply all elements in the array with -1 once before sorting
and once after sorting.

sort() is taken from Algorithms page 249, less(), exch() from Algorithms on page 245.
 */
import java.util.Scanner;

public class upg2Descending {
    private static void show(int[] arr){
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
            array[i]=(0-in.nextInt());  //Multiply all elements in the array with -1
        }

        show(array);
        sort(array);

        for(int i = 0; i < size; i++){
            array[i]= array[i]*(-1);    //Multiply all elements in the array with -1 again
        }

        show(array);
    }
}

/*
Inputs:
1 2 4 3 5 0

Outputs:
-1 -2 -4 -3 -5 0
-5 -2 -4 -3 -1 0
-5 -4 -2 -3 -1 0
-5 -4 -3 -2 -1 0
-5 -4 -3 -2 -1 0
-5 -4 -3 -2 -1 0
-5 -4 -3 -2 -1 0
5 4 3 2 1 0
 */