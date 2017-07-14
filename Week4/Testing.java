import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.lang.StringBuilder;

public class Testing{
    
    private final int[][] blocks;
    public Testing(int[][] b){
        int l = b.length;
        blocks = new int[l][l];
        for (int i = 0; i< l; i++)
            for (int j=0; j<l; j++)
            blocks[i][j] = b[i][j];
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(blocks.length + "\n");
        for(int i=0; i < blocks.length;i++){
            for (int j=0; j < blocks.length; j++){
                sb.append(" " + blocks[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public static void main(String[] args){
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i< n; i++)
            for (int j=0; j<n; j++)
            blocks[i][j] = in.readInt();
        Testing initial = new Testing(blocks);
        
        StdOut.print(initial);
        
        blocks[2][2] = 9;
        StdOut.println();
        StdOut.print(initial);
    }
}