package general.actions.strategies.collision;

import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

public interface Collidable {
	
	public void enterCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff);
	
	public void stayCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff);
	
	public void exitCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff);
	
}
