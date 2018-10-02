package play.model.engine;

import general.actions.strategies.Strategy;
import general.attributes.SpawnAction;
import general.entities.GameEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;
import general.storage.GameState;

public class SpawnEngine extends Engine {
	
	@Override
	public void step(GameState state, ButtonList activeKeys, double deltaTime) {
		Spawner spawner = new Spawner(state);

		for(GameEntity entity : state){
			Strategy spawnStrategy = entity.getAttribute("SpawnAction", SpawnAction.class).getValue();
			entity.run(spawnStrategy.getAction(state.getReadOnlyEntityValues(), activeKeys, spawner, deltaTime));
		}
				
	}

}
