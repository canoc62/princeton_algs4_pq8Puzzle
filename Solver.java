import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class Solver {
   
    private Node initialNode;
    private Node min;
    public boolean isSolvable = true;
    private int moves = 0;
    private MinPQ<Node> search = new MinPQ<Node>();
    private ArrayList<Board> solution = new ArrayList<Board>();
    
    private class Node implements Comparable<Node> {
        private Board board;
        private int moves;
        private int priority;
        private Node previous;
        
        private Node(Board board, Node previousNode) {
            this.board = board;
            previous = previousNode;
            if (previousNode != null) {
                moves = previousNode.moves + 1;
            }
            else {
                moves = 0;
            }
            priority = this.board.manhattan() + moves;
        }
        
        public int compareTo(Node otherNode) {
            if (this.priority > otherNode.priority) {
                return 1;
            }
            else if (this.priority < otherNode.priority) {
                return -1;
            }
            else {
                return 0;
            }
        }                                                                    
    }
    
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        initialNode = new Node(initial, null);
        moves = initialNode.moves;
        
    }
    
    public boolean isSolvable() {
        return isSolvable;
    }
    
    public int moves() {
        if (!isSolvable()) return -1;
        return moves;
    }
    
    public Iterable<Board> solution() {
        //Insert initial search node into PQ
        //Node curr = initialNode;
        search.insert(initialNode);
        //boolean goalNotReached = true;
        
        while (true) {
            
            //min = search.min(); //also will be the previous search node
            
            //Delete node with min priority from PQ
            
            min = search.delMin();
            moves = min.moves;
            solution.add(min.board);
            
            if (min.board.isGoal()) {
                break;//goalNotReached = false;
            }
            
            //Insert into PQ all neighboring search nodes
            for (Board neighborBoard : min.board.neighbors()) { 
                if (!neighborBoard.equals(min.previous.board)) {
                    Node searchNode = new Node(neighborBoard, min);
                    search.insert(searchNode);   
                }
            }
        }
        return solution;
    }
    
    public static void main(String[] args) {
        
    }
    
    
    
    
}