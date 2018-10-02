package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.Vector;
import general.fields.Field;
import general.fields.valuefields.DoubleValueField;

/**
 * Stores velocity (pixels/sec) of entity
 * @author DhruvKPatel
 *
 */
public class Velocity extends Attribute<Vector> {
	private final static Vector DEFAULT_VALUE = Vector.ORIGIN;
	
	public Velocity(String name, Vector baseVelocity){
		super(name, baseVelocity);
	}
	
	public Velocity(String name){
		super(name, DEFAULT_VALUE);
	}
	
	public Velocity(Velocity old){
		this(old.getName(), new Vector(old.getValue()));
	}

	@Override
	protected List<Field<?>> updateFields(Vector value) {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new DoubleValueField("vx", value.getX()));
		fields.add(new DoubleValueField("vy", value.getY()));
		return fields;
	}

	@Override
	protected Vector updateValue(LinkedHashMap<String, Field<?>> fields) {
		double vx = fields.get("vx").getDouble();
		double vy = fields.get("vy").getDouble();
		return new Vector(vx, vy);
	}

	@Override
	public int compareTo(Vector o) {
		return 0;
	}
}
