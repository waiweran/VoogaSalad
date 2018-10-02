package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.Vector;
import general.fields.Field;
import general.fields.valuefields.DoubleValueField;

/**
 * Stores entity's (X,Y) position as Doubles on game board
 * @author DhruvKPatel
 *
 */
public class Location extends Attribute<Vector> {
	private final static Vector DEFAULT_VALUE = Vector.ORIGIN;
	
	public Location(String name, Vector value) {
		super(name, value);
	}
	
	public Location(String name){
		super(name, DEFAULT_VALUE);
	}
	
	public Location(Location old){
		super(old.getName(), new Vector(old.getValue()));
	}

	@Override
	protected List<Field<?>> updateFields(Vector value) {
		List<Field<?>> fields = new ArrayList<>();
		
		fields.add(new DoubleValueField("x", value.getX()));
		fields.add(new DoubleValueField("y", value.getY()));
		
		return fields;		
	}

	@Override
	protected Vector updateValue(LinkedHashMap<String, Field<?>> fields) {
		return new Vector(fields.get("x").getDouble(), fields.get("y").getDouble());
	}

	@Override
	public int compareTo(Vector o) {
		return 0;
	}
}
