/**
 *
 * @author Nicki Ogg
 */
import java.util.*;
import java.io.*;
public class PathfinderDijkstraModifiedOutput {
    private char[][] maze;
    private int rows;
    private int cols;
    private Graph graph;
    private Vertex start;
    private Vertex end;
    private ArrayList<Vertex> path;
    public PathfinderDijkstraModifiedOutput() {
        path = new ArrayList<Vertex>();
	boolean done = false;
	try {
            graph = new Graph();
            getMaze();
            done = true;
            getNodes();
            setMoves();
            dijkstra(graph, start);
            addParentsToPath(end);
            //for (int r = 0; r < rows; r++) {
                //for (int c = 0; c < cols; c++)
                    //System.out.print(maze[r][c]);
                //System.out.println();
            //}
            //Print out the nodes of the path in order
            PrintWriter out = new PrintWriter("output.txt");
            for (Vertex n : path) {
            	Location loc = (Location) n.getData();
                out.printf("%d %d\n", loc.getCol(), loc.getRow());
            }
            out.close();
        } catch (Exception e) {
            System.err.println("Error!");
            System.err.println(e);
            e.printStackTrace();
            done = false;
	}

    }
    
    /**
     * Get a maze
     * @throws Exception 
     */
    public void getMaze() throws Exception {
	Scanner in = new Scanner(System.in);
	System.out.print("Enter path to maze file: ");
	String path = in.nextLine();
	File f = new File(path);
	Scanner input = new Scanner(f);
	rows = 183; cols = 183;
	//rows = Integer.parseInt(input.nextLine());
	//cols = Integer.parseInt(input.nextLine());
	char[][] m = new char[rows][cols];
	//System.out.println("Maze entered:");
	for (int r = 0; r < rows; r++) {
	    m[r] = input.nextLine().replace(",", "").toCharArray();
	    //System.out.println(m[r]);
	}
	maze = m;
    }
    
    /**
     * Convert the 2d array to nodes
     * . = start
     * X = end
     * ' ' = movable position
     */
    public void getNodes() {
	for (int r = 0; r < rows; r++) {
	    for (int c = 0; c < cols; c++) {
		if (maze[r][c] == '3') {
		    Vertex n = new Vertex(new Location(r, c));
                    end = n;
		    graph.addVertex(n);
		} else if (maze[r][c] == 'A') {
		    Vertex n = new Vertex(new Location(r, c));
		    graph.addVertex(n);
		    start = n;
		} else if (maze[r][c] == '0') {
		    Vertex n = new Vertex(new Location(r, c));
		    graph.addVertex(n);
		}
	    }
	}
    }
    
    /**
     * Set the possible moves
     */
    public void setMoves() {
        
	for (Vertex cur: graph.getVertices()) {
	    for (Vertex n: graph.getVertices()) {
		if (!cur.equals(n)) {
		    if (((Location)cur.getData()).getRow() == ((Location)n.getData()).getRow()) {
			if ((((Location)cur.getData()).getCol() + 1 == ((Location)n.getData()).getCol()) || (((Location)cur.getData()).getCol() - 1 == ((Location)n.getData()).getCol())) {
                            Edge e = new Edge(cur, n, 1.0);
                            graph.addEdge(e);
                            cur.addEdge(e);
                        }
		    }
		    if (((Location)cur.getData()).getCol() == ((Location)n.getData()).getCol()) {
			if ((((Location)cur.getData()).getRow() + 1 == ((Location)n.getData()).getRow()) || (((Location)cur.getData()).getRow() - 1 == ((Location)n.getData()).getRow())) {
			    Edge e = new Edge(cur, n, 1.0);
                            graph.addEdge(e);
                            cur.addEdge(e);
                        }
		    }
                    //Diagonals
                    if (((Location)cur.getData()).getCol() - 1 == ((Location)n.getData()).getCol()) {
			if ((((Location)cur.getData()).getRow() + 1 == ((Location)n.getData()).getRow()) || (((Location)cur.getData()).getRow() - 1 == ((Location)n.getData()).getRow())) {
			    Edge e = new Edge(cur, n, 1.414);
                            graph.addEdge(e);
                            cur.addEdge(e);
                        }
		    }
                    if (((Location)cur.getData()).getCol() + 1 == ((Location)n.getData()).getCol()) {
			if ((((Location)cur.getData()).getRow() + 1 == ((Location)n.getData()).getRow()) || (((Location)cur.getData()).getRow() - 1 == ((Location)n.getData()).getRow())) {
			    Edge e = new Edge(cur, n, 1.414);
                            graph.addEdge(e);
                            cur.addEdge(e);
                        }
		    }
		}
	    }
	}
        //System.out.println("Edges: " + graph.getEdges().size());
        //System.out.println("Vertices: " + graph.getVertices().size());
    }
    /*public boolean isSolvable(Node current) {//depth first
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
    
    /**
     * Perform a breadth first search to test if the maze is solvable.
     * This also updates the path if the maze is solvable.
     * @return 
     */
    /*private boolean isSolvable() {//breadth first
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
	    ArrayList<Node> neighbors = current.getMoves();
	    for (Node next : neighbors) {
		if (!tested.contains(next)) {
		    next.setParent(current);
		    tested.add(next);
		    list.addLast(next);
		}
	    }
	}
	return false;
    }*/
    
    /**
	 * Run dijkstra's for a given vertex as the start
	 */
	public static void dijkstra(Graph graph, Vertex start) {
		//Reset all the nodes
		for (Vertex v : graph.getVertices()) {
			v.setDistance(Double.MAX_VALUE);
			v.setPrevious(null);
		}
		start.setDistance(0);
		ArrayList<Vertex> untouched = new ArrayList<Vertex>();
		for (Vertex v : graph.getVertices())
			untouched.add(v);
		
		while (!untouched.isEmpty()) {
			//Next - the vertex with the smallest distance
			Vertex next = untouched.get(0);
			for (Vertex v : untouched) {
				if (v.getDistance() < next.getDistance())
					next = v;
			}
			untouched.remove(next);
			if (next.getDistance() == Double.MAX_VALUE)
				break;
			
			for (Edge e : next.getEdges()) {
				//The neighbor
				Vertex v = e.getEnd();
				double potentialDistance = next.getDistance() + e.getWeight();
				if (potentialDistance < v.getDistance()) {
					v.setDistance(potentialDistance);
					v.setPrevious(next);
				}
			}
		}
	}
        
        /**
	 * Update the betweennesss for the edges
	 */
	public static void updateEdgeBetweenness(Graph graph) {
		for (Vertex v : graph.getVertices()) {
			if (v.getPrevious() == null)
				continue;
			Vertex previous = v.getPrevious();
			graph.getEdge(previous, v).incrementBetweenness();
		}
	}

    /**
     * Recursively add the parents of current to the path
     * @param current 
     */
    private void addParentsToPath(Vertex current) {
	Vertex parent = current.getPrevious();
	if (parent != null) {
	    addParentsToPath(parent);
	}
        path.add(current);
        maze[((Location) current.getData()).getRow()][((Location) current.getData()).getCol()] = '.';
	
    }

    public static void main(String[] args) {
	PathfinderDijkstraModifiedOutput ms = new PathfinderDijkstraModifiedOutput();
    }
}
