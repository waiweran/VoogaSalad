package play.externalAPI;

import java.util.Observer;

/**
 * Interface for the player front-end to interact with attributes in the model.
 * @param <T> the type of value held by the attribute.
 */
public interface PlayAttribute<T> {
		
	/**
	 * Returns the title of a specific attribute of a game object. This is used for the
	 * front end to display the name of the attribute that is being manipulated.
	 * @return the name of the attribute
	 */
	public String getName();
	
	/**
	 * Get the value of the attribute. This is a generic because it can represent anything
	 * from a movement strategy to a integer value for health.
	 * @return Generic Value
	 */
	public T getValue();
		
	/**
	 * Adds an observer so that the front end knows if this attribute's value is changed. 
	 * @param o the Observer to add.
	 * @see java.util.Observable
	 */
	public void addObserver(Observer o);


}
