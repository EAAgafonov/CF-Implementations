package graph;

import java.awt.geom.Point2D.Double;


@SuppressWarnings("serial")
public class GeographicPoint extends Double {
	
	public GeographicPoint(double latitude, double longitude) {
		super(latitude, longitude);
	}
	
	
	public double distance(GeographicPoint other) {
		//ToDo
		//getDist
		return 0.0;
	}
	
	private double getDist(double lat1, double lon1, double lat2, double lon2) {
		//ToDo
		return 0.0;
	}
	
	public String toString() {
		return "lat: " + getX() + ", lon: " + getY();
 	}
}
