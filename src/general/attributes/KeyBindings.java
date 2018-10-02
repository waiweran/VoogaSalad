package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.fields.Field;
import general.fields.SelectionField;
import general.keypress.ArrowKeys;
import general.keypress.KeyMap;
import general.keypress.SelectableKeyCode;
import javafx.scene.input.KeyCode;

public class KeyBindings extends Attribute<KeyMap> {

	public KeyBindings(String name, KeyMap value) {
		super(name, value);
	}
	
	public KeyBindings(String name){
		this(name, new KeyMap(ArrowKeys.NONE, KeyCode.DIGIT1, KeyCode.DIGIT2, KeyCode.DIGIT3, KeyCode.DIGIT4));
	}

	@Override
	protected List<Field<?>> updateFields(KeyMap value) {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new SelectionField<ArrowKeys>("Movement", value.getMovementKeys(), ArrowKeys.getOptions()));
		fields.add(new SelectionField<SelectableKeyCode>("Action 1", value.getAction(1), SelectableKeyCode.getOptions()));
		fields.add(new SelectionField<SelectableKeyCode>("Action 2", value.getAction(2), SelectableKeyCode.getOptions()));
		fields.add(new SelectionField<SelectableKeyCode>("Action 3", value.getAction(3), SelectableKeyCode.getOptions()));
		fields.add(new SelectionField<SelectableKeyCode>("Action 4", value.getAction(4), SelectableKeyCode.getOptions()));
		System.out.println("test>>" + value.getAction(1).getStringRepresentation());
		return fields;		
	}

	@Override
	protected KeyMap updateValue(LinkedHashMap<String, Field<?>> fields) {
		ArrowKeys movement = ArrowKeys.getKeysFromString(fields.get("Movement").getValue(ArrowKeys.class).getStringRepresentation());
		SelectableKeyCode action1 = fields.get("Action 1").getValue(SelectableKeyCode.class);
		SelectableKeyCode action2 = fields.get("Action 2").getValue(SelectableKeyCode.class);
		SelectableKeyCode action3 = fields.get("Action 3").getValue(SelectableKeyCode.class);
		SelectableKeyCode action4 = fields.get("Action 4").getValue(SelectableKeyCode.class);
//		System.out.println("hi>>" + movement.getStringRepresentation());
//		System.out.println("hi>>" + action1.getStringRepresentation());
//		System.out.println("hi>>" + action2.getStringRepresentation());
//		System.out.println("hi>>" + action3.getStringRepresentation());
//		System.out.println("hi>>" + action4.getStringRepresentation());
//		
//		System.out.println("hi>>" + action1.getCode());
//		System.out.println("hi>>" + action2.getStringRepresentation());
//		System.out.println("hi>>" + action3.getStringRepresentation());
//		System.out.println("hi>>" + action4.getStringRepresentation());
		return new KeyMap(movement, action1.getCode(), action2.getCode(), action3.getCode(), action4.getCode());
	}

	@Override
	public int compareTo(KeyMap o) {
		// TODO Auto-generated method stub
		return 0;
	}	

}
