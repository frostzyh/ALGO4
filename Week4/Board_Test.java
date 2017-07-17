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
        Board ini = new Board(blocks);
        StdOut.println(ini);
        StdOut.println("Manhattan distance: " + ini.manhattan());
        StdOut.println("Hamming distance: " + ini.hamming());
        StdOut.println("Is Goal?: " + ini.isGoal());
        StdOut.println("Twin: \n" + ini.twin());
        StdOut.println("equals: " + ini.equals(new Board(blocks)));
        StdOut.println("Neighbors:\n");
        for (Board b: ini.neighbors()){
            StdOut.println(b);
        }
    }
}