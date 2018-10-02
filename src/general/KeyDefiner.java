package general;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;

/**
 * Class that creates the map with definitions of KeyCode to string action associated with that KeyCode
 * @author Harry Liu
 * @author salo
 * @version 04-01-17
 */
public class KeyDefiner {

	private Map<KeyCode, String> inputMap;	
	
	public KeyDefiner(){
		inputMap = new HashMap<KeyCode, String>();
		KeyInputDefinition(KeyCode.M, "PopupMenu"); //example. Formatting can change -> Adds in one definition for PopupMenu
	}
	
	/**
	 * Adds a key and value pair to the inputMap (KeyCode, String)
	 * @param keyCode
	 * @param name
	 */
	public void KeyInputDefinition(KeyCode keyCode, String name){
			inputMap.put(keyCode, name);	
	}
	
	/**
	 * Gets the name associated with the keycode
	 * @param keyCode
	 * @return String name
	 */
	public String getName(KeyCode keyCode){
		return inputMap.get(keyCode);
	}
	
}
