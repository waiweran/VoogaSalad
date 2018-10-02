package general.fields.valuefields;

import general.exceptions.IncorrectFieldException;
import general.fields.ValueField;

/**
 * Field that is supposed to contain a Double
 * @author DhruvKPatel
 *
 */
public class DoubleValueField extends ValueField<Double> {

	public DoubleValueField(String name, Double defaultValue) {
		super(name, defaultValue);
	}

	@Override
	protected Double getValueFromString(String newValue) throws IncorrectFieldException {
		try{
			return Double.parseDouble(newValue);
		}
		catch(NullPointerException | NumberFormatException e){
			throw new IncorrectFieldException("Field input must be DOUBLE");
		}
	}

}
