/*
Author: Keying Liang
Generated on: 2019-09-25
Last updated: 2019-09-30

Dependencies: upg2BST.java StdIn.java
This program allows the user find all positions in the text (i.e. the number of characters from the beginning)
the word X occurs. The program will print the position of all occurrences of X as answer to the query.
In this assignment, Java library (built-in) ArrayList is used.

$ java upg6Index "lodge" < filterad.txt
230956

$ java upg6Index "confidence" < filterad.txt
16800
129420
140624
192768
269705
271662
273335
273383
273660
291460
301679
301708
304333
347617
366950
367548
402433
410414
410547
412500
430483
477301
477776
480453
534144
578776
605740
623241
652296
669424
675145
702983
731687
*/

import java.util.*;

public class upg6Index {
    public static void main(String args[]){
        String keyword = args[0];
        int nrofChar = 0;

        //Build a BST with Key of type String, and Value of an ArrayList that holds all indices of the keyword occurs
        upg2BST<String, ArrayList<Integer>> st = new upg2BST<>();
        String str = "";
        while (!StdIn.isEmpty()) {
            char character = StdIn.readChar();       //Read one character at a time
            nrofChar++;                             //count numbers of characters

            if(character==' ' || character=='\n'){   //if a blank space or new line is found, all characters that come before are a word
                if(!st.contains(str)){               //if the word found is no in the ST, put it in the ST and create a new ArrayList
                    st.put(str, new ArrayList<>());
                    st.get(str).add(nrofChar-str.length()); //and add the index of the word
                }
                else {                                //if the word exists in the ST, put the index in the ArrayList
                    st.get(str).add(nrofChar - str.length());
                }
                str = "";
            }

            else{                       //else, an alphabet is found, put it in the str
                str = str + character;
            }
        }
        for(Integer x: st.get(keyword))     //print all indices of the keyword
            System.out.println(x);
    }
}