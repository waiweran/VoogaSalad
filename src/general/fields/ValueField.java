package general.fields;

/**
 * Defines an Attribute field from a single text input
 * 
 * @author DhruvKPatel
 *
 */
public abstract class ValueField<T> extends Field<T>{
	
	public ValueField(String name, T defaultValue){
		super(name, defaultValue);
	}	
}
