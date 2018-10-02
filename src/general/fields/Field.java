package general.fields;
import java.util.List;
import java.util.Observable;

import general.exceptions.IncorrectFieldException;

/**
 * Defines a parameter of an attribute in a way that is readable in editor
 * 
 * For example: The "Position" attribute has two Fields ("x" and "y")
 * 
 * @author Dhruv K Patel
 * @author Justin Wang
 *
 * @param <T>
 */
public abstract class Field<T> extends Observable {

	private String name;
	private T value;
	
	public Field(String name, T defaultValue){
		this.name = name;
		this.value = defaultValue;
	}
	
	/**
	 * Gets name of field
	 * @return String name of field
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets current setting of field
	 * @return String that represents field setting
	 */
	public T getValue(){
		return value;
	}

	/**
	 * Sets current value of field
	 * @throws IncorrectFieldException 
	 */
	public void setValue(String newValue) throws IncorrectFieldException{
		value = getValueFromString(newValue);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Returns a list of options for possible values to set the field to
	 * @throws IncorrectFieldException: if this does not apply to a certain field, this exception will be thrown
	 * 
	 * (If a subclass is able to give options, it must override this method)
	 */
	public List<String> getOptions() throws IncorrectFieldException{
		throw new IncorrectFieldException("Field does not have a list of options"); // This should only be thrown if there is an error in the front-end	
	}
	
	/**
	 * This is basically the "setValue" method, except notifying observers of the
	 * field is already taken care of. 
	 * @param newValue
	 * @throws IncorrectFieldException
	 */
	protected abstract T getValueFromString(String newValue) throws IncorrectFieldException;
	
	/*
	 * Methods below type check and return objects of designated type by casting
	 * (not sure how to get around this, but if solution is found, this will be removed)
	 */
	/**
	 * Returns value as double
	 * @return
	 * @throws IncorrectFieldException if value is not an instance of Double
	 */
	public double getDouble(){
		if(value instanceof Double){
			return (double) value;
		}
		return 0;
		//throw new IncorrectFieldException("Value of field is not a Double");
	}
	
	/**
	 * Returns value as int
	 * @return
	 * @throws IncorrectFieldException if value is not an instance of Integer
	 */
	public int getInteger(){
		if(value instanceof Integer){
			return (int) value;
		}
		return 0;
		//throw new IncorrectFieldException("Value of field is not a Integer");
	}
	
	/**
	 * Returns value as boolean
	 * @return
	 * @throws IncorrectFieldException if value is not an instance of Boolean
	 */
	public boolean getBoolean(){
		if(value instanceof Boolean){
			return (boolean) value;
		}
		return false;
		//throw new IncorrectFieldException("Value of field is not a Integer");
	}
	
	/**
	 * Returns value as String Representation
	 * @return
	 * @throws IncorrectFieldException if value is not an instance of String
	 */
	public String getString(){
		return value.toString();
	}
	
	/**
	 * Returns value as string
	 */
	public String toString(){
		return "field: " + getName() + " = " + getString();
	}

	/**
	 * Returns value as "A" (generic type)
	 * @return
	 * @throws IncorrectFieldException if value is not an instance of "A" (generic type)
	 */
	public <A> A getValue(Class<A> objectClass){
		try{
			return objectClass.cast(value);
		}
		catch(Exception e){
			return null;
			//throw new IncorrectFieldException(String.format("Value of field is not a %s", objectOfSameType.getClass().toGenericString()));
		}
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Field) ? this.getValue().equals(((Field<?>) obj).getValue()) : false;
	}

}