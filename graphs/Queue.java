/*
 Author: Keying Liang
 Generated on: 2019-09-05
 Updated on: 2019-09-09

This function allows user to add or remove items in a generic iterable FIFO-queue t
hat is based on a double linked list. It will print the content of the list after
each insertion/deletion of an element.

API:
enqueue: add items to the queue
dequeue: remove items from the queue
 */
import java.util.Iterator;

public class Queue <Item> implements Iterable <Item> {
    private Node first; // link to least recently added node. change place when pushing
    private Node last;  // link to most recently added node. change place when pop
    private int n;      //number of elements in the queue

    class Node{					//declare Node
        Item data;
        Node next;

        public Node() {			//constructors
            this.next = null;
        }
    }

    public Queue(){
        first = null;
        last = null;
        n = 0;
    }


    public void enqueue (Item c) {	//API. add an item
        Node n = new Node ();
        n.data = c;
        push(n);
        //System.out.println(toString());
    }

    public Item dequeue() {					//API. remove the least recently added item
        Node n = pop();
        //System.out.println(toString());
        return n.data;
    }


    private void push (Node node) {
        if(first == null){
            first = node;
            last = node;
        }

        else {
            last.next = node;
            //node.prev = last;
            last = node;
        }
        n++;
    }

    private Node pop () {
        Node node = first;				//save the node to return, before we pop it
        //try{
            first = first.next;
        //}
        //catch(Exception e){System.out.println("Queue is empty");}
        n--;
        return node;
    }


    public int size() {
        return n;
    }

    public boolean isEmpty(){
        return first == null;
    }

    //create iterator (iteratorobjekt), go through the list without changing any thing
    public Iterator <Item> iterator (){
        return new ListIterator();
    }

    public class ListIterator implements Iterator <Item>{
        private Node head;

        public ListIterator() {
            this.head = first;
        }

        public boolean hasNext() {
            if(head != null) {
                return true;
            }
            else {
                return false;
            }
        }

        public Item next() {
            if(hasNext()==true) {
                Node r = head;
                head = head.next;
                return r.data;
            }
            return null;
        }
    }

}

