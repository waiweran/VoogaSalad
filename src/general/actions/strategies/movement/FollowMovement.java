package general.actions.strategies.movement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import general.attributes.Location;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.fields.Field;
import general.keypress.ButtonList;

/**
 * Moves towards an entity - MovementStrategy
 * @author AndresLebbos
 */
public class FollowMovement extends FollowTowardsStrategy{
	private ReadOnlyEntity target;
	
	/**
	 * Specifies a movement strategy towards a specific point.
	 * @param tags ignored, used for other actions
	 * @param frequency ignored because it happens every iteration
	 */
	public FollowMovement(int waitPeriod, ReadOnlyEntity readOnlyEntity) {
		super(waitPeriod);
		target = readOnlyEntity;
	}

	/**
	 * Returns a consumer that moves the object in direction of the target defined when this strategy was created.
	 */
	@Override
	public Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference) {
		return victim -> moveTowards(victim, target.getReadOnlyAttribute("Location", Location.class).getValue());
	}

	@Override
	public Collection<? extends Field<?>> getFields() {
		return new ArrayList<>();
	}

	@Override
	public void setFields(LinkedHashMap<String, Field<?>> fields) {
		// TODO Can the user create this? How?
	}
}