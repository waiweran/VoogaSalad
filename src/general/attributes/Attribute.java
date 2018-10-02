package general.attributes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import editor.externalAPI.EditorAttribute;
import general.fields.Field;
import play.externalAPI.PlayAttribute;

/**
 * This represents something that distinguished an Entity from other Entities, 
 * whether that be functionality or some value
 * 
 * For an example of how to extend, take a look at the "general.attributes.Location" class.
 * 
 * @author Dhruv K Patel
 * @author Justin Wang
 * @param <T>
 */

public abstract class Attribute<T> extends Observable implements EditorAttribute<T>, 
																					PlayAttribute<T>,
																					Observer, 
																					Iterable<Field<?>>,
																					//Comparable<T>
																					ReadOnlyAttribute<T>{

	public static final String ATTRIBUTES = "resources/Attribute";
	public static final String OBSERVERS = "resources/Observer";

	
	private String name;
	private T value;
	private LinkedHashMap<String, Field<?>> fields;
	
	/**
	 * Constructor for all Attributes (does not have to be added to in subclasses)
	 * @param name
	 * @param value
	 */
	public Attribute(String name, T value){
		this.name = name;
		setValue(value);
		this.attachObserversToFields();
	}
	
	/**
	 * Returns the title of a specific attribute of a game object. This is used for the
	 * front end to display the which attribute is being manipulated.
	 * @return String name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Get the value of an attribute. This is a generic because it can represent a movement
	 * strategy or an attribute like health.
	 * @return Generic Value
	 */
	@Override
	public T getValue() {
		return value;
	}
	
	/**
	 * Set the value of an attribute.
	 * This value is something that is directly linked to the model configuration
	 * @param value
	 */
	public void setValue(T value) {
		this.value = value;
		
		List<String> previousFields;
		if(fields != null){
			previousFields = new ArrayList<>(fields.keySet());
		}
		else{
			previousFields = new ArrayList<>();
		}
		
		fields = getMapFromList(updateFields(value));	
		attachObserversToFields();
		
		if(!previousFields.containsAll(fields.keySet()) || !fields.keySet().containsAll(previousFields)){
			onFieldAddition();
		}
		else{
			onChange();
		}
	}

	/**
	 * This class iterates over the fields the attribute contains
	 */
	@Override
	public Iterator<Field<?>> iterator() {
		return fields.values().iterator();
	}
	
	/**
	 * Returns a Map of the fields in the order inserted
	 * Note: this is a LinkedHashMap because its entries
	 * are ordered by insertion.
	 */
	public LinkedHashMap<String, Field<?>> getFields(){
		return fields;
	}
	
	/**
	 * Returns a list of the fields in the order inserted
	 */
	public List<Field<?>> getFieldsList(){
		return new ArrayList<Field<?>>(fields.values());
	}
	
//	/**
//	 * Returns observable list of fields in the order inserted
//	 */
//	public ObservableList<Field<?>> getObservableFieldsList(){
//		return observableFields;
//	}
		
	/**
	 * This is called when an observable observed by attribute changes.
	 * @param o
	 * @param arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Field<?>){
			Field<?> changedField = ((Field<?>) o); 
			fields.put(changedField.getName(), changedField);
			value = updateValue(fields);
			setValue(value);
		}
		else if(o instanceof Attribute<?>){
			this.attributeUpdate(o, arg);
		}
		onChange();
	}
	
	/**
	 * Method to be overwritten by Attributes that observe other attributes.
	 */
	protected void attributeUpdate(Observable o, Object arg){
		return;
	}
		
	private void onChange(){
		setChanged();
		notifyObservers();
	}
	
	private void onFieldAddition(){
		setChanged();
		notifyObservers("Fields length changed");
	}
	
	private LinkedHashMap<String, Field<?>> getMapFromList(List<Field<?>> input){
		LinkedHashMap<String, Field<?>> output = new LinkedHashMap<>();
		if(input == null){
			return output;
		}
		for(Field<?> field : input){
			output.put(field.getName(), field);
		}
		return output;
	}
	
	private void attachObserversToFields(){
		for(Field<?> field : fields.values()){
			field.addObserver(this);
		}
	}

//	/**
//	 * For comparables
//	 */
//	public int compareTo(Attribute<T> targetAttribute){
//		return this.getValue().compareTo(targetAttribute.getValue());
//	}
	
	/**
	 * Returns string to represent values in testing
	 */
	public String toString(){
		String s = "attribute: " + getName();
//		for(Field<?> f : this){
//			s += "\n\t" + f.toString();
//		}
		return s;
	}
	
	@Override
	public int hashCode(){
		return getName().hashCode();
	}

	/**
	 * This method takes a newly modified value and updates the corresponding fields to encompass the values.
	 * @param value
	 * @return
	 */
	protected abstract List<Field<?>> updateFields(T value);
	
	/**
	 * This method takes in a map of newly modified fields and updates the corresponding value to account for the changes.
	 * @param fields
	 * @return
	 */
	protected abstract T updateValue(LinkedHashMap<String, Field<?>> fields);


	public int compareTo(Attribute<?> targetAttribute) {
		// TODO Auto-generated method stub
		return 0;
	}

}