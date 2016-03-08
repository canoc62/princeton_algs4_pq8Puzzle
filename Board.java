import java.util.*;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
    
    private int[][] blocks;
    private int[][] twinBlocks;
    private int N;
    private int blockNumber;
    private int hammingCount;
    private int manhattanCount;
    private final ArrayList<Board> neighbors = new ArrayList<Board>();
    
    private int tileNumber(int i, int j) {
        return (i*N) + j;
    }
    
    private int computeManhattanI(int tileNum){
        return tileNum/N;
    }
    
    private int computeManhattanJ(int tileNum) {
        int manhattanJ = tileNum - (computeManhattanI(tileNum)*N);
        return manhattanJ; 
    }
    
    private int computeManhattanCount(int tileNum, int i, int j) {
        int iDifference = Math.abs(i - computeManhattanI(tileNum));
        int jDifference = Math.abs(j - computeManhattanJ(tileNum));
        return iDifference + jDifference;
    }
    
    private void copyBlocks(int[][] blocks, int[][] twin) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                twin[i][j] = blocks[i][j];
            }
        }
    }
    
    public Board(int[][] blocks) {
        this.blocks = blocks; 
        N = this.blocks[1].length;
        twinBlocks = new int[N][N];
        copyBlocks(this.blocks, twinBlocks);
    }
    
    public int dimension() {
        return N;
    }
    
    public int hamming() {
        hammingCount = 0;
        
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if(this.blocks[i][j] != tileNumber(i,j)) {
                    hammingCount++;
                }
            }
        }
        return hammingCount;
    }
    
    public int manhattan() {
        manhattanCount = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int tile = this.blocks[i][j];
                if(tile != 0 && tile != tileNumber(i,j)) {
                    manhattanCount += computeManhattanCount(tile,i,j);
                }
            }
        }
        return manhattanCount;
    }
    
    public boolean isGoal() {
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if(this.blocks[i][j] != tileNumber(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Board twin() {
        //twin = new int[N][N];
        //copyBoard(board, twin);
        int i = StdRandom.uniform(0,N);
        int j = StdRandom.uniform(0,N);
        int i2 = StdRandom.uniform(0,N);
        int j2 = StdRandom.uniform(0,N);
        if (twinBlocks[i][j] == 0 || twinBlocks[i2][j2] == 0) {
            return twin();
        }
        else {
            int temp = twinBlocks[i][j];
            twinBlocks[i][j] = twinBlocks[i2][j2];
            twinBlocks[i2][j2] = temp;
        }
        Board twinBoard = new Board(twinBlocks);
        return twinBoard; 
    }
    
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) return false;
        if (this.hamming() != that.hamming()) return false;
        if (this.manhattan() != that.manhattan()) return false;
        if (!Arrays.deepEquals(this.blocks,that.blocks)) return false;
        /*for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
         if (this[i][j] != that[i][j]) {
         return false;
         }
         }
         }*/
        //if (this.twin() != that.twin()) return false;
        return true;
    }
    
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighborsCopy = new ArrayList<Board>(neighbors);
        return neighborsCopy;
    }
    
    public String toString() {
        String boardRepresentation = "" + N;
        for (int i = 0; i < N; i++) {
            boardRepresentation += "\n";
            for(int j = 0; j <N; j++) {
                boardRepresentation += " " + this.blocks[i][j];
            }
        }
        return boardRepresentation;
    }
    
    
    
    
    public static void main(String[] args) {
        
    }
    
    
    
}