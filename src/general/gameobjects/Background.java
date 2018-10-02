package general.gameobjects;

import general.Vector;

/**
 * Holds information about background images in the back end.
 * @author Gordon Hyunh
 * @author Nathaniel Brooke
 * @version 04-29-2017
 */
public class Background {
	
	private String myImage;
	private Vector myPos, mySize;
	
	/**
	 * Initializes a new Background.
	 * @param image the URL of the displayed image.
	 * @param position the location of the corner of the image.
	 * @param size the width and height of the image.
	 */
	public Background(String image, Vector position, Vector size) {
		myImage = image;
		myPos = position;
		mySize = size;
	}
	
	/**
	 * @return the URL of the background image to display.
	 */
	public String getImageURL() {
		return myImage;
	}
	
	/**
	 * @return Vector denoting the corner position of the image.
	 */
	public Vector getPosition() {
		return myPos;
	}
	
	/**
	 * @return Vector denoting the width and height of the image.
	 */
	public Vector getSize() {
		return mySize;
	}
	
	@Override
	public String toString() {
		return "Background [" + myImage + "] Size " + mySize + " Position " + myPos;
	}
}

