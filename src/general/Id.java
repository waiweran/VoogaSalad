package general;

import java.util.UUID;

/**
 * Id Class: Holds a unique identifier to differentiate between entities
 * @author Justin Wang
 */

public class Id {

	private UUID ID;

	/**
	 * Default constructor
	 */
	public Id(){
		this.generateID();
	}

    /*Primary Methods*/

	/**
	 * Generates a unique ID using Java's UUID class
	 */
	private void generateID() {
		this.ID = UUID.randomUUID();
	}

	/**
	 * Retrieves the primary UUID of the SpriteID
	 * @return the UUID
	 */
	public UUID getID() {
		return ID;
	}

    /*Parent Methods*/

	@Override
	public boolean equals(Object object) {
		return object instanceof Id && (this.getID().equals(Id.class.cast(object).getID()));
	}

	@Override
	public int hashCode() {
		return getID() != null ? getID().hashCode() : 0;
	}
	
	@Override
	public String toString() {
		return getID().toString();
	}

}
