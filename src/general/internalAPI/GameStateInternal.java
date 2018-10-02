package general.internalAPI;

import java.util.Map;

import general.Id;
import general.entities.GameEntity;

public interface GameStateInternal {
	public Map<Id, GameEntity> getEntities();
}
