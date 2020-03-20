/*
 Author: Keying Liang
 Generated on: 2019-09-23
 Updated on: 2019-09-27
 
 This is a filter program that removes all characters that are not alphabetic, blank or newline,
 then replaces every such character by a blank to keep the number of characters constant to the original text.
 
 Compilation: gcc upg1Filter.c
 Execution: ./a.out
 hej @_@ fr!day
 
 hej     fr day
 
 */

#include <stdio.h>
#include <ctype.h>

void filter(){
    char r = getchar();
    while(r != EOF){                               //read input stream
        if(isalpha(r) == 0 && r!=' ' && r!='\n'){ //find characters that are not alphabetic, blank or newline
            putchar(' ');}                        //replace such character by a blank
        else{
            putchar(r);}
        r = getchar();
    }
}

int main(){
    filter();
    return 0;
}
