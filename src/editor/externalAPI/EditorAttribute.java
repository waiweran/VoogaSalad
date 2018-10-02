package editor.externalAPI;

import java.util.Iterator;
import java.util.Observer;

import general.fields.Field;

/**
 * Interface for the editor front-end to interact with attributes in the model.
 * @param <T> the type of value held by the attribute.
 */
public interface EditorAttribute<T> extends Iterable<Field<?>>{
	
	/**
	 * Returns the title of a specific attribute of a game object. This is used for the
	 * front end to display the name of the attribute that is being manipulated.
	 * @return the name of the attribute
	 */
	public String getName();
	
	/**
	 * Set the value of an attribute.
	 * 
	 * If the value needs to be modified, not set, do this through the attribute's fields.
	 * 
	 * Some types of value must be set from a list of options, some can be set to any
	 * value, and some can only be set to a certain range of values.  Details on this are
	 * determined by the type T of value.
	 * 
	 * @param value the new value to set.
	 */
	public void setValue(T value);
	
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
	
	/**
	 * This allows the fields in an EditorAttribute to be iterated over.
	 * @see java.util.Iterator
	 */
	@Override
	public Iterator<Field<?>> iterator();
	
}
