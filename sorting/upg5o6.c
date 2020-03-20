/*
 Author: Keying Liang
 Generated on: 2019-09-17
 Updated on: 2019-09-20
 
 Assignment 5: This program will reaarange an array contains both positive and negative numbers,
 so that all negative elements come before the positive. And the algorithm is in-place.
 Time complexity: O(N) (loop through the array once)

 Assignment 6: Loop invariant: Efter ith interation of the for loop, no element on the left of p[exch] is positive.
*/

#include <stdio.h>

void rearrange(int *p){
    int exch = 0;
    int temp;
    for(int i=0; i<5; i++){   //loop through the array
        if(p[i]<0){             //if the element is negative, change place with element in p[j]
            temp = p[i];
            p[i] = p[exch];
            p[exch] = temp;
            exch++;
        }
    }
    for(int i=0; i<5; i++)
        printf("%d\n", p[i]);
}

int main (){
    int array[5] = {4,2,5,-6,-1};
    rearrange(array);
    return 0;
}

/*
 outputs: -6  -1  5  4  2
*/
