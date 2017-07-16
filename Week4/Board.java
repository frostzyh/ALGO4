import java.lang.StringBuilder;
public class Board {
    private int[][] b;
    private final int n;
    public Board(int[][] blocks){           // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        n = blocks.length;
        b = new int[n][n];
        for (int i = 0; i< n; i++)
            for (int j=0; j<n; j++)
            b[i][j] = blocks[i][j];
    }
    
    // board dimension n
    public int dimension(){
        return n;
    }
    
    // number of blocks out of place
    public int hamming(){
        int num = 0;
        int ct = 0;
        for (int i = 0; i< n; i++){
            for (int j=0; j<n; j++){
                ct++;
                if (b[i][j] == 0) continue;
                if (b[i][j] != ct) num++;
                
            }
        }
        return num;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan(){
        int dis = 0;
        for (int i = 0; i < n; i++){
            for (int j=0; j<n; j++){
                int num = b[i][j];
                if (num == 0) continue;
                if (num % n == 0){
                    dis += Math.abs(n-1-j) + Math.abs(num/n -1 -i);
                    continue;
                }
                int hor = Math.abs(num % n -1 - j);
                int ver = Math.abs(num/n - i);
                dis += hor + ver;
            }
        }
        return dis;
    }
    
    
    // is this board the goal board?
    public boolean isGoal(){
        return hamming() == 0;
    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin(){          
        
    }
    
    // does this board equal y?
    public boolean equals(Object y)   {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return that.toString().equals(this.toString());
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors(){
        
    }
    
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(n + "\n");
        for(int i=0; i < n;i++){
            for (int j=0; j < n; j++){
                //sb.append(" " + blocks[i][j] + " ");
                sb.append(String.format("%2d ", b[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}