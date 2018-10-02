package general.fields;

import general.exceptions.IncorrectFieldException;

/**
 * This field defines a value within a pre-defined range. This is particularly useful for sliders,
 * but it can be used for any Double that must be bounded.
 * @author DhruvKPatel
 *
 */
public class RangeField extends Field<Double>{
	
	private double minValue;
	private double maxValue;
	
	public RangeField(String name, double defaultValue, double minValue, double maxValue){
		super(name, defaultValue);
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	protected Double getValueFromString(String newValue) throws IncorrectFieldException {
		double value;
		try{
			value = Double.parseDouble(newValue);
		}
		catch(NullPointerException | NumberFormatException e){
			throw new IncorrectFieldException("Field input must be DOUBLE");
		}
		
		if(value < minValue || value > maxValue){
			throw new IncorrectFieldException(String.format("Field value must be within range (%f,%f)", minValue, maxValue));
		}
		
		
		return value;
	}
	
	public double getMin() {
		return minValue;
	}
	
	public double getMax() {
		return maxValue;
	}
}
