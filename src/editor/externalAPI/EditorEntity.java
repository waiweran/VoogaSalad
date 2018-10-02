package editor.externalAPI;

import java.util.List;

import general.Id;
import general.attributes.Attribute;

/**
 * Interface for the editor front-end to interact with entities in the model.
 */
public interface EditorEntity {
		
	/**
	 * @return a list of entity's attributes that the user can see and edit.
	 */
	public List<EditorAttribute<?>> getViewableAttributes();
	
	/**
	 * Gets a specific attribute from the back-end by name.
	 * @param s the name of the attribute to get.
	 * @param c the class type of the attribute.
	 * @return the attribute being accessed.
	 */
	public <T> T getAttribute(String s, Class<T> c);
	
	/**
	 * Gets a generic attribute from the back-end by name.
	 * Note: Value is of type "Object"
	 * @param s the name of the attribute to get.
	 * @param c the class type of the attribute.
	 * @return the attribute being accessed.
	 */
	public Attribute<?> getAttribute(String attributeID);
	
	/**
	 * 
	 * @param a - attribute to be added to the entity
	 */
	public void addAttribute(EditorAttribute<?> a);
	
	/**
	 * @return the entity's unique ID used to identify entities in the world.
	 */
	public Id getId();
}
