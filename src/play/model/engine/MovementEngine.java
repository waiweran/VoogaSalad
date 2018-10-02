package play.model.engine;

import general.Vector;
import general.attributes.Location;
import general.attributes.Velocity;
import general.entities.GameEntity;
import general.keypress.ButtonList;
import general.storage.GameState;

/**
 * 
 * This class moves objects by their velocity
 * 
 * @author DhruvKPatel
 *
 */
public class MovementEngine extends Engine {

	/**
	 * Moves objects by their velocity
	 */
	@Override
	public void step(GameState state, ButtonList activeKeys, double deltaTime) {
		
		for(GameEntity thing : state){
			
			Vector velocity = thing.getAttribute("Velocity", Velocity.class).getValue();
			Location position = thing.getAttribute("Location", Location.class);
			position.setValue(position.getValue().add(velocity.scalarMultiply(deltaTime)));
			//TODO: Check location attribute and send message
			
		}
	}

}
