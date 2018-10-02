package general.fields;

import general.exceptions.IncorrectFieldException;

/**
 * This class is used to represent a field with a check box.
 * 
 * Set value using strings: "true" and "false"
 * 
 * @author DhruvKPatel
 *
 */
public class BooleanField extends Field<Boolean> {

	public BooleanField(String name, Boolean defaultValue) {
		super(name, defaultValue);
	}

	@Override
	protected Boolean getValueFromString(String newValue) throws IncorrectFieldException {
		try{
			return Boolean.parseBoolean(newValue);
		}
		catch(NullPointerException | NumberFormatException e){
			throw new IncorrectFieldException("Field input must be BOOLEAN"); // should not happen if front-end code is correct
		}
	}

}
