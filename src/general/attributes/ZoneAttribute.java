     package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;

import general.Vector;
import general.fields.Field;
import general.fields.valuefields.DoubleValueField;
import play.model.collisionmanager.Zone;

/**
 * Represents a Zone as an Attribute (radius and x,y location)
 * @author DhruvKPatel
 */
public class ZoneAttribute extends Attribute<Zone> {
	
	private static final Zone DEFAULT_ZONE = new Zone(new Vector(0, 0), 25);

	public ZoneAttribute(String name, Zone value) {
		super(name, value);
	}
	
	public ZoneAttribute(String name){
		super(name, DEFAULT_ZONE);
	}
	
	public ZoneAttribute(ZoneAttribute old){
		this(old.getName(), new Zone(old.getValue()));
	}

	@Override
	protected List<Field<?>> updateFields(Zone value) {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new DoubleValueField("radius", value.getRadius()));
		fields.addAll(new Location("", value.getOffset()).getFieldsList());
		return fields;		
	}

	@Override
	protected Zone updateValue(LinkedHashMap<String, Field<?>> fields) {
		Vector offset = new Vector(fields.get("x").getDouble(), fields.get("y").getDouble());
		double radius = fields.get("radius").getDouble();
		return new Zone(offset, radius);
	}
	
	@Override
	protected void attributeUpdate(Observable o, Object arg){
		//do nothing
	}

	@Override
	public int compareTo(Zone o) {
		return 0;
	}
}
