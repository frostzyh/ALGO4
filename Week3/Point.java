import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;


public class Point implements Comparable<Point>{
    private final int x; // Immutable
    private final int y; 
    // Construct the point (x,y)
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Draws this point
    public void draw(){
        StdDraw.point(x,y);
    }
    
    // Draws the line segment from this point to that point.
    public void drawTo(Point that){
        StdDraw.line(this.x,this.y,that.x,that.y);
    }
    
    // string representation
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    // Comparable method
    public int compareTo(Point that){
        if (this.y < that.y) return -1;
        if (this.y > that.y) return 1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return 1;
        return 0;
    }
    public double slopeTo(Point that){
        if (this.x == that.x && this.y == that.y)
            return Double.NEGATIVE_INFINITY;
        if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        double slope = (double)(that.y - this.y)/ (that.x - this.x);
        return slope == 0 ? 0.0: slope;
    }
    
    public Comparator<Point> slopeOrder(){
        return new SlopeOrder();
    }
    
    private class SlopeOrder implements Comparator<Point>{
        public int compare(Point p1, Point p2){
            double diff = slopeTo(p1) - slopeTo(p2);
            if (diff == 0) return 0;
            return diff < 0 ? -1: 1;
        }
    }
 
    public static void main(String[] args){
//        //Test slopeTo
        Point p1 = new Point(4,4);
        Point p2 = new Point(3,4);
//        StdOut.println(p1.slopeTo(p2));
        // Test SlopeOrder
        Point p3 = new Point(5,5);
        StdOut.println(p3.slopeOrder().compare(p2,p1));
    }
    
    
}