import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.lang.IllegalArgumentException;
import java.util.LinkedList;

public class KdTree {
    // construct an empty set of points
    private Node root;
    private int size;
    
    private static final boolean VERT = true; // vertical
    private static final boolean HORI = false; // horizontal
    public KdTree(){
        root = null;
        size = 0;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public int size(){
        return size;
    }
    
    public void insert(Point2D p){
        if (p == null) throw new IllegalArgumentException();
        // Add root
        if (root == null){
            root = new Node(p, null);
            size++;
            return;
        }
        Node r = root;
        Node prev = null;
        Boolean goLeft = true;
        while(true){
            if (r == null){ // if we found insersion place, create a new node.
                // No need to consider childs, since r is the child.
                this.size++;
                if (goLeft) prev.lb = new Node(p, prev);
                else prev.rt = new Node(p, prev);
                return; // break;
            }
            if (isDuplicate(p, r.p)) return; // check duplicate
            prev = r;   // update prev
            if (r.isLeft(p)) {
                r = r.lb;
                goLeft = true;
            }
            else{
                r = r.rt;
                goLeft = false;
            }
        }
    }
    
    public boolean contains(Point2D p){
        if (p == null) throw new IllegalArgumentException();
        Node r = root;
        while(true){
            if (r == null) return false;
            if (isDuplicate(p, r.p)) return true;
            
            if (r.isLeft(p)) r = r.lb;
            else r = r.rt;
        }
    }
    
    private boolean isDuplicate(Point2D a, Point2D b){
        if (Math.abs(a.x() - b.x()) < 0.0000001 && Math.abs(a.y()-b.y()) < 0.0000001) return true;
        return false;
    }
    
    // draw all points to standard draw
    public void draw(){
        if (this.isEmpty()) return;
        drawHelper(root);
    }
    
    private void drawHelper(Node n){
        n.p.draw();
        if (n.lb != null) drawHelper(n.lb);
        if (n.rt != null) drawHelper(n.rt);
    }
    
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null) throw new IllegalArgumentException();
        LinkedList<Point2D> list = new LinkedList<>();
        rangeHelper(root, rect, list);
        return list;
    }
    
    private void rangeHelper(Node n, RectHV rect, LinkedList<Point2D> list){
        if (n == null) return;
        Point2D p = n.p;
        if (rect.contains(p)) list.add(p);
        if (rect.intersects(n.rect)){
            rangeHelper(n.lb, rect, list);
            rangeHelper(n.rt, rect, list);
        }
        else{
            if (n.direction == VERT){
                // Two conditions, p.x > r.xmax or p.x < r.xmin
                if (p.x() > rect.xmax()) rangeHelper(n.lb, rect, list);
                else rangeHelper(n.rt, rect, list);
            }
            else{
                if (p.y() > rect.ymax()) rangeHelper(n.lb, rect, list);
                else rangeHelper(n.rt, rect, list);
            }
        }
    }
    
    
    
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p){
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return null;
        return nearHelper(root, p, root.p);
    }
    
    private Point2D nearHelper(Node n, Point2D target, Point2D ans){
        if (n == null) return ans;
        
        if (target.distanceSquaredTo(n.p) < target.distanceSquaredTo(ans)) ans = n.p;
        if (n.isLeft(target)){
            ans = nearHelper(n.lb, target, ans);
            if (target.distanceSquaredTo(ans) > n.rect.distanceSquaredTo(target)){
                ans = nearHelper(n.rt, target, ans);
            }
        }
        else{
            ans = nearHelper(n.rt, target, ans);
            if (target.distanceSquaredTo(ans) > n.rect.distanceSquaredTo(target)){
                ans = nearHelper(n.lb, target, ans);
            }
        }
        return ans;
    }
    
    private static class Node {
        private final Point2D p;
        private Node lb; // left/bottom node
        private Node rt; // right/top node
        private final Node prev;
        private Boolean direction; // direction of axis-aligned rectengle
        private RectHV rect;
        
        public Node(Point2D p, Node n){
            this.p = p;
            this.lb = null;
            this.rt = null;
            this.prev = n;
            // Assign direction and Construct rectangle
            if (n == null){
                this.direction = true;
                // RectHV(double xmin, double ymin,double xmax, double ymax) 
                this.rect = new RectHV(p.x(),0.0, p.x(), 1.0);
            }
            else{
                this.direction = !n.direction;
                if (this.direction == HORI){ //
                    if (p.x() < prev.p.x()){
                        this.rect = new RectHV(0,p.y(),prev.p.x(),p.y());
                    }
                    else this.rect = new RectHV(prev.p.x(),p.y(),1,p.y());
                }
                else{
                    if (p.y() < prev.p.y()){
                        this.rect = new RectHV(p.x(),0,p.x(),prev.p.y());
                    }
                    else this.rect = new RectHV(p.x(),prev.p.y(),p.x(),1);
                }
            }
        }
        
        public boolean isLeft(Point2D t){
            if (t == null) throw new IllegalArgumentException();
            if (this.direction == VERT){
                if (t.x() < p.x()) return true;
                else return false;
            }
            else{
                if (t.y() < p.y()) return true;
                else return false;
            }
        }
    }
}
