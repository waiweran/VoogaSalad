package play.model.collisionmanager;

import java.util.List;

import general.Vector;

public class RectZone {
	
	double horLength;
	double vertLength;
	Vector topRight;
	Vector topLeft;
	Vector botRight;
	Vector botLeft;
	List<Vector> corners;
	
	public RectZone(Vector topRight, Vector topLeft, Vector botRight, Vector botLeft) {
		horLength = topRight.distanceTo(topLeft);
		vertLength = topRight.distanceTo(botRight);
		this.botLeft = botLeft;
		this.botRight = botRight;
		this.topLeft = topLeft;
		this.topRight = topRight;
		corners.add(botLeft); corners.add(botRight); corners.add(topLeft); corners.add(topRight);
	}
	
	public boolean contains (Vector point){
		boolean inXBounds = (point.getX() > topLeft.getX()) && (point.getX() < topRight.getX());
		boolean inYBounds = (point.getY() < topLeft.getY()) && (point.getY() > botLeft.getX());
		if (inXBounds && inYBounds){
			return true;
		}
		return false;
	}
	
	private boolean rectIntersects (RectZone other){
		boolean intersects = false;
		int intersectionCount = 0;
		for (Vector corner : other.corners){
			if (this.contains(corner)){
				intersects = true;
				intersectionCount++;
			}
		}
		if (intersectionCount == other.corners.size()){
			intersects = false;
		}
		return intersects;
	}
	
	public boolean intersects (RectZone other){
		return rectIntersects(other) || other.rectIntersects(this);
	}
	
	public boolean intersects (Zone other, Vector otherLoc){
		boolean intersects = false;
		int intersectionCount1 = 0;
		int intersectionCount2 = 0;
		for (Vector corner : this.corners){
			if (other.contains(corner, otherLoc)){
				intersects = true;
				intersectionCount1++;
			}
		}
		List<Vector> boundaryPoints = other.getBoundaryPoints(otherLoc);
		for (Vector bp : boundaryPoints){
			if (this.contains(bp)){
				intersects = true;
				intersectionCount2++;
			}
		}
		if (intersectionCount1 == this.corners.size() || intersectionCount2 == boundaryPoints.size()){
			intersects = false;
		}
		return intersects;
	}

}
