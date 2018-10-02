package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.fields.Field;
import general.fields.RangeField;

/**
 * Designates the size (width) of an entity
 * @author DhruvKPatel
 */
public class Size extends Attribute<Double> {
	private static final double DEFAULT_SIZE = 50;
	
	public Size(String name, Double value) {
		super(name, value);
	}
	
	public Size(String name){
		this(name, DEFAULT_SIZE);		
	}

	@Override
	protected List<Field<?>> updateFields(Double value) {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new RangeField("diameter", value, 0, 1000));
		return fields;
	}

	@Override
	protected Double updateValue(LinkedHashMap<String, Field<?>> fields) {
		return fields.get("diameter").getDouble();
		
	}

	@Override
	public int compareTo(Double o) {
		return 0;
	}
}
