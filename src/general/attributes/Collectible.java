package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.fields.BooleanField;
import general.fields.Field;

public class Collectible extends Attribute<Boolean> implements Comparable<Boolean> {

	public Collectible(String name, Boolean value) {
		super(name, value);
	}
	
	public Collectible(String name){
		super (name, true);
	}

	@Override
	protected List<Field<?>> updateFields(Boolean value) {
		List<Field<?>> fields = new ArrayList<Field<?>>();
		fields.add(new BooleanField("Collectible", value));
		return fields;
	}

	@Override
	protected Boolean updateValue(LinkedHashMap<String, Field<?>> fields) {
		return fields.get("Collectible").getValue(Boolean.class);
	}


	@Override
	public int compareTo(Boolean o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
