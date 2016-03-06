import java.util.*;
import edu.princeton.cs.algs4.MinPQ;

public class Board {
    
    private int[][] board;
    private int N;
    private int blockNumber;
    private int hammingCount;
    private int manhattanCount;
    
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
    
    public Board(int[][] blocks) {
        board = blocks; 
        N = blocks[1].length;
    }
    
    public int dimension() {
        return N;
    }
    
    public int hamming() {
        hammingCount = 0;
        
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if(board[i][j] != tileNumber(i,j)) {
                    hammingCount++;
                }
            }
        }
        return hammingCount;
    }
    
    public int manhattan() {
        manhattanCount = 0;
        
        for (int i = 0; i < dimensionSize; i++) {
            for (int j = 0; j < dimensionSize; j++) {
                int tile = board[i][j];
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
                if(board[i][j] != tileNumber(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Board twin() {
        
    }
    
    public boolean equals(Object y) {
        
    }
    
    public Iterable<Board> neighbors() {
        
    }
    
    public String toString() {
        
    }
    
    
    
    
    public static void main(String[] args) {
        
    }
                                        
                                      
    
}