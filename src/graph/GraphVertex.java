package graph;

import java.util.*;

@SuppressWarnings("rawtypes")
public class GraphVertex implements Comparable {
	
	private GeographicPoint location;
	HashSet<GraphEdge> edges; //from this vertex;
	String name;
	private double distance;
	private double actualDistance;

	
	public GraphVertex(GeographicPoint loc, String name) {
		location = loc;
		edges = new HashSet<>();
		this.name = name;
		distance = 0.0;
		actualDistance = 0.0;
	}
	
	public void addEdge(GraphEdge edge) {
		edges.add(edge);
	}

	public Set<GraphVertex> getNeighbors() {
		Set<GraphVertex> neighbors = new HashSet<>();
		for (GraphEdge edge : edges) {
			neighbors.add(edge.getDiffSideOfVertex(this));
		}
		return neighbors;
	}

	GeographicPoint getLocation() {
		return location;
	}
	
	public double getDistance() {
		return this.distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getActualDistance() {
		return this.actualDistance;
	}
	public void setActualDistance(double actualDistance) {
		this.actualDistance = actualDistance;
	}

	@Override
	public int compareTo(Object o) {
		GraphVertex qwe = (GraphVertex) o;
		return ((Double) this.getDistance()).compareTo((Double) qwe.getDistance());
	}
	public boolean equals(Object obj) {
		if(!(obj instanceof GraphVertex) || (obj == null)) {
			return false;
		}
		GraphVertex nd = (GraphVertex) obj;
		return nd.location.equals(this.location);
	}
}
