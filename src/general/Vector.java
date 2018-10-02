package general;

import java.awt.Point;

/**
 * Vector class holds 2 double elements.
 * @author Nathaniel Brooke
 * @author Advait Reddy
 * @version 02-26-2017
 */
public class Vector implements Comparable<Vector> {
	
	/**
	 * Origin Position constant for easy access.
	 */
	public static final Vector ORIGIN = new Vector(0, 0);
	
	private double x, y;

	/**
	 * Initializes a new Position.
	 * @param x
	 * @param y
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector old) {
		this(old.getX(), old.getY());
	}

	public Vector(Point point) {
		this(point.getX(), point.getY());
	}

	/**
	 * Gets the X value.
	 * @return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Gets the Y value.
	 * @return y
	 */
	public double getY() {
		return y;
	}
		
	/**
	 * Creates a new Position offset from this Position by the given values.
	 * @param xOff the X offset.
	 * @param yOff the Y offset.
	 * @return new Position, correctly offset.
	 */
	public Vector offset(double xOff, double yOff) {
		return new Vector(x + xOff, y + yOff);
	}
	
	/**
	 * Changes the sign of the vector.
	 * @return Vector with x and y values of opposite sign.
	 */
	public Vector negate() {
		return new Vector(-x, -y);
	}
	
	/**
	 * Flips the vector around the X axis
	 * @return new Vector with y value inverted
	 */
	public Vector flip() {
		return new Vector(x, -y);
	}
	
	/**
	 * Calculates the dot product between two vectors.
	 * @param other the other vector.
	 * @return double dot product value.
	 */
	public double dot(Vector other){
		return this.x * other.x + this.y * other.y;
	}
	
	/**
	 * Adds two vectors together.
	 * @param other the other vector to add.
	 * @return Vector sum of the vectors.
	 */
	public Vector add(Vector other){
		return new Vector(this.x + other.x, this.y + other.y);
	}
	
	/**
	 * Performs scalar multiplication on a vector.
	 * @param scalar the scalar to multiply by
	 * @return Vector multiplied by given scalar.
	 */
	public Vector scalarMultiply(double scalar) {
		return new Vector(x*scalar, y*scalar);
	}
	
	/**
	 * Calculates the magnitude of a vector.
	 * @return double magnitude value.
	 */
	public double magnitude(){
		return distanceTo(ORIGIN);
	}
	
	/**
	 * Calculates the distance between two points defined by vectors.
	 * @param other the second point in the calculation
	 * @return double distance value.
	 */
	public double distanceTo(Vector other){
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}
	
	/**
	 * Calculates the angle, in degrees, of the vector, from positive x direction.
	 * @return Double value of the angle, in degrees.
	 */
	public double angle() {
		return Math.toDegrees(Math.atan2(y, x));
	}
	
	/**
	 * Calculates the angle, in radians, of the vector, from the positive x direction.
	 * @return Double value of the angle, in radians.
	 */
	public double angleRad() {
		return Math.toRadians(angle());
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Vector 
				&& this.x == ((Vector)other).x
				&& this.y == ((Vector)other).y;
	}

	@Override
	public int compareTo(Vector other) {
		int diffX = new Double(this.x).compareTo(new Double(other.x));
		if(diffX == 0) {
			return new Double(this.y).compareTo(new Double(other.y));
		}
		return diffX;
	}
	
	@Override
	public int hashCode() {
		return new Double(x).hashCode() + 1019*(new Double(y).hashCode());
	}
	
	@Override
	public String toString() {
		return "(" + Math.round(x) + ", " + Math.round(y) + ")";
	}
	
}
