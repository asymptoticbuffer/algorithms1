import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>
{
    private int N;
    private int capacity;
    private Node<Item> front;
    private Node<Item> end;

    private class Node<Item>
    {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    public Deque()
    {
        int N = 0;
        Node<Item> front = null;
        Node<Item> end = null;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size()
    {
        return N;
    }

    public void addFirst(Item item)
    {
        // add the item to the front
        Node<Item> newNode = new Node<Item>();
        newNode.item = item;
        newNode.next = null;
        newNode.previous = null;
        if (isEmpty()){
            front = newNode;
            end = front;
            ++N;
            return;
        }
        Node<Item> oldFront = front;
        front = newNode;
        front.next = oldFront;
        oldFront.previous = front;
        N++;
    }

    public void addLast(Item item)
    {
        Node<Item> newNode = new Node<Item>();
        newNode.item = item;
        newNode.next = null;
        newNode.previous = null;
        if (isEmpty()){
            end = newNode;
            front = end;
            N++;
            return;
        }
        newNode.previous = end;
        end.next = newNode;
        end = end.next;
        N++;
    }

    public Item removeFirst()
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item oldFrontItem = front.item;
        if (N == 1) {
            end = null;
            front = null;
        }
        else if (N == 2) {
            front = front.next;
            front.previous = null;
            end = front;
        }
        else {
            front = front.next;
            front.previous = null;
        }
        --N;
        return oldFrontItem;
    }

    public Item removeLast()
    {
        if (N == 0) {
            throw new NoSuchElementException();
        }

        Item oldEndItem = end.item;
        if (N == 1) {
            end = null;
            front = null;
        }
        else if (N == 2) {
            end = end.previous;
            end.next = null;
            front = end;
        }
        else {
            end = end.previous;
            end.next = null;
        }

        --N;
        return oldEndItem;


    }

    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item>
    {
        private Node<Item> iteratorFront = front;
        public boolean hasNext() {return N > 0;}
        public Item  next()
        {
            Item oldFrontItem = removeFirst();
            //Item oldFrontItem = removeLast();
            return oldFrontItem;
        }
        public void remove() {}
    }

    public static void main(String[] args)
    {
        Deque<String> dq = new Deque<String>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            dq.addFirst(item);
        }
        for (String s: dq){
            StdOut.println(s);
        }
    }


}
