package graph;

public class GraphEdge {
	String edgeName;
	GraphVertex start;
	GraphVertex end;
	double length;
	static final double DEFAULT_LENGTH = 0.01;
	
	public GraphEdge(String edgeName, GraphVertex n1, GraphVertex n2, double length) {
		this.edgeName = edgeName;
		this.start = n1;
		this.end = n2;
		this.length = length;
	}

	public GraphVertex getDiffSideOfVertex(GraphVertex node) {
		if (node.equals(start))
			return end;
		else if (node.equals(end))
			return start;
		throw new IllegalArgumentException();
	}
	GraphVertex getEndNode() {
		return end;
	}
	GeographicPoint getStartPoint() {
		return start.getLocation();
	}
	GeographicPoint getEndPoint() {
		return end.getLocation();
	}
	
}
