package play.model.collisionmanager;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import general.actions.strategies.CollisionStrategy;
import general.actions.strategies.collision.CollisionStatus;
import general.attributes.CollisionAction;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

public class CollisionHandler {
	
	private ButtonList activeKeys;
	private double deltaTime;
	private Spawner spawner;
	
	public CollisionHandler(){
		activeKeys = new ButtonList();
		deltaTime = 0;
	}
	
	public void pass(CollisionType type, Map<CollisionStatus, List<ReadOnlyEntityPair>> collisionStateMap, ButtonList activeKeys, Spawner spawner, double deltaTime){
		this.activeKeys = activeKeys;
		this.deltaTime = deltaTime;
		this.spawner = spawner;
		for (CollisionStatus status : collisionStateMap.keySet()){
			Stream<ReadOnlyEntityPair> collisionStream = collisionStateMap.get(status).parallelStream();
			collisionStream.forEach(pair -> handleCollision(pair, status, type));
		}
	}
	
	private void handleCollision(ReadOnlyEntityPair collidingPair, CollisionStatus status, CollisionType type){
		ReadOnlyEntity recipient = collidingPair.getFirst();
		ReadOnlyEntity actor = collidingPair.getSecond();
		CollisionStrategy strategy = (CollisionStrategy) actor.getReadOnlyAttribute(type.toString(), CollisionAction.class).getValue();
		strategy.setType(status);
		recipient.run(strategy.getAction(actor.getReadOnlyAsList(), activeKeys, spawner, deltaTime));
	}
	
	private void sendMessage(ReadOnlyEntityPair collidingPair, CollisionStatus type){
		//TODO
	}

}
