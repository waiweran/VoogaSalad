package general.actions.strategies.collision.trigger;

import general.actions.strategies.CollisionStrategy;

/**
 * Defines a strategy that determines the behavior when entities move into the trigger zone of the object.
 * @author Andres Lebbos
 */
public abstract class TriggerStrategy extends CollisionStrategy {

	/**
	 * Defines a trigger strategy that defines the behavior of elements that move inside the trigger radius.
	 * @param tags the possible entities that can make the object trigger its behavior.
	 * @param frequency determines each x seconds that the action is triggered.
	 */
	public TriggerStrategy(int waitPeriod, int amountUses) {
		super(waitPeriod, amountUses);
	}
	
	public TriggerStrategy(int waitPeriod) {
		super(waitPeriod);
	}
}