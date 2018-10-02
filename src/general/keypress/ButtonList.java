package general.keypress;

import java.util.ArrayList;
import java.util.List;

import general.attributes.KeyBindings;
import general.entities.GameEntity;
import javafx.scene.input.KeyCode;

/**
 * Container to hold all active keys
 * @author Gordon
 *
 */
public class ButtonList {
	List<KeyCode> activeKeys;
	
	public ButtonList(){
		activeKeys = new ArrayList<KeyCode>();
	}
	
	public void add(KeyCode kc){
		if(activeKeys.contains(kc)){
			return;
		}
		activeKeys.add(kc);
	}
	
	public void delete(KeyCode kc){
		if(!activeKeys.contains(kc)){
			return;
		}
		activeKeys.remove(kc);
	}
	
	public boolean contains(KeyCode kc){
		return activeKeys.contains(kc);
	}
	
	public boolean containsCommand(KeyCommand cmd, GameEntity entityToCommand){
		KeyBindings kB = entityToCommand.getAttribute("KeyBindings", KeyBindings.class);
		KeyMap myMap = kB.getValue();
		
		for(KeyCode code : activeKeys){
			if(myMap.getCommand(code) == cmd)
				return true;
		}
		
		return false;
	}

}
