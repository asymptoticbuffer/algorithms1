import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> arrayLineSegments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points)
    {
        checkNull(points);
        checkDuplicates(points);
		Point[] auxPoints = points.clone();
        for (int i = 0; i < auxPoints.length - 3; i++) {
            Arrays.sort(auxPoints);
            Arrays.sort(auxPoints, auxPoints[i].slopeOrder());


            for (int p = 0, first = 1, last = 2; last < auxPoints.length; last++) {
                while (last < auxPoints.length && Double.compare(auxPoints[p].slopeTo(auxPoints[first]), auxPoints[p].slopeTo(auxPoints[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && auxPoints[p].compareTo(auxPoints[first]) < 0) {
                    arrayLineSegments.add(new LineSegment(auxPoints[p], auxPoints[last - 1]));
                }
                first = last;
            }
        }
    }

    public int numberOfSegments()
    {
        return arrayLineSegments.size();
    }
    public LineSegment[] segments()
    {
        return arrayLineSegments.toArray(new LineSegment[arrayLineSegments.size()]);
    }

    private void checkNull(Point[] points)
    {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++){
                if (points[i] == null) {
                    throw new IllegalArgumentException();
                }
            }
        }
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
        Point[] pts = {
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0),
            new Point(3, 0),
            new Point(4, 0),
            new Point(5, 0),
            new Point(2, 2),
            new Point(3, 3),
            new Point(1, 1),
            new Point(4, 3),
            new Point(5, 3),
            new Point(7, 3),

        };

        FastCollinearPoints fcp = new FastCollinearPoints(pts);
        StdOut.print("number of segments: ");
        StdOut.print(fcp.numberOfSegments());
        StdOut.print("\n");
    }
}
