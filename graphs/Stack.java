/*
 Author: Keying Liang
 Generated on: 2019-09-05
 Last updated: 2019-10-08

 This function reads characters from stdin until a newline character is read, and prints them to stdout in reverse order,
 using stack as ADT.

 API: pushEl:push elements in stack, by callling private push.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
    private class Node{       //declare Node
        Item data;
        Node next;

        public Node(){          //constructor
            next = null;
        }
    }

    private Node first = null;        //Node first should link to the first node/element in stack

    public void pushEl (Item c){     //API
        Node n = new Node ();
        n.data = c;
        push(n);
    }

    public Item popEl(){              //API
        Node n = pop();
        return n.data;
    }

    private void push (Node n){         //push elements in stack
        n.next = first;
        first = n;
    }

    private Node pop(){                 //pop elements from stack
        Node n = first;
        try{
            first = n.next;
        }                                 //catch exception if the sstack is empty and there's nothing to pop
        catch(Exception e){System.out.println("Stack is empty");}
        return n;
    }

    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator{
        private Node current;
        public ListIterator(Node first){
            current = first;
        }
        @Override  //override superklassen Iterator
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext())
                throw new NoSuchElementException();
            Item data = current.data;
            current = current.next;
            return data;
        }
    }
}