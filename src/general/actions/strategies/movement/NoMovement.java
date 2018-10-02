package general.actions.strategies.movement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.fields.Field;
import general.keypress.ButtonList;

/**
 * Defines a movement strategy that doesn't move the object.
 * @author Andres Lebbos
 */
public class NoMovement extends MovementStrategy{
	
	/**
	 * Specifies a movement strategy that doesn't move.
	 * @param tags ignored, used for other actions
	 * @param frequency ignored because it happens every iteration
	 */
	public NoMovement(int waitPeriod) {
		super(waitPeriod);
	}

	/**
	 * Returns an empty consumer because it is not supposed to stay at same position.
	 */
	@Override
	
	public Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference) {
		return victim -> {};
	}
	
	@Override
	public Collection<? extends Field<?>> getFields() {
		return new ArrayList<>();
	}

	@Override
	public void setFields(LinkedHashMap<String, Field<?>> fields) {}
}