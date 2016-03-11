import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.*;
//import java.util.ArrayList;

public class Solver {
   
    //private Node initialNode;
    //private Node initialTwinNode;
    //private Node min;
    //private Node twinMin;//might not need?
    private boolean isSolvable = true;
    private int moves = 0;
    private MinPQ<Node> search = new MinPQ<Node>();
    //private ArrayList<Board> solution = new ArrayList<Board>();
    //private Stack<Board> solution = new Stack<Board>();
    private Stack<Board> solution;
    
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
            if (this.priority > otherNode.priority) return 1;
            else if (this.priority < otherNode.priority) return -1;
            else {//if (this.priority == otherNode.priority) {
                if (this.board.hamming() > otherNode.board.hamming()) return 1;
                else if (this.board.hamming() < otherNode.board.hamming()) return -1;
                else return 0;
            }
        }
                                                                            
    }
    
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        //min = new Node(initial, null);
        Node initialNode = new Node(initial, null);
        search.insert(initialNode);
        Node initialTwinNode = new Node(initial.twin(), null);
        search.insert(initialTwinNode);
     
       // Node twinMin;
        Node min;
        
        while (true) {
            //moves++;
            //twinMin = search.delMin();
           // twinMin = search.delMin();
            
            min = search.delMin();//search.delMin();
           // solution.add(min.board);
            /*if (twinMin.board.isGoal()) {
                isSolvable = false;
                break;
            }*/
            moves = min.moves;
            if (min.board.isGoal()) {
                //isSolvable = false;
                solution = new Stack<Board>();
                while (min != null) {
                    solution.push(min.board);
                    //if (min.previous == null) {
                        if (min.board == initialTwinNode.board) {
                            isSolvable = false;
                        }
                   // }
                    min = min.previous;
                }
                //moves = min.moves;
                break;
            }
            
             /*if (twinMin.board.isGoal()) {
                isSolvable = false;
                break;
            }*/
            
            for (Board neighborBoard : min.board.neighbors()) { 
                if (min.previous == null || !neighborBoard.equals(min.previous.board)) {
                    Node searchNode = new Node(neighborBoard, min);
                    search.insert(searchNode);   
                }
            }
            
            //twinMin = search.delMin();
            
            /*if (twinMin.board.isGoal()) {
                isSolvable = false;
                break;
            }*/
            
            //min = search.delMin();
            
            /*for (Board twinNeighborBoard : twinMin.board.neighbors()) {
                if (twinMin.previous == null || !twinNeighborBoard.equals(twinMin.previous.board)) {
                    Node twinSearchNode = new Node(twinNeighborBoard, twinMin);
                    search.insert(twinSearchNode);   
                }
            }*/
            //moves = min.moves;
        }
        
    }
    
    
    public boolean isSolvable() {
        return isSolvable;
    }
    
    public int moves() {
        if (!isSolvable()) return -1;
        return moves;//min.moves;
    }
    
    public Iterable<Board> solution() {
        /*Stack<Board> solution = new Stack<Board>();
        while (min != null) {
            solution.push(min.board);
            if (min.board == initialTwinNode.board) {
                isSolvable = false;
            }
            min = min.previous;
        }*/
        return solution;
    }
    
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}