/**
 *
 * @author Nicki Ogg
 */
import java.util.*;
public class Node {
    private ArrayList<Node> moves;
    private boolean end;
    private Node parent;
    private Location myLoc;
    public Node(Location loc, boolean isEnd) {
	moves = new ArrayList<Node>();
	myLoc = loc;
	end = isEnd;
    }
    public boolean isEnd() {
	return end;
    }
    public ArrayList<Node> getMoves() {
	return moves;
    }
    public void setParent(Node p) {
	parent = p;
    }
    public Node getParent() {
	return parent;
    }
    public void removeParent() {
        parent = null;
    }
    public void addMove(Node move) {
	moves.add(move);
    }
    public Location getLocation() {
	return myLoc;
    }
    public boolean equals(Object other) {
	if (!(other instanceof Node))
	    return false;
	Node n = (Node) other;
	if (myLoc.equals(n.getLocation()))
	    return true;
	return false;
    }
    public String toString() {
	return "Node[" + myLoc + "][isEnd: " + end + "]";
    }

}
