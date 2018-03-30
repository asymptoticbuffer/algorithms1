import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation
{
    public static void main(String args[])
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String s;
        int howMany = Integer.parseInt(args[0]);
        while(howMany > 0){
            s = StdIn.readString();
            rq.enqueue(s);
            howMany--;
        }
        for (String result: rq){
            StdOut.println(result);
        }
    }
}
