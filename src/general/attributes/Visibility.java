package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.fields.BooleanField;
import general.fields.Field;

public class Visibility extends Attribute<Boolean> {
	
	private static final Boolean DEFAULT_VALUE = true;

	public Visibility(String name, Boolean value) {
		super(name, value);
	}
	
	public Visibility(String name) {
		super(name, DEFAULT_VALUE);
	}

	@Override
	public int compareTo(Boolean o) {
		return this.getValue().compareTo(o);
	}

	@Override
	protected List<Field<?>> updateFields(Boolean value) {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new BooleanField("Visible", value));
		return fields;		

	}

	@Override
	protected Boolean updateValue(LinkedHashMap<String, Field<?>> fields) {
		return fields.get("Visible").getBoolean();
	}

}
