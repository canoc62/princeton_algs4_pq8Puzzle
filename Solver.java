import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class Solver {
   
    private class Node {
        private Board board;
        private int moves;
        private Node previous;
    }
    
    private Node initialNode = new Node();
    private int moves = 0;
    private MinPQ solution = new MinPQ();
    
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        initialNode.board = initial;
        initialNode.moves = 0;
        initialNode.previous = null;
    }
    
    public boolean isSolvable() {
        return true;
    }
    
    public int moves() {
        if (!isSolvable()) return -1;
        return moves;
    }
    
    public Iterable<Board> solution() {
        //Insert initial search node into PQ
        Node initial = new Node();
        solution.insert(initialNode);
        //Delete node with min priority from PQ (sooo least manhattanCount?)
        //Insert into PQ all neighboring search nodes
        //Repeat until search node dequeued corresponds to a goal board
        return solution;
    }
    
    public static void main(String[] args) {
        
    }
    
    
    
    
}