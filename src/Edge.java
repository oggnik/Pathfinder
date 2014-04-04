/**
 * Edge.java
 * Holds information about an edge
 */

import java.util.*;

public class Edge {
	//start - the start of the edge
	private Vertex start;
	//end - the end of the edge
	private Vertex end;
	//betweenness - the number of shortest paths running through the edge
	private int betweenness;
	//weight - the weight of the edge
	private double weight;

	/**
	 * Create a new edge with a weight of zero
	 * Takes a start and end vertex
	 */
	public Edge(Vertex start, Vertex end) {
		this.start = start;
		this.end = end;
		weight = 1.0;
	}
	
	/**
	 * Create a new edge with a given weight
	 * Takes a start and end vertex
	 */
	public Edge(Vertex start, Vertex end, double weight) {
		this(start, end);
		this.weight = weight;
	}
	/**
	 * Get the starting vertex
	 */
	public Vertex getStart() {
		return start;
	}

	/**
	 * Get the ending vertex
	 */
	public Vertex getEnd() {
		return end;
	}

	/**
	 * Get the betweenness of the edge
	 */
	public int getBetweenness() {
		return betweenness;
	}

	/**
	 * Set the betweenness of the edge
	 */
	public void setBetweenness(int b) {
		betweenness = b;
	}

	/**
	 * Add one to the betweenness
	 */
	public void incrementBetweenness() {
		betweenness++;
	}

	/**
	 * Get the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Set the weight
	 */
	public void setWeight(double w) {
		weight = w;
	}

	/**
	 * Equals
	 */
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (!(other instanceof Edge))
			return false;
		Edge oEdge = (Edge) other;
		if (!start.equals(oEdge.getStart()))
			return false;
		if (!end.equals(oEdge.getEnd()))
			return false;
		return true;
	}

	/**
	 * toString
	 */
	public String toString() {
		return "Edge: " + start + " -> " + end + "  Betweenness: " + betweenness;
	}
}
