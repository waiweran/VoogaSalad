package general.actions.strategies.movement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import general.Vector;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.fields.Field;
import general.fields.valuefields.DoubleValueField;
import general.keypress.ButtonList;

/**
 * Moves towards an entity - MovementStrategy
 * @author AndresLebbos
 */
public class TowardsMovement extends FollowTowardsStrategy{
	private Vector target;
	
	/**
	 * Specifies a movement strategy towards a specific point.
	 * @param targ ignored, used for other actions
	 * @param frequency ignored because it happens every iteration
	 */
	public TowardsMovement(int waitPeriod, Vector targ) {
		super(waitPeriod);
		target = targ;
	}

	public TowardsMovement(int waitPeriod) {
		this(waitPeriod, Vector.ORIGIN);
	}

	/**
	 * Returns a consumer that moves the object in direction of the target defined when this strategy was created.
	 */
	@Override
	public Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference) {
		return victim -> moveTowards(victim, target);
	}
	
	@Override
	public Collection<? extends Field<?>> getFields() {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new DoubleValueField("Target x", target.getX()));
		fields.add(new DoubleValueField("Target y", target.getY()));
		return fields;
	}

	@Override
	public void setFields(LinkedHashMap<String, Field<?>> fields) {
		target = new Vector(fields.get("Target x").getDouble(), fields.get("Target y").getDouble());
	}
}