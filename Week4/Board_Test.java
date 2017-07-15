import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


// Test: java Board_Test filename.txt
public class Board_Test{
    public static void main(String[] args){
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i< n; i++)
            for (int j=0; j<n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        StdOut.println(initial);
        StdOut.println("Manhattan distance: " + initial.manhattan());
        StdOut.println("Hamming distance: " + initial.hamming());
        
    }
}