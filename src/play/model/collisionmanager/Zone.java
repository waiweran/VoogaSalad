package play.model.collisionmanager;

import java.util.ArrayList;
import java.util.List;

import general.Vector;

public class Zone implements Comparable<Zone>{
	
	public static final double MAX_RADIUS = 500;
	public static final double PIXEL_DENSITY = 4;
	
	private Vector myOffset;
	private double myRadius;
	
	public Zone(Vector offset, double radius){
		myOffset = offset;
		myRadius = radius;
	}
	
	public Zone(){
		myOffset = Vector.ORIGIN;
		myRadius = 50;
	}
	
	public Zone(Zone old) {
		this(new Vector(old.getOffset()), old.getRadius());
	}

	public Vector getOffset(){
		return myOffset;
	}
	
	public void setOffset(Vector newOffset){
		myOffset = newOffset;
	}
	
	public double getRadius(){
		return myRadius;
	}
	
	public boolean intersects(Zone other, Vector myLoc, Vector otherLoc){
		return this.myOffset.add(myLoc).distanceTo(other.myOffset.add(otherLoc)) <= this.myRadius + other.myRadius;
	}
	
	public boolean intersects(RectZone other, Vector myLoc){
		return other.intersects(this, myLoc);
		
	}
	
	public boolean contains (Vector point, Vector myLoc){
		return point.distanceTo(myLoc) <= myRadius;
	}
	
	public List<Vector> getBoundaryPoints(Vector myLoc){
		List<Vector> boundaryPoints = new ArrayList<Vector>();
		double numOfBoundaryPoints = PIXEL_DENSITY * this.myRadius;
		double angleDiff = 360/numOfBoundaryPoints;
		double currAngle = 0;
		for (int i=0; i < numOfBoundaryPoints; i++){
			double xOffset = myRadius * Math.sin(Math.toRadians(currAngle));
			double yOffset = myRadius * Math.cos(Math.toRadians(currAngle));
			Vector boundaryPoint = myLoc.add(new Vector(xOffset, yOffset));
			boundaryPoints.add(boundaryPoint);
			currAngle += angleDiff;
		}
		return boundaryPoints;
	}

	@Override
	public int compareTo(Zone o) {
		return 0;
	}
	
}


