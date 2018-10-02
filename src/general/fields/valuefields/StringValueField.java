package general.fields.valuefields;

import general.exceptions.IncorrectFieldException;
import general.fields.ValueField;

public class StringValueField extends ValueField<String> {

	public StringValueField(String name, String defaultValue) {
		super(name, defaultValue);
	}

	@Override
	protected String getValueFromString(String newValue) throws IncorrectFieldException {
		return newValue;
	}

}
