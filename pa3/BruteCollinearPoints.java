import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints
{
    private int numSegments = 0;
    private LineSegment[] arrayLineSegments;

    public BruteCollinearPoints(Point[] points)
    {
        if (points == null ) throw new IllegalArgumentException();
        checkDuplicates(points);
        // initialize arrayLineSegments with # points size
        arrayLineSegments = new LineSegment[points.length];

        for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++){
                for (int k = j + 1; k < points.length; k++){
                    for (int l = k + 1; l < points.length; l++){
                        Point point1 = points[i];
                        Point point2 = points[j];
                        Point point3 = points[k];
                        Point point4 = points[l];
                        if (point1.slopeTo(point2) == point1.slopeTo(point3) &&
                            point1.slopeTo(point2) == point1.slopeTo(point4)){
                            Point[] linePoints = { point1, point2, point3, point4 };
                            Arrays.sort(linePoints);
                            if (numSegments == arrayLineSegments.length) resize(numSegments + 1);
                            arrayLineSegments[numSegments++] = new LineSegment(linePoints[0], linePoints[3]);
                        }
                    }

                }
            }
        }
        if (points == null) throw new IllegalArgumentException();
    }

    public int numberOfSegments()
    {
        return numSegments;
    }

    public LineSegment[] segments()
    {
        return arrayLineSegments;
    }

    private void resize(int n)
    {
        LineSegment[] newArrLineSeg = new LineSegment[n];
        for (int i = 0; i < arrayLineSegments.length; i++){
            newArrLineSeg[i] = arrayLineSegments[i];
        }

        arrayLineSegments = newArrLineSeg;
    }

    private void checkDuplicates(Point[] points)
    {
        for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++){
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

    }

    // Some unit testing
    public static void main (String[] args)
    {
        try {
            new BruteCollinearPoints(null);
        }
        catch (IllegalArgumentException exception){
        }

        try {
            Point[] equalPoints = { new Point(0, 0), new Point(0, 0) };
            new BruteCollinearPoints(equalPoints);
        }
        catch (IllegalArgumentException exception){
        }


        Point[] pts = {
            new Point(1, 2),
            new Point(1, 4),
            new Point(1, 1),
            new Point(1, 6),

            new Point(2, 4),
            new Point(-2, -4),
            new Point(0, 0),

            new Point(0, 4),
            new Point(0, -4),
            new Point(0, 10),

        };

        BruteCollinearPoints bcp = new BruteCollinearPoints(pts);
        assert bcp.numberOfSegments() == 3;
        LineSegment[] expected = { 
            new LineSegment(new Point(1, 1), new Point(1, 6)),
            new LineSegment(new Point(-2, -4), new Point(2, 4))
        };

        //assert bcp.segments()[1].toString() == expected[1].toString();
        StdOut.print( bcp.segments()[0].toString());
        StdOut.print(expected[0].toString());
    }
}
