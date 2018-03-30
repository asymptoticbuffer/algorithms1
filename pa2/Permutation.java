import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation
{
    public static void main(String args[])
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String s;
        int howMany = Integer.parseInt(args[0]);
        while(!StdIn.isEmpty())
        {
            s = StdIn.readString();
            rq.enqueue(s);
        }
        while(howMany > 0)
        {
            StdOut.println(rq.dequeue());
            howMany--;
        }
    }
}
