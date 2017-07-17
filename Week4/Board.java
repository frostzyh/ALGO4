import java.lang.StringBuilder;
import java.util.LinkedList;

public class Board {
    private final int[][] b;
    private final int n; // length
    private Integer md;
    private Integer hd;
    private final int emp_x;
    private final int emp_y;
    
    public Board(int[][] blocks){           // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        md = null;
        hd = null;
        
        n = blocks.length;
        b = new int[n][n];
        
        int e_x = -1;
        int e_y = -1;
        main_loop:
        for (int i = 0; i< n; i++){
            for (int j=0; j<n; j++){
                b[i][j] = blocks[i][j];
                if (b[i][j] == 0){
                    e_x = i;
                    e_y = j;
                }
            }
        }
        emp_x = e_x;
        emp_y = e_y;
        
    }
    
    // board dimension n
    public int dimension(){
        return n;
    }
    
    // number of blocks out of place
    public int hamming(){
        if (hd != null) return hd;
        int num = 0;
        int ct = 0;
        for (int i = 0; i< n; i++){
            for (int j=0; j<n; j++){
                ct++;
                if (b[i][j] == 0) continue;
                if (b[i][j] != ct) num++;
                
            }
        }
        hd = num;
        return hd;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan(){
        if (md != null) return md;
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
        md = dis;
        return md;
    }
    
    
    // is this board the goal board?
    public boolean isGoal(){
        return hamming() == 0;
    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin(){          
        int[][] temp = new int[n][n];
        
        int p1 = -1;
        int p2 = -1;
        //Deep copy
        for (int i =0; i < n; i++){
            for (int j=0; j< n; j++){
                temp[i][j] = b[i][j];
            }
        }
        
        for(int i=0; i<n; i++){
            if (b[0][i] != 0){
                p1 = i;
                break;
            }
        }
        
        for(int i=0; i<n; i++){
            if (b[1][i] != 0){
                p2 = i;
                break;
            }
        }
        
        int pp = temp[0][p1];
        temp[0][p1] = temp[1][p2];
        temp[1][p2] = pp;
        return new Board(temp);
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
        LinkedList<Board> queue = new LinkedList<Board>();
        boolean b_left = true; // switch with left
        boolean b_right = true; // switch with right
        boolean b_top = true; // switch with top
        boolean b_bot = true; // switch with bot
        if(emp_x == 0) b_left = false;
        if (emp_x == n-1) b_right = false;
        if (emp_y == 0) b_top = false;
        if (emp_y == n-1) b_bot = false;
        
        int [][] temp;
        if (b_left){
            temp = new int[n][n];
            for (int i =0; i < n; i++){
                for (int j=0; j< n; j++){
                    temp[i][j] = b[i][j];
                }
            }
            
            temp[emp_x][emp_y] = temp[emp_x][emp_y-1];
            temp[emp_x][emp_y-1] = 0;
            queue.add(new Board(temp));
        }
        if (b_right){
            temp = new int[n][n];
            for (int i =0; i < n; i++){
                for (int j=0; j< n; j++){
                    temp[i][j] = b[i][j];
                }
            }
            temp[emp_x][emp_y] = temp[emp_x][emp_y+1];
            temp[emp_x][emp_y+1] = 0;
            queue.add(new Board(temp));
        }
        if (b_top){
            temp = new int[n][n];
            for (int i =0; i < n; i++){
                for (int j=0; j< n; j++){
                    temp[i][j] = b[i][j];
                }
            }
            temp[emp_x][emp_y] = temp[emp_x-1][emp_y];
            temp[emp_x-1][emp_y] = 0;
            queue.add(new Board(temp));
        }        
        if (b_bot){
            temp = new int[n][n];
            for (int i =0; i < n; i++){
                for (int j=0; j< n; j++){
                    temp[i][j] = b[i][j];
                }
            }
            temp[emp_x][emp_y] = temp[emp_x+1][emp_y];
            temp[emp_x+1][emp_y] = 0;
            queue.add(new Board(temp));
        }
        return queue;
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