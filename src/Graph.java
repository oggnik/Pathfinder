/**
 * Graph.java
 * Holds the necessary information for a graph
 */

import java.util.*;

public class Graph {
	private ArrayList<Edge> edges;
	private ArrayList<Vertex> vertices;

	/**
	 * Create an empty graph
	 */
	public Graph() {
		edges = new ArrayList<Edge>();
		vertices = new ArrayList<Vertex>();
	}

	/**
	 * Create a graph with starting edges and vertices
	 */
	public Graph(ArrayList<Edge> edges, ArrayList<Vertex> vertices) {
		this.edges = edges;
		this.vertices = vertices;
	}

	/**
	 * Add an edge
	 */
	public void addEdge(Edge e) {
		edges.add(e);
	}

	/**
	 * Add a list of edges
	 */
	public void addEdges(ArrayList<Edge> edges) {
		for (Edge e : edges)
			this.edges.add(e);
	}

	/**
	 * Get the edges
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * Set the edges
	 */
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}


	/**
	 * Add a vertex
	 */
	public void addVertex(Vertex v) {
		vertices.add(v);
	}

	/**
	 * Add a list of vertices
	 */
	public void addVertices(ArrayList<Vertex> vertices) {
		for (Vertex v : vertices)
			this.vertices.add(v);
	}

	/**
	 * Get the vertices
	 */
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * Set the vertices
	 */
	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	/**
	 * Get the vertex containing the data
	 */
	public Vertex getVertex(Object data) {
		for (Vertex v : vertices) {
			if (data.equals(v.getData()))
				return v;
		}
		return null;
	}

	/**
	 * Get the edge with the specified starting and ending points
	 */
	public Edge getEdge(Vertex start, Vertex end) {
		for (Edge e : edges) {
			if (e.getStart().equals(start) && e.getEnd().equals(end))
				return e;
		}
		return null;
	}
}
