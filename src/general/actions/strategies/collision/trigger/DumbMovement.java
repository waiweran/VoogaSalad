package general.actions.strategies.collision.trigger;

import general.actions.strategies.movement.FollowMovement;
import general.actions.strategies.movement.MovementStrategy;
import general.attributes.MovementAction;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

public class DumbMovement extends MovementTrigger {

	private MovementStrategy saved;

	public DumbMovement(int waitPeriod) {
		super(waitPeriod);
	}
	
	@Override
	public void enterCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		saved = (MovementStrategy) recipient.getAttribute("Movement", MovementAction.class).getValue();
		recipient.getAttribute("Movement", MovementAction.class).setValue(new FollowMovement(0, actor));
		}

	@Override
	public void stayCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) { /* DO NOTHING */	}

	@Override
	public void exitCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		recipient.getAttribute("Movement", MovementAction.class).setValue(saved);		
	}

}
