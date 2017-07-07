import java.lang.IllegalArgumentException;

public class BruteCollinearPoints{
    int nos;
    public BruteCollinearPoints(Point[] points){
        if (points == null) throw new IllegalArgumentException();
        for (int i=0; i < points.length; i++){
            if (points[i] == null) throw new IllegalArgumentException();
        }
        // if we have repeated points, 
    }
    
    public int numberOfSegments(){
        
    }
    
    public LineSegment[] segments(){
        LineSegment[] ls = new LineSegment[];
        for(int p = 0; p < points.length-3; p++){
            for (int q = p + 1; q < points.length-2; q++){
                for (int r = q +1; r < points.length-1; r++){
                    for (int s = r +1; s < points.length; s++){
                        double s1 = points[p].slopeTo(points[q]);
                        double s2 = points[q].slopeTo(point[r]);
                        if (s1 == s2){
                            double s3 = points[r].slopeTo(point[s]);
                        }
                        
                        if (s1 == s2 && s2 == s3)
                            new LineSegment(points[p],points[s]);
                    }
                }
            }
        }
    }
}