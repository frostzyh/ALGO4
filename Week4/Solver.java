import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.MinPQ;
import java.util.LinkedList;

public class Solver {
    /*
     * Problem of W4.001.zip: 
     *  1. Takes too long to solve problems.
     *  Improvement: use one Priority Queue instead of two. Add initial board and its twin in the same PQ.
     */
    private boolean isSolvable;
    private LinkedList<Board> solution;
    private int moves;
    // Initial search node (initial board, 0 moves, null previous search node). Add to priority queue.
    public Solver(Board initial){           // find a solution to the initial board (using the A* algorithm)
        
        if (initial == null) throw new IllegalArgumentException("Null Board detected");
        
        isSolvable = true;
        
        MinPQ<Node> pq = new MinPQ<>();
        
        pq.insert(new Node(initial, null));
        pq.insert(new Node(initial.twin(),null));
        //Find Goal
        Node n_a = pq.delMin();
        while (!n_a.isGoal()){
            Iterable<Board> ib = n_a.b.neighbors();
            
            if (n_a.p != null){
                Board prevSerchBoard = n_a.p.b;
                for (Board tbb:ib){
                    if (!tbb.equals(prevSerchBoard))
                        pq.insert(new Node(tbb, n_a));
                }
            }
            else{
                for (Board tbb:ib)
                        pq.insert(new Node(tbb, n_a));
            }
            
            n_a = pq.delMin();
        }
        
        // Go from goal to initial
        moves = 0;
        solution = new LinkedList<>();
        solution.add(n_a.b);
        while(n_a.p != null){
            moves++;
            n_a = n_a.p;
            solution.addFirst(n_a.b);
        }
        
        // now n_a is the initial
        // If n_a is not the initial board...
        if (!n_a.b.equals(initial)){
            isSolvable = false;
            solution = null;
            moves = -1;
        }
    }
    
    public boolean isSolvable(){            // is the initial board solvable?
        return isSolvable;
    }
    public int moves(){                     // min number of moves to solve initial board; -1 if unsolvable
        return moves;
    }
    public Iterable<Board> solution(){      // sequence of boards in a shortest solution; null if unsolvable
        return solution;
    }
    
    private class Node implements Comparable<Node>{
        private Board b; // current board
        private Node p; // previous search node
        int moves; // number of moves
        int priority;
        
        public Node(Board b, Node p){
            this.b = b;
            this.p = p;
            if (p == null) moves = 0;
            else this.moves = p.moves + 1;
            this.priority = this.moves + b.manhattan();
        }
        
        public boolean isGoal(){
            return b.isGoal();
        }
        
        //@Override
        public int compareTo(Node c){
            return this.priority - c.priority;
        }
    }
}