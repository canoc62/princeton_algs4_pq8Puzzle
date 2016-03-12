import edu.princeton.cs.algs4.*;

public class Solver {
   
    private boolean isSolvable = true;
    private int moves = 0;
    private MinPQ<Node> search = new MinPQ<Node>();
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
            else {
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
    
        Node initialNode = new Node(initial, null);
        search.insert(initialNode);
        Node initialTwinNode = new Node(initial.twin(), null);
        search.insert(initialTwinNode);
     
        Node min;
        
        while (true) {
            min = search.delMin();
            moves = min.moves;
            
            if (min.board.isGoal()) {
                
                solution = new Stack<Board>();
                while (min != null) {
                    solution.push(min.board);
                    
                    if (min.board == initialTwinNode.board) {
                        isSolvable = false;
                    }
                    
                    min = min.previous;
                }
                break;
            }
            
            for (Board neighborBoard : min.board.neighbors()) { 
                if (min.previous == null || !neighborBoard.equals(min.previous.board)) {
                    Node searchNode = new Node(neighborBoard, min);
                    search.insert(searchNode);   
                }
            }
            
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
        if (isSolvable()) return solution;
        else return null;
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