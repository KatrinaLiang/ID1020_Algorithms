/*
Author: Keying Liang
Generated on: 2019-09-12
Updated on: 2019-09-21

This program implement a linked list which should hold elements of type int.
The elements will be stored in ascending order in the list (order the elements when they are inserted).
The elements of the list will be printed after each enqueue operation in your unit test.

 */

public class upg7LinkedList {
    public static void main (String []args){
        upg7LinkedList array = new upg7LinkedList();
        array.enqueue(5);
        array.enqueue(2);
        array.enqueue(3);
        array.enqueue(10);
    }

    public void enqueue (int c){
        Node n = new Node ();
        n.data = c;
        push(n);
    }

    private Node first; // link to least recently added node

    private void push (Node n){
        Node current;

        // Special case for the first head node ever
        if(first == null){
            first = n;
        }

        else if (first.data >= n.data){
            n.next = first;
            first = n;
        }
        else {
            // Go to the node before point of insertion.
            current = first;

            while (current.next != null && current.next.data < n.data)
                current = current.next;
                n.next = current.next;
                current.next = n;
        }

        System.out.println(toString());
    }

    public String toString(){
        Node print = first;
        StringBuilder str = new StringBuilder();
        while(print != null){
            str.append("[");
            str.append(print.data);
            str.append("]");
            print = print.next;
        }
        return str.toString();
    }

        // deklarera
    class Node{
        int data;
        Node next;

        // konstruktor
        public Node(){
            this.next = null;
        }
    }
}
/*
The output is:
[5]
[2][5]
[2][3][5]
[2][3][5][10]
 */