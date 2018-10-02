package general.keypress;

import java.util.Arrays;
import java.util.List;

import general.fields.selectionfields.Selectable;
import javafx.scene.input.KeyCode;

/**
 * This represents different options for arrow keys used in the game
 * @author DhruvKPatel
 *
 */
public enum ArrowKeys implements Selectable {
	NONE (null, null, null, null, "None"),
	DEFAULT (KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, "Arrow Keys"),
	NUMPAD (KeyCode.NUMPAD8, KeyCode.NUMPAD5, KeyCode.NUMPAD4, KeyCode.NUMPAD6, "Numpad (8456)"),
	WASD (KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, "WASD"),
	UHJK (KeyCode.U, KeyCode.J, KeyCode.H, KeyCode.K, "UHJK");
	
	public KeyCode UP, DOWN, LEFT, RIGHT;
	private String representation;
	
	ArrowKeys(KeyCode up, KeyCode down, KeyCode left, KeyCode right, String representation){
		this.UP = up;
		this.DOWN = down;
		this.LEFT = left;
		this.RIGHT = right;	
		this.representation = representation;
	}
	
	@Override
	public String getStringRepresentation() {
		return representation;
	}
	
	public static ArrowKeys getKeysFromString(String representation){
		for(ArrowKeys keys : values()){
			System.out.println(representation + " " + keys.representation);
			if(keys.representation.equals(representation)) return keys;
		}
		return null;
	}
	
	public static List<ArrowKeys> getOptions(){
		return Arrays.asList(values());
	}

}
