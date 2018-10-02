package general.keypress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.input.KeyCode;

/**
 * This class encapsulates the link between KeyCodes and
 * commands that can be used to control the game.
 * 
 * Note: An enum for JavaFX is used in this class solely
 * to reference key values. This is to reduce unecessarily 
 * repeated code.
 * 
 * @author DhruvKPatel
 */
public class KeyMap implements Comparable<KeyMap>{
	private Map<KeyCode, KeyCommand> keys = new HashMap<>();
	private ArrowKeys movementKeys;
	private List<KeyCode> actions;
	
	/**
	 * Constructs a KeyMap given a set of Arrow Keys and Action keys
	 * @param movement
	 * @param action1
	 * @param action2
	 * @param action3
	 */
	public KeyMap(ArrowKeys movement, KeyCode action1, KeyCode action2, KeyCode action3, KeyCode action4){
		this.movementKeys = movement;
		this.actions = new ArrayList<>(Arrays.asList(new KeyCode[]{action1, action2, action3, action4}));
		
		keys.put(movement.UP, KeyCommand.UP);
		keys.put(movement.DOWN, KeyCommand.DOWN);
		keys.put(movement.LEFT, KeyCommand.LEFT);
		keys.put(movement.RIGHT, KeyCommand.RIGHT);		
		
		System.out.println(String.format("Actions-- %s\t%s\t%s\t%s\t", action1.name(), action2.name(), action3.name(), action4.name()));
		keys.put(action1, KeyCommand.USE_ITEM);
		keys.put(action2, KeyCommand.SWITCH);
		keys.put(action3, KeyCommand.ATTACK);
		keys.put(action4, KeyCommand.INTERACT);
	}
	
	/**
	 * Returns the proper Command Value from a keycode input
	 * @param code
	 * @return
	 */
	public KeyCommand getCommand(KeyCode code){
		return keys.get(code);
	}
	
	/**
	 * Returns current set of movement keys
	 * @return
	 */
	public ArrowKeys getMovementKeys(){
		return movementKeys;
	}
	
	/**
	 * Returns action from index
	 * Note: if action doesn't exist, returns null
	 * @param action_number
	 * @return
	 */
	public SelectableKeyCode getAction(int action_number){
		if(action_number == 0) return new SelectableKeyCode(null);
		return new SelectableKeyCode(actions.get(action_number - 1));
	}

	@Override
	public int compareTo(KeyMap o) {
		return 0; // All KeyMap objects are equal in order
	}
	
	public String toString(){
		return actions.toString();
	}
}
