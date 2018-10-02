package editor.view.worldEditor.entityDisplay;

import editor.externalAPI.EditorAttribute;
import general.Vector;
import javafx.scene.shape.Shape;
import play.model.collisionmanager.Zone;

/**
 * Displays a zone as a circle in the editor.
 * @author Nathaniel Brooke
 * @version 04-17-2017
 */
public abstract class ZoneView {
	
	private EditorAttribute<Zone> attribute;
	private Vector location;
	
	/**
	 * Initializes a new zone view.
	 * @param a the Zone attribute to view.
	 */
	public ZoneView(EditorAttribute<Zone> a) {
		attribute = a;
		a.addObserver((o, arg) -> updateZone()); 
		location = Vector.ORIGIN;
	}
	
	/**
	 * Sets the global location of this zone.
	 * @param loc the location to move the zone to.
	 */
	public void setLocation(Vector loc) {
		location = loc;
		updateZone();
	}
	
	/**
	 * @return the position of the zone's center.
	 */
	protected Vector getLocation() {
		return location;
	}
	
	/**
	 * @return the Zone Attribute that this represents.
	 */
	protected EditorAttribute<Zone> getAttribute() {
		return attribute;
	}
	
	/**
	 * Gets the view of the Zone.
	 * @return Shape representing the zone.
	 */
	public abstract Shape getView();
	
	/**
	 * Updates the zone view radius and position.
	 */
	protected abstract void updateZone();

}
