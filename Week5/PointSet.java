import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.lang.IllegalArgumentException;
import java.util.TreeSet;
import java.util.LinkedList;


//Brute-force implementation
public class PointSET {
    private final TreeSet<Point2D> ts;
    // construct an empty set of points
    public PointSET(){
        ts = new TreeSet<>();
    }
    
    public boolean isEmpty(){
        return ts.isEmpty();
    }
    
    public int size(){
        return ts.size();
    }
    
    public void insert(Point2D p){
        if (p == null) throw new IllegalArgumentException();
        ts.add(p);
    }
    
    public boolean contains(Point2D p){
        if (p == null) throw new IllegalArgumentException();
        return ts.contains(p);
    }
    
    // draw all points to standard draw
    public void draw(){
        for (Point2D p : ts){
            p.draw();
        }
    }
    
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null) throw new IllegalArgumentException();
        LinkedList<Point2D> list = new LinkedList<>();
        for (Point2D p : ts){
            if (rect.distanceSquaredTo(p) == 0.0){
                list.add(p);
            }
        }
        return list;
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p){
        if (p == null) throw new IllegalArgumentException();
        double distance = Double.MAX_VALUE;
        Point2D ans = null;
        for (Point2D t : ts){
            double dis = p.distanceSquaredTo(t);
            if (dis <= distance || ans == null){
                distance = dis;
                ans = t;
            }
        }
        return ans;
    }
}
