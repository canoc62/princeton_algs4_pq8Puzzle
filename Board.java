import java.util.*;
import edu.princeton.cs.algs4.MinPQ;

public class Board {
    
    private int[][] board;
    private int dimensionSize;
    private int blockNumber;
    private int hammingCount;
    private int manhattanCount;
    
    private int tileNumber(int i, int j) {
        return (i*dimensionSize) + j;
    }
    
    private int computeManhattanI(int tileNum){
        return tileNum/dimensionSize;
    }
    
    private int computeManhattanJ(int tileNum) {
        int manhattanJ = tileNum - (computeManhattanI(tileNum)*dimensionSize);
        return manhattanJ; 
    }
    
    private int computeManhattanCount(int tileNum, int i, int j) {
        int iDifference = Math.abs(i - computeManhattanI(tileNum));
        int jDifference = Math.abs(j - computeManhattanJ(tileNum));
        return iDifference + jDifference;
    }
    
    public Board(int[][] blocks) {
        board = blocks; 
        dimensionSize = blocks[1].length;
    }
    
    public int dimensions() {
        return dimensionSize;
    }
    
    public int hamming() {
        hammingCount = 0;
        
        for (int i = 0; i < dimensionSize; i++) {
            for (int j = 0; j < dimensionSize; j++) {
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
                if(board[i][j] != tileNumber(i,j)) {
                    manhattanCount += computeManhattanCount(board[i][j],i,j);
                }
            }
        }
        return manhattanCount;
    }
    
    public boolean isGoal() {
        
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