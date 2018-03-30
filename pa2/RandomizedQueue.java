import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private int front;
    private int n;
    private Item[] a;

    public RandomizedQueue()
    { 
        a = (Item[]) new Object[1];
        n = 0;
        front = -1;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public int size()
    {
        return n;
    }

    public void enqueue(Item item)
    {
        /*
         * if front is 0, then resizes to 2N
         * updates front to front -1
         * adds an element in front
         * update N
         */
        if (item == null) throw new IllegalArgumentException();
        if (front == 0)
        {
            resize(2 * n);
        }
        else if (front == -1)
        {
            front = 0;
            a[0] = item;
            n++;
            return;
        }
        a[--front] = item;
        n++;
    }

    public Item dequeue()
    {
        /*
         * update end to end -1
         * save the value of a[end]
         * avoid loitering: set a[end] to null
         * if N == a.length / 4, then resize to N/2
         * output the value previously saved
         */
        // to do: if N == 0, raise exception
        int end = front + n - 1;
        int randomIndex;
        if (front != n)
        {
            randomIndex = StdRandom.uniform(front, n);
        }
        else
        {
            randomIndex = front;
        }
        Item tmp = a[randomIndex];
        a[randomIndex] = a[end];
        a[end] = tmp;
        Item res = a[end];
        a[end] = null; // avoid loitering
        --n;
        if (n == 0) front = -1;
        if ( n > 0 && n == a.length / 4) resize(a.length / 2);
        return res;
    }

    public Item sample(){
        if (n == 0){
            throw new NoSuchElementException();
        }

        int r = StdRandom.uniform(front, front + n);
        return a[r];
    }

    private void resize(int newSize)
    {
        Item[] newA = (Item[]) new Object[newSize];
        int newFront = newSize - n;
        for (int k = 0; k < n; k++){
            newA[newFront + k] = a[k + front];
        }
        front = newFront;
        a = newA;
    }

    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterable();
    }


    private class RandomizedQueueIterable implements Iterator<Item>
    {
        private int i;
        private Item[] popSequence;

        public RandomizedQueueIterable(){
            // copy array a into a new one (since it will be modified)
            // shuffle those values and initialize i to 0
            popSequence = (Item[]) new Object[n];
            System.arraycopy(a, front, popSequence, 0, n);
            StdRandom.shuffle(popSequence);
            i = n - 1;
        }

        public boolean hasNext() { return i > -1; }

        public Item next()
        {
            if (i == -1)
            {
                throw new NoSuchElementException();
            }
            Item res = popSequence[i];
            popSequence[i--] = null;
            return res;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args){
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        //RandomizedQueue<String> rq = new RandomizedQueue<String>();
        /*
        String  seq[] = {"Blas", "Tinto", "Mecha", "Nacho"};
        for (int i = 0; i < seq.length; i++)
        {
            rq.enqueue(seq[i]);
        }
        for (String s: rq)
        {
            StdOut.println(s);
        }
        StdOut.println("New iterator");
        for (String s: rq)
        {
            StdOut.println(s);
        }
        */
        rq.enqueue(2);
        rq.enqueue(0);
        rq.enqueue(1);
        StdOut.println(rq.sample());
        StdOut.println(rq.sample());
        StdOut.println(rq.sample());
    }
}
