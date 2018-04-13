import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints
{
    private final ArrayList<LineSegment> arrayLineSegments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        if (points == null)
            throw new NullPointerException();

        Point[] auxPoints = points.clone();
        Arrays.sort(auxPoints);

        if (hasDuplicate(auxPoints)) {
            throw new IllegalArgumentException("U have duplicate points");
        }

        for (int first = 0; first < auxPoints.length - 3; first++) {
            for (int second = first + 1; second < auxPoints.length - 2; second++) {
                double slopeFS = auxPoints[first].slopeTo(auxPoints[second]);
                for (int third = second + 1; third < auxPoints.length - 1; third++) {
                    double slopeFT = auxPoints[first].slopeTo(auxPoints[third]);
                    if (Double.compare(slopeFS, slopeFT) == 0) {
                        for (int forth = third + 1; forth < auxPoints.length; forth++) {
                            double slopeFF = auxPoints[first].slopeTo(auxPoints[forth]);
                            if (Double.compare(slopeFS, slopeFF) == 0) {
                                arrayLineSegments.add(new LineSegment(auxPoints[first], auxPoints[forth]));
                            }
                        }
                    }
                }

            }
        }
    }
    
    private boolean hasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;

    }
    
    // the number of line segments
    public int numberOfSegments() {
        return arrayLineSegments.size();
    }
    
    // the line segments
    public LineSegment[] segments() {
        return arrayLineSegments.toArray(new LineSegment[arrayLineSegments.size()]);
    }
}
