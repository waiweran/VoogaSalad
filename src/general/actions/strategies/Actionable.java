package general.actions.strategies;

import java.util.function.Consumer;

import general.entities.GameEntity;

public interface Actionable {
	
	public void run(Consumer<GameEntity> action);
	
}