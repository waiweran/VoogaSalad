package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import general.fields.Field;
import general.fields.valuefields.IntegerValueField;

/**
 * Represents health of an entity
 * @author DhruvKPatel
 * @author Gordon
 */
public class Health extends Attribute<Integer> {
	private final static int DEFAULT_HEALTH = 100;

	public Health(String name) {
		super(name, DEFAULT_HEALTH);
	}
	
	public Health(String name, Integer value) {
		super(name, value);
	}
	
	public Health(Health old){
		super(old.getName(), new Integer(old.getValue()));
	}

	@Override
	protected List<Field<?>> updateFields(Integer value) {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new IntegerValueField("health", value));
		return fields;
	}

	@Override
	protected Integer updateValue(LinkedHashMap<String, Field<?>> fields) {
		return fields.get("health").getInteger();
		//TODO: Tell Nathaniel that when you backspace the text field to empty, it throws an exception
		// because it doesn't recognize an Integer
	}

	@Override
	public int compareTo(Integer o) {
		return 0;
	}
}
