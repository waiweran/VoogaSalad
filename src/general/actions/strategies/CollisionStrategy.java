package general.actions.strategies;

import java.util.List;
import java.util.function.Consumer;

import general.actions.strategies.collision.Collidable;
import general.actions.strategies.collision.CollisionStatus;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

public  abstract class CollisionStrategy extends Strategy implements Collidable{
	
	private CollisionStatus status;
	
	public CollisionStrategy(double waitPeriod, int amountUses) {
		super(waitPeriod, amountUses);
		status = CollisionStatus.NONE;
	}
	
	public CollisionStrategy(double waitPeriod) {
		super(waitPeriod);
		status = CollisionStatus.NONE;
	}

	public void setType(CollisionStatus type){
		this.status = type;
	}
	
	private void onCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff){
		if (status == CollisionStatus.ENTER){
			enterCollision(actor, recipient, activeKeys, spawner, timeDiff);
		}
		else if (status == CollisionStatus.EXIT){
			exitCollision(actor, recipient, activeKeys, spawner, timeDiff);
		}
		else if (status == CollisionStatus.STAY) {
			stayCollision(actor, recipient, activeKeys, spawner, timeDiff);
		}
	}
	
	@Override
	protected Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		if (entities.size() != 1){
			throw new IllegalArgumentException(this.getErrorsResource().getString("CollisionArg"));
		}
		return recipient -> onCollision(entities.get(0), recipient, activeKeys, spawner, timeDiff);
	}

}
