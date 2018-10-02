package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.entities.EntityType;
import general.fields.Field;
import general.fields.valuefields.StringValueField;

/**
 * This attribute can be used to label an entity with some tag
 * @author DhruvKPatel
 *
 */
public class Tag extends Attribute<EntityType>{
	private static final EntityType DEFAULT_VALUE = new EntityType("", "", "");

	public Tag(String name, EntityType value) {
		super(name, value);
	}
	
	public Tag(String name){
		super(name, DEFAULT_VALUE);
	}
	
	public Tag(Tag old){
		super(old.getName(), new EntityType(old.getValue()));
	}

	@Override
	protected List<Field<?>> updateFields(EntityType value) {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new StringValueField("name", value.getName()));
		fields.add(new StringValueField("type", value.getType()));
		fields.add(new StringValueField("sub-type", value.getSubType()));
		return fields;		
	}

	@Override
	protected EntityType updateValue(LinkedHashMap<String, Field<?>> fields) {
		String name = fields.get("name").getString();
		String type = fields.get("type").getString();
		String subType = fields.get("sub-type").getString();
		return new EntityType(name, type, subType);
	}

	@Override
	public int compareTo(EntityType o) {
		return 0;
	}
}
