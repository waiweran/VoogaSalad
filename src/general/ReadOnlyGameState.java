package general;

import java.util.Collection;

import general.entities.GameEntity;

public interface ReadOnlyGameState {
	
	public Collection<GameEntity> getAll();
	
	public Collection<GameEntity> getAllWithTag(String tag);
	
}
