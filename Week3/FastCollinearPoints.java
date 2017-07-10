import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints{
    
    private ArrayList<LineSegment> ls;
    
    public FastCollinearPoints(Point[] points){
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
        Point[] arr = pts.clone();
        for(int p = 0; p < pts.length-3; p++){
            // two sorts. The output would be -1,..,-1,0,...,0,1,...,1. We want to find 0s.
            Arrays.sort(arr);
            Arrays.sort(arr,pts[p].slopeOrder());
            int start = 1, end = 2;
            while (end < arr.length){
                while (end < arr.length && 
                       arr[0].slopeOrder().compare(arr[start],arr[end]) == 0)
                    //end < arr.length has to be placed before second condition.
                    end++;  // find the max length of segments
                // 3 or more points and The arr[0] is the starting point of segment
                if (end - start >= 3 && arr[0].compareTo(arr[start]) < 0){
                    ls.add(new LineSegment(arr[0],arr[end-1]));
                }
                start = end;
                end++;
            }
        }
    }
    
    public int numberOfSegments(){
        return ls.size();
    }
    
    public LineSegment[] segments(){
        return ls.toArray(new LineSegment[ls.size()]);
    }
    
    
    public static void main(String[] args){
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
        
        /*
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        collinear.segments();
        */
        
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}