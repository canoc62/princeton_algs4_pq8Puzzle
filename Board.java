import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.*;

public class Board {
    
    private int[][] blocks;
    private final int N;
    
    public Board(int[][] blocks) {
        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
        this.N = this.blocks.length;
    }
    
    public int dimension() {
        return N;
    }
    
    public int hamming() {
        int hammingCount = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(this.blocks[i][j] != tileNumber(i,j)) {
                    if (this.blocks[i][j] != 0) hammingCount++;
                }
            }
        }
        return hammingCount;
    }
    
    public int manhattan() {
        int manhattanCount = 0;
        
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
        return hamming() == 0;
    }
    
    public Board twin() {
        int[][] twinBlocks = copyBlocks(this.blocks);
       
        int i = StdRandom.uniform(N);
        int j = StdRandom.uniform(N);
        int i2 = StdRandom.uniform(N);
        int j2 = StdRandom.uniform(N);
        
        if (i == i2 && j == j2) {
            return twin();
        }
        if (twinBlocks[i][j] != 0 && twinBlocks[i][j] != 0) {
            exchangeTiles(twinBlocks, i, j, i2, j2);
        }
        else {
            return twin();
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
        return true;
    }
    
    public Iterable<Board> neighbors() {
        int zeroIndexI = 0;
        int zeroIndexJ = 0;
        boolean foundZero = false;
        ArrayList<Board> neighbors = new ArrayList<Board>(); 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j< N; j++) {
                if (this.blocks[i][j] == 0) {
                    zeroIndexI = i;
                    zeroIndexJ = j;
                    foundZero = true;
                    break;
                }                                                
            }
            if (foundZero == true) {
                break;
            }
        }
        
        if (zeroIndexI > 0) {
            int[][] neighbor1 = copyBlocks(this.blocks);
            exchangeTiles(neighbor1, zeroIndexI, zeroIndexJ, zeroIndexI-1, zeroIndexJ);
            Board neighboringBoard1 = new Board(neighbor1);
            neighbors.add(neighboringBoard1);
            neighbor1 = null;
        }
        
        if (zeroIndexI < N-1) {
            int[][] neighbor2 = copyBlocks(this.blocks);
            exchangeTiles(neighbor2, zeroIndexI, zeroIndexJ, zeroIndexI+1, zeroIndexJ);
            Board neighboringBoard2 = new Board(neighbor2);
            neighbors.add(neighboringBoard2);
            neighbor2 = null;
        }
        
        if (zeroIndexJ > 0) {
            int[][] neighbor3 = copyBlocks(this.blocks);
            exchangeTiles(neighbor3, zeroIndexI, zeroIndexJ, zeroIndexI, zeroIndexJ-1);
            Board neighboringBoard3 = new Board(neighbor3);
            neighbors.add(neighboringBoard3);
            neighbor3 = null;
        }
        
        if (zeroIndexJ < N-1) {
            int[][] neighbor4 = copyBlocks(this.blocks);
            exchangeTiles(neighbor4, zeroIndexI, zeroIndexJ, zeroIndexI, zeroIndexJ+1);
            Board neighboringBoard4 = new Board(neighbor4);
            neighbors.add(neighboringBoard4);
            neighbor4 = null;
        }
        
        return neighbors;
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
    
    private int tileNumber(int i, int j) {
        //Add one to adjust for fact that the goal tile numbers start at 1 instead of 0
        return (i*N) + j + 1;
    }
    
    private int computeManhattanI(int tileNum){
        //Subtract one to adjust for fact that the goal tile numbers start at 1 instead of 0
        return (tileNum-1)/N;
    }
    
    private int computeManhattanJ(int tileNum) {
        //Subtract one to adjust for fact that the goal tile numbers start at 1 instead of 0
        int manhattanJ = tileNum - (computeManhattanI(tileNum)*N) - 1;
        return manhattanJ; 
    }
    
    private int computeManhattanCount(int tileNum, int i, int j) {
        int iDifference = Math.abs(i - computeManhattanI(tileNum));
        int jDifference = Math.abs(j - computeManhattanJ(tileNum));
        return iDifference + jDifference;
    }
    
    private int[][] copyBlocks(int[][] blocks) {
        int[][] copy = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                copy[i][j] = blocks[i][j];
            }
        }
        return copy;
    }
    
    private void exchangeTiles(int[][] arr, int i1, int j1, int i2, int j2) {
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        /*System.out.println("Blocks: ");
        for (int i =0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.println(boardBlocks[i][j]);
            }
        }*/
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
        
        Board board = new Board(blocks);
        /*System.out.println("blocks length: "+ blocks.length);
        System.out.println("hamming: " + board.hamming());
        System.out.println("manhattan: " + board.manhattan());*/
        System.out.println("\nBoard: " + board);        
        System.out.println("\nTwin: " + board.twin());
    }
    
    
    
}