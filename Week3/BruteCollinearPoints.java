import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints{
    //Remember to set private for instance variables to fit API.
    
    private ArrayList<LineSegment> ls;
    
    public BruteCollinearPoints(Point[] points){
        if (points == null) throw new IllegalArgumentException("The input argument is null");
        for (int i=0; i < points.length; i++){
            if (points[i] == null) throw new IllegalArgumentException("There's null in the array");
        }
        // if we have repeated points
        Point[] pts = points.clone();
        Arrays.sort(pts);
        for (int i=1; i < pts.length; i++){
            if (pts[i].compareTo(pts[i-1]) == 0)
                    throw new IllegalArgumentException("repeated point found in array");
        }
        
        ls = new ArrayList<>();
        boolean foundSeg = false;
        for(int p = 0; p < pts.length-3; p++){
            for (int q = p + 1; q < pts.length-2; q++){
                for (int r = q +1; r < pts.length-1; r++){
                    double s1 = pts[p].slopeTo(pts[q]);
                    double s2 = pts[q].slopeTo(pts[r]);
                    if (Double.compare(s1,s2)==0){
                        for (int s = r +1; s < pts.length; s++){
                            double s3 = pts[r].slopeTo(pts[s]);
                            if (Double.compare(s1,s3)==0){// Since the array was sorted, no need to concern orders.
                                ls.add(new LineSegment(pts[p],pts[s]));
                                foundSeg = true;
                            }
                            if (foundSeg) break;
                        }
                    }
                    if (foundSeg) break;
                }
                if (foundSeg) foundSeg = false;
            }
            if (foundSeg) foundSeg = false;
        }
    }
    
    public int numberOfSegments(){
        return ls.size();
    }
    
    public LineSegment[] segments(){
        return ls.toArray(new LineSegment[ls.size()]);
    }
    
    public static void main(String[] args){
        // To execute: java BruteCollinearPoints fileName.txt
        In in = new In(args[0]);
        int n = in.readInt();
        //StdOut.println("Here is n: "+ n);
        Point[] points = new Point[n];
        for (int i = 0; i< n; i++){
            int x = in.readInt();
            int y = in.readInt();
            //StdOut.println("Here is x,y:("+ x + "," + y + ")" );
            points[i] = new Point(x,y);
        }
        
        //Draw Points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0,32768);
        StdDraw.setYscale(0,32768);
        for (Point p : points){
            p.draw();
        }
        StdDraw.show();
        StdOut.println("This part was done");
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}