package general.fields;

import java.util.ArrayList;
import java.util.List;

import general.exceptions.IncorrectFieldException;
import general.fields.selectionfields.Selectable;

/**
 * Field in which a user must select a value from 
 * @author DhruvKPatel
 * @param <T>
 */
public class SelectionField<T extends Selectable> extends Field<T>{
	List<T> options;
	
	public SelectionField(String name, T defaultValue, List<T> options) {
		super(name, defaultValue);
		this.options = options;
	}
	
	/**
	 * Returns a "T" type value given a String identifier.
	 * Note: This string identifier is taken from the `toString()` method.
	 * This means that any value in a selection field must be represent by its `toString()` method.
	 */
	@Override
	protected T getValueFromString(String newValue) throws IncorrectFieldException {
		for(T option : options){
			if(option.getStringRepresentation().equals(newValue)) return option;
		}
		throw new IncorrectFieldException("Selected value is not within list of options");
	}
	
	/**
	 * Returns a list of options for possible values to set the field to
	 * @throws IncorrectFieldException: if this does not apply to a certain field, this exception will be thrown
	 */
	public List<String> getOptions() throws IncorrectFieldException{
		List<String> optionStrings = new ArrayList<>();
		options.forEach((option) -> {
			optionStrings.add(option.getStringRepresentation());
		});
		return optionStrings;
	}
}
