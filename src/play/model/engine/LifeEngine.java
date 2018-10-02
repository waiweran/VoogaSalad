package play.model.engine;

import general.attributes.Health;
import general.entities.GameEntity;
import general.keypress.ButtonList;
import general.storage.GameState;

/**
 * Deactivates death objects.
 * @author AndresLebbos
 */
public class LifeEngine extends Engine{

	@Override
	public void step(GameState state, ButtonList activeKeys, double deltaTime) {
		for(GameEntity thing : state){
			if(thing.getAttribute("Health", Health.class).getValue() <= 0){
				thing.getAttribute("Health", Health.class).setValue(0);
				thing.setActive(false);
			}
		}
	}
}
