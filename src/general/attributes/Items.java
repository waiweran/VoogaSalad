package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.fields.Field;
import general.fields.valuefields.IntegerValueField;
import general.items.ItemHolder;

/**
 * Attribute that deals with Items an Entity holds
 * @author Alex
 * @author DhruvKPatel
 */
public class Items extends Attribute<ItemHolder> {

	private static final ItemHolder DEFAULT_ITEMHOLDER = new ItemHolder();
	
	public Items(String name, ItemHolder value) {
		super(name, value);
	}
	
	public Items(String name){
		super(name, DEFAULT_ITEMHOLDER);
	}
	
	public Items(Items old){
		super(old.getName(), old.getValue());
	}

	@Override
	protected List<Field<?>> updateFields(ItemHolder value) {
		List<Field<?>> fields = new ArrayList<>();
	
		fields.add(new IntegerValueField("Inventory Size", value.getInventorySize()));
		
		return fields;
	}

	@Override
	protected ItemHolder updateValue(LinkedHashMap<String, Field<?>> fields) {
		return new ItemHolder(fields.get("Inventory Size").getInteger());
	}

	@Override
	public int compareTo(ItemHolder o) {
		return 0;
	}
}
