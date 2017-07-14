import java.lang.StringBuilder;
public class Board {
    private int[][] b;
    public Board(int[][] blocks){           // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        int l = blocks.length;
        b = new int[l][l];
        for (int i = 0; i< l; i++)
            for (int j=0; j<l; j++)
            b[i][j] = blocks[i][j];
    }
    
    // board dimension n
    public int dimension(){                 

    }
    
    // number of blocks out of place
    public int hamming(){                   

        
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan()    {             

    }
    
    // is this board the goal board?
    public boolean isGoal(){                

    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin(){                    

    }
    
    // does this board equal y?
    public boolean equals(Object y)   {     

    }
    
    // all neighboring boards
    public Iterable<Board> neighbors(){     

    }
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(b.length + "\n");
        for(int i=0; i < blocks.length;i++){
            for (int j=0; j < blocks.length; j++){
                sb.append(" " + blocks[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }