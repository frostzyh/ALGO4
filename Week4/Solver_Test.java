import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


// % java Solver_Test Data/8puzzle/puzzle20.txt
public class Solver_Test{
    public static void main(String[] args){
        
        long startTime = System.currentTimeMillis();
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        
        
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Total Running Time: " + totalTime);
    }
}