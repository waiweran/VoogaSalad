package general.fields.valuefields;

import general.exceptions.IncorrectFieldException;
import general.fields.ValueField;

public class IntegerValueField extends ValueField<Integer> {

	public IntegerValueField(String name, Integer defaultValue) {
		super(name, defaultValue);
	}

	@Override
	protected Integer getValueFromString(String newValue) throws IncorrectFieldException {
		try{
			return Integer.parseInt(newValue);
		}
		catch(NullPointerException | NumberFormatException e){
			throw new IncorrectFieldException("Field input must be INTEGER");
		}
	}
}
