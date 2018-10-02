package play.model.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import general.actions.strategies.collision.CollisionStatus;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;
import general.newgoal.messages.MessageFactory;
import general.storage.GameState;
import play.model.collisionmanager.CollisionChecker;
import play.model.collisionmanager.CollisionHandler;
import play.model.collisionmanager.CollisionType;
import play.model.collisionmanager.DirectCollisionChecker;
import play.model.collisionmanager.EffectCollisionChecker;
import play.model.collisionmanager.ReadOnlyEntityPair;

public class CollisionEngine extends Engine {
	
	CollisionChecker directChecker;
	CollisionChecker effectChecker;
	CollisionHandler handler;

	private MessageFactory messageFactory;
	
	public CollisionEngine(){
		directChecker = new DirectCollisionChecker();
		effectChecker = new EffectCollisionChecker();
		messageFactory = new MessageFactory();
		handler = new CollisionHandler();
	}

	@Override
	public void step(GameState state, ButtonList activeKeys, double deltaTime) {
		Spawner spawner = new Spawner(state);
		List<ReadOnlyEntityPair> allEntityPairs = makePairs(state.getReadOnlyEntityValues());
		handleCollision(directChecker, CollisionType.DIRECT, allEntityPairs, activeKeys, spawner, deltaTime);
		handleCollision(effectChecker, CollisionType.EFFECT, allEntityPairs, activeKeys, spawner, deltaTime);
	}
	
	private void handleCollision(CollisionChecker checker, CollisionType type, List<ReadOnlyEntityPair> allPairs, ButtonList activeKeys, Spawner spawner, double deltaTime){
		checker.pass(allPairs);
		Map<CollisionStatus, List<ReadOnlyEntityPair>> collisionMap = checker.getCollisionStateMap();
		handler.pass(type, collisionMap, activeKeys, spawner, deltaTime);
	}
	
	private List<ReadOnlyEntityPair> makePairs(List<ReadOnlyEntity> entities){ //TODO: make entity pairs read only

		List<ReadOnlyEntityPair> allEntityPairs = new ArrayList<>();

		for (ReadOnlyEntity entityA : entities){

			for (ReadOnlyEntity entityB : entities){

				if (!entityA.equals(entityB)){
					allEntityPairs.add(new ReadOnlyEntityPair(entityA, entityB));
				}
			}
		}
		return allEntityPairs;
	}
}
