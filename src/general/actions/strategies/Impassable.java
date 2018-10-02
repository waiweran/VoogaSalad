package general.actions.strategies;

import java.util.HashMap;
import java.util.Map;

import general.Vector;
import general.attributes.Velocity;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;
import play.model.collisionmanager.CollisionCalculator;

public class Impassable extends CollisionStrategy {

	CollisionCalculator collisionCalculator;
	
	public Impassable(int waitPeriod) {
		super(waitPeriod);
		collisionCalculator = new CollisionCalculator();
	}

	@Override
	public void enterCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		Vector recipientVelocity = recipient.getAttribute("Velocity", Velocity.class).getValue();
		Vector newVelocity = recipientVelocity;
		if (collisionCalculator.isMovingTowardsCollision(actor, recipient.getReadOnlyCopy())){
			newVelocity = collisionCalculator.getParallelVelocity(actor, recipient.getReadOnlyCopy());
		}
		recipient.getAttribute("Velocity", Velocity.class).setValue(newVelocity);
	}

	@Override
	public void stayCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		enterCollision(actor, recipient, activeKeys, spawner, timeDiff);
	}

	@Override
	public void exitCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		// do nothing
	}

	@Override
	public String getStringRepresentation() {
		return "Impassable";
	}
}
