import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class Solver {
    
    private int moves = 0;
    private MinPQ solution = new MinPQ();
    
    private class Node {
        private Board board;
        private int moves;
        private Node previous;
    }
    
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
    }
    
    public boolean isSolvable() {
        return true;
    }
    
    public int moves() {
        if !isSolvable() return -1;
        return moves;
    }
    
    public Iterable<Board> solution() {
        //Insert initial search node into PQ
        //Delete node with min priority from PQ
        //Insert into PQ all neighboring search nodes
        //Repeat until search node dequeued corresponds to a goal board
        Node initial = new Node();
        solution.insert(initial);
        return solution;
    }
    
    public static void main(String[] args) {
        
    }
    
    
    
    
}