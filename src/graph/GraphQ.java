package graph;

import java.util.*;

public class GraphQ {
	HashMap<GeographicPoint, GraphVertex> nodes;
	HashSet<GraphEdge> edges;
	static int numOfNodes = 0;
	static int numOfEdges = 0;
	public static boolean isAstar;
	
	
	public GraphQ() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
	
	public void addVertex(GeographicPoint location) {
		String name = "";
		addVertex(location, name);
	}

	public boolean addVertex(GeographicPoint location, String name) {
		GraphVertex vertex = nodes.get(location);		
		if (vertex == null) {
			vertex = new GraphVertex(location, name);
			nodes.put(location, vertex);
			numOfNodes++;
			return true;
		}
		return false;
	}

	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName, double length) {
		GraphVertex n1 = nodes.get(from);
		GraphVertex n2 = nodes.get(to);
		if (n1 == null || n2 == null) throw new NullPointerException("wrong input for edges");
		
		GraphEdge edge = new GraphEdge(roadName, n1, n2, GraphEdge.DEFAULT_LENGTH);
		edges.add(edge);
		n1.addEdge(edge);
//		n2.addEdge(edge);
		numOfEdges++;
	}
	
	public List<GeographicPoint> bfs(GeographicPoint start,GeographicPoint end) {
		if (start == null || end == null) throw new NullPointerException("check  start or end = null");
		
		GraphVertex startPos = nodes.get(start);
		GraphVertex endPos = nodes.get(end);
		
		if (startPos == null || endPos == null)
			return null;
		
		HashMap<GraphVertex, GraphVertex> map = new HashMap<>();
		Queue<GraphVertex> queue = new LinkedList<>(); // loc to be visited
		HashSet<GraphVertex> visited = new HashSet<>(); // already been there
		
		queue.add(startPos);
		GraphVertex curr = null;
		while (!queue.isEmpty()) {
			curr = queue.remove();
			if (curr.equals(endPos)) {
				break;
			}
			Set<GraphVertex> neighbors = getNeighbors(curr);
			for (GraphVertex neighbor : neighbors) {
				if(!visited.contains(neighbor)) {
					visited.add(neighbor);
					map.put(neighbor, curr);
					queue.add(neighbor);
				}
			}
		}
		
		if(!curr.equals(endPos)) {
			System.out.println("no path found");
			return null;
		}
		
		List<GeographicPoint> path = reconstructPath(map, startPos, endPos);		
		return path;
	}
	private Set<GraphVertex> getNeighbors(GraphVertex vertex) {
		return vertex.getNeighbors();
	}
	private List<GeographicPoint> reconstructPath(HashMap<GraphVertex, GraphVertex> map,  GraphVertex start, GraphVertex end) {
		LinkedList<GeographicPoint> path = new LinkedList<>();
		GraphVertex curr = end;
		
		while (!curr.equals(start)) {
			path.addFirst(curr.getLocation());
			curr = map.get(curr);
		}
		path.addFirst(start.getLocation());
		return path;
	}
	
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		isAstar = false;
		return dijkstraOrAstar(start, goal);
	}
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		isAstar = true;
		return dijkstraOrAstar(start, goal);
	}
	
	
	public List<GeographicPoint> dijkstraOrAstar(GeographicPoint start, GeographicPoint end) {
		if (start == null || end == null) throw new NullPointerException("check  start or end = null");
		
		GraphVertex startPos = nodes.get(start);
		GraphVertex endPos = nodes.get(end);
		
		if (startPos == null || endPos == null) {
			System.out.println("something wrong");
			return null;
		}
			
		
		PriorityQueue<GraphVertex> PriorityQueue = new PriorityQueue<>();
		HashSet<GraphVertex> visited = new HashSet<>();
		HashMap<GraphVertex, GraphVertex> map = new HashMap<>();
		GraphVertex curr = null;
		
		for (GeographicPoint p : nodes.keySet()) {
			nodes.get(p).setDistance(Double.POSITIVE_INFINITY);
			nodes.get(p).setActualDistance(Double.POSITIVE_INFINITY);
		}
		startPos.setDistance(0.0);
		startPos.setActualDistance(0.0);
		PriorityQueue.add(startPos);
		
		while (!PriorityQueue.isEmpty()) {
			curr = PriorityQueue.remove();
			
			if (!visited.contains(curr)) {
				visited.add(curr);
				if (curr.equals(endPos)) {
					break;
				}
				
				for (GraphVertex neighbor : getNeighbors(curr)) {
					if (!visited.contains(neighbor)) {
						
						Double distFromCurr = findDistanceBetweenNeighbors(curr.getLocation(), neighbor.getLocation());
						Double distanceFromTarget = isAstar ? findStraightLineBetweenNodes(neighbor.getLocation(), endPos.getLocation()) : 0.0;
						
						if (distFromCurr + curr.getDistance() < neighbor.getDistance()) {
							neighbor.setActualDistance(distFromCurr + curr.getActualDistance());
							neighbor.setDistance(neighbor.getActualDistance() + distanceFromTarget);
							map.put(neighbor, curr);
							PriorityQueue.add(neighbor);
						}
					}
				}
			}
		}
		
		if (!curr.equals(endPos)) {
			System.out.println("no path found");
			return null;
		}
		
		List<GeographicPoint> path = reconstructPath(map, startPos, endPos);
		return path;
	}

	public Double findDistanceBetweenNeighbors(GeographicPoint start, GeographicPoint end) {
		for (GraphEdge mapEdge : edges) {
			if (mapEdge.getEndNode().getLocation().equals(end) && mapEdge.getDiffSideOfVertex(nodes.get(end)).getLocation().equals(start)) {
				return mapEdge.length;
			}
			if (start.equals(end)) {
				return 0.0;
			}
		}
		System.out.println(start + " - " + end);
		System.out.println("qqqqqq");
		return null;
	}
	
	public Double findStraightLineBetweenNodes(GeographicPoint start, GeographicPoint end) {
		return 100*Math.sqrt(Math.pow(end.x - start.x, 2.0) + Math.pow(end.y - start.y, 2.0));
	}
	
	
	
	
	

	
	

	public static void main(String[] args) {
		
		GraphQ qwe = new GraphQ();
		GraphLoader.loadMap("data/san_diego.map", qwe);
		System.out.println(numOfNodes + " - " + numOfEdges);
		
		GeographicPoint testStart = new GeographicPoint(32.7205321, -117.1453554);
		GeographicPoint testEnd = new GeographicPoint(32.71159, -117.1402447);

		List<GeographicPoint> testroute = qwe.dijkstra(testStart, testEnd);
		
		System.out.println(testroute);
	}

}
