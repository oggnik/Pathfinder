/**
 * Vertx.java
 * Holds information about a vertex
 */

import java.util.*;

public class Vertex {
	//data - holds the information in the vertex
	private Object data;
	//edges - holds the edges
	private ArrayList<Edge> edges;

	//distance - used for Dijkstra's
	private double distance;
	//Previous - used for Dijkstra's
	private Vertex previous;

	/**
	 * Create a new vertex
	 * Takes a data object
	 */
	public Vertex(Object data) {
		this.data = data;
		edges = new ArrayList<Edge>();
	}

	/**
	 * Add an edge to the vertex
	 */
	public void addEdge(Edge e) {
		edges.add(e);
	}

	/**
	 * Get the edges
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}



	/**
	 * Get the data
	 */
	public Object getData() {
		return data;
	}

	public double getDistance() {
		return distance;
	}
	public void setDistance(double d) {
		distance = d;
	}

	public Vertex getPrevious() {
		return previous;
	}
	public void setPrevious(Vertex v) {
		previous = v;
	}

	/**
	 * Equals
	 */
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (!(other instanceof Vertex))
			return false;
		Vertex oVertex = (Vertex) other;
		if (!data.equals(oVertex.getData()))
			return false;
		return true;
	}

	/**
	 * toString
	 */
	public String toString() {
		return "Vertex: " + data;
	}
}
