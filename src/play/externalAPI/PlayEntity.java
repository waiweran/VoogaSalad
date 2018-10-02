package play.externalAPI;

import java.util.List;

import general.Id;

/**
 * Interface for the player front-end to interact with entities in the model.
 */
public interface PlayEntity {
	
	/**
	 * @return a list of all the entity's attributes.
	 */
	public List<PlayAttribute<?>> getAttributes();
	
	/**
	 * Gets a specific attribute from the back-end by name.
	 * @param s the name of the attribute to get.
	 * @param c the class type of the attribute.
	 * @return the attribute being accessed.
	 */
	public <T> T getAttribute(String s, Class<T> c);
	
	/**
	 * @return the entity's unique ID used to identify entities in the world.
	 */
	public Id getId();

}
