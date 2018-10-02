package play.model.engine;

import general.actions.strategies.Strategy;
import general.attributes.MovementAction;
import general.entities.GameEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;
import general.storage.GameState;


/**
 * 
 * Sets entity velocities given an entity's movement strategy
 * 
 * @author DhruvKPatel
 *
 */
public class VelocityEngine extends Engine {
	
	public VelocityEngine(){
		
	}
	
	/**
	 * Checks movement pattern of entities and changes entity's velocity
	 */
	@Override
	public void step(GameState state, ButtonList activeKeys, double deltaTime) {
		Spawner spawner = new Spawner(state);
		
		for(GameEntity entity : state){
			Strategy movement = entity.getAttribute("Movement", MovementAction.class).getValue();
			entity.run(movement.getAction(state.getReadOnlyEntityValues(), activeKeys, spawner, deltaTime));
		}		
	}

}
