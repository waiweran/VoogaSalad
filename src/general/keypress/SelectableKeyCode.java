package general.keypress;

import java.util.ArrayList;
import java.util.List;

import general.fields.selectionfields.Selectable;
import javafx.scene.input.KeyCode;

/**
 * 
 * This class is a wrapper around the JavaFX KeyCode enum.
 * It allows the enum to implement the "Selectable" interface
 * so it can be selected by a user.
 * 
 * @author DhruvKPatel
 *
 */
public class SelectableKeyCode implements Selectable {
	
	private String name;
	private KeyCode code;
	
	/**
	 * Constructs a SelectableKeyCode from a KeyCode object
	 * @param code
	 */
	public SelectableKeyCode(KeyCode code){
		this.code = code;
		
		if(code == null){
			this.name = "None";
		}
		else{
			this.name = code.getName();
		}
	}
	
	/**
	 * Returns KeyCode representation
	 * @return
	 */
	public KeyCode getCode(){
		return code;
	}
	
	/**
	 * Returns String representation
	 * @return
	 */
	@Override
	public String getStringRepresentation() {
		return name;
	}
	
	/**
	 * Returns a list of all possible SelectableKeyCodes
	 */
	public static List<SelectableKeyCode> getOptions(){
		List<SelectableKeyCode> options = new ArrayList<>();
		for(KeyCode code : KeyCode.values()){
			options.add(new SelectableKeyCode(code));
		}
		return options;
	}

}