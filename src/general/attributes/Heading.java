package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;

import general.Vector;
import general.fields.Field;
import general.fields.RangeField;

/**
 * Holds the face angle of an object as a slider
 * and restricts from -180 to 180
 * @author DhruvKPatel
 *
 */
public class Heading extends Attribute<Double> {
	private final static double DEFAULT_VALUE = 0;
	
	public Heading(String name, Double startingHeading){
		super(name, startingHeading);
	}
	
	public Heading(String name){
		super(name, DEFAULT_VALUE);
	}
	
	public Heading(Heading old){
		super(old.getName(), new Double(old.getValue()));
	}

	@Override
	protected List<Field<?>> updateFields(Double value) {
		double min = -180;
		double max = 180;
		double increment = max - min;
		
		while(value <= min) value += increment;
		while(value > max) value -= increment;
		
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new RangeField("degrees", DEFAULT_VALUE, min, max));
		return fields;
	}

	@Override
	protected Double updateValue(LinkedHashMap<String, Field<?>> fields) {
		return fields.get("degrees").getDouble();
	}
	
	@Override
	protected void attributeUpdate(Observable o, Object arg){
		if (o instanceof Velocity){
			Vector velocity = ((Velocity) o).getValue();
			this.setValue(velocity.angle());
		}
	}

	@Override
	public int compareTo(Double o) {
		return 0;
	}
}
