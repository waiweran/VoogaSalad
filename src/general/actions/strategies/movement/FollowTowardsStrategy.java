package general.actions.strategies.movement;

import general.Vector;
import general.entities.GameEntity;

public abstract class FollowTowardsStrategy extends MovementStrategy{

	public FollowTowardsStrategy(int waitPeriod) {
		super(waitPeriod);
	}

	protected GameEntity moveTowards(GameEntity victim, Vector target){
		double vel = getVelocity(victim).getValue().magnitude();
		double angle = angleBetween(target, getLocation(victim).getValue());
		getVelocity(victim).setValue(new Vector(vel * Math.cos(angle), vel * Math.sin(angle)));
		return victim;
	}

}
