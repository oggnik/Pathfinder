/**
 *
 * @author Nicki Ogg
 */
import java.util.*;
import java.io.*;
public class Pathfinder {
    private ArrayList<Node> nodes;
    private ArrayList<Node> tested;
    private char[][] maze;
    private int rows;
    private int cols;
    private Node start;
    public Pathfinder() {
	nodes = new ArrayList<Node>();
        tested = new ArrayList<Node>();
	boolean done = false;
	try {
            getMaze();
            done = true;
            getNodes();
            setMoves();
            boolean solvable = isSolvable(start);
            System.out.println("Solvable: " + solvable);
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++)
                    System.out.print(maze[r][c]);
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Error!");
            System.err.println(e);
            done = false;
	}

    }
    public void getMaze() throws Exception {
	Scanner in = new Scanner(System.in);
	System.out.print("Enter path to maze file: ");
	String path = in.nextLine();
	File f = new File(path);
	Scanner input = new Scanner(f);
	rows = Integer.parseInt(input.nextLine());
	cols = Integer.parseInt(input.nextLine());
	char[][] m = new char[rows*2][cols];
	System.out.println("Maze entered:");
	for (int r = 0; r < rows; r++) {
	    m[r] = input.nextLine().replace(",", "").toCharArray();
	    System.out.println(m[r]);
	}
	maze = m;
    }
    public void getNodes() {
	for (int r = 0; r < rows; r++) {
	    for (int c = 0; c < cols; c++) {
		if (maze[r][c] == 'X') {
		    Node n = new Node(new Location(r, c), true);
		    nodes.add(n);
		} else if (maze[r][c] == '.') {
		    Node n = new Node(new Location(r, c), false);
		    nodes.add(n);
		    start = n;
		} else if (maze[r][c] == ' ') {
		    Node n = new Node(new Location(r, c), false);
		    nodes.add(n);
		}
	    }
	}
    }
    
    public void setMoves() {
	for (Node cur: nodes) {
	    for (Node n: nodes) {
		if (!cur.equals(n)) {
		    if (cur.getLocation().getRow() == n.getLocation().getRow()) {
			if ((cur.getLocation().getCol() + 1 == n.getLocation().getCol()) || (cur.getLocation().getCol() - 1 == n.getLocation().getCol()))
			    cur.addMove(n);
		    }
		    if (cur.getLocation().getCol() == n.getLocation().getCol()) {
			if ((cur.getLocation().getRow() + 1 == n.getLocation().getRow()) || (cur.getLocation().getRow() - 1 == n.getLocation().getRow()))
			    cur.addMove(n);
		    }
		}
	    }
	}
    }
    /*public boolean isSolvable(Node current) {
        tested.add(current);
	if (current.isEnd())
	    return true;
        //maze[current.getLocation().getRow()][current.getLocation().getCol()] = 'o';
	ArrayList<Node> curMoves = current.getMoves();
	if (curMoves.isEmpty()) {
	    return false;
        }
	for (Node next: curMoves) {
	    if (!tested.contains(next)) {
		if (isSolvable(next)) {
                    maze[current.getLocation().getRow()][current.getLocation().getCol()] = '.';
		    return true;
                }
	    }
	}
	return false;
    }*/
    private boolean isSolvable() {//breadth first
	if (start == null)
	    return false;
        tested = new ArrayList<Node>();
	LinkedList<Node> list = new LinkedList<Node>();
	list.add(start);
	tested.add(start);
	while (!list.isEmpty()) {
	    Node current = list.removeFirst();
	    if (current.isEnd()) {
		addParentsToPath(current);
		return true;
	    }
	    ArrayList<Node> possibleWords = current.getPossibleWords();
	    for (Node next : possibleWords) {
		if (!tested.contains(next)) {
		    next.setParent(current);
		    tested.add(next);
		    list.addLast(next);
		}
	    }
	}
	return false;
    }


    public static void main(String[] args) {
	Pathfinder ms = new Pathfinder();
    }
}
