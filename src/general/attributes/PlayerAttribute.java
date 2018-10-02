package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.fields.BooleanField;
import general.fields.Field;

public class PlayerAttribute extends Attribute<Boolean> {
	
	private static final Boolean DEFAULT_VALUE = false;

	public PlayerAttribute(String name, Boolean value) {
		super(name, value);
	}
	
	public PlayerAttribute(String name) {
		super(name, DEFAULT_VALUE);
	}

	@Override
	public int compareTo(Boolean o) {
		return this.getValue().compareTo(o);
	}

	@Override
	protected List<Field<?>> updateFields(Boolean value) {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new BooleanField("Is Playable Character", value));
		return fields;		

	}

	@Override
	protected Boolean updateValue(LinkedHashMap<String, Field<?>> fields) {
		return fields.get("Is Playable Character").getBoolean();
	}

}
