import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>
{
    private int n;
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
        n = 0;
        front = null;
        end = null;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public int size()
    {
        return n;
    }

    public void addFirst(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> newNode = new Node<Item>();
        newNode.item = item;
        newNode.next = null;
        newNode.previous = null;
        if (isEmpty())
        {
            front = newNode;
            end = front;
            ++n;
            return;
        }
        Node<Item> oldFront = front;
        front = newNode;
        front.next = oldFront;
        oldFront.previous = front;
        n++;
    }

    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException();

        Node<Item> newNode = new Node<Item>();
        newNode.item = item;
        newNode.next = null;
        newNode.previous = null;
        if (isEmpty())
        {
            end = newNode;
            front = end;
            n++;
            return;
        }
        newNode.previous = end;
        end.next = newNode;
        end = end.next;
        n++;
    }

    public Item removeFirst()
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item oldFrontItem = front.item;
        if (n == 1) {
            end = null;
            front = null;
        }
        else if (n == 2) {
            front = front.next;
            front.previous = null;
            end = front;
        }
        else {
            front = front.next;
            front.previous = null;
        }
        --n;
        return oldFrontItem;
    }

    public Item removeLast()
    {
        if (n == 0) {
            throw new NoSuchElementException();
        }

        Item oldEndItem = end.item;
        if (n == 1) {
            end = null;
            front = null;
        }
        else if (n == 2) {
            end = end.previous;
            end.next = null;
            front = end;
        }
        else {
            end = end.previous;
            end.next = null;
        }

        --n;
        return oldEndItem;


    }

    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item>
    {
        private Node<Item> iteratorFront = front;
        public boolean hasNext() { return iteratorFront != null; }
        public Item  next()
        {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item result = iteratorFront.item;
            iteratorFront = iteratorFront.next;
            return result;
        }
        public void remove() { throw new UnsupportedOperationException(); }
    }

    public static void main(String[] args)
    {
        Deque<String> dq = new Deque<String>();
        dq.addLast("SAD");
        dq.addLast("BLAS");
        Iterator<String> it = dq.iterator();
    }
}
