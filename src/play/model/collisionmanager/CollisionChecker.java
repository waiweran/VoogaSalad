package play.model.collisionmanager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import general.actions.strategies.collision.CollisionStatus;
public abstract class CollisionChecker {
	
	private List<ReadOnlyEntityPair> previousCollidingPairs;
	private List<ReadOnlyEntityPair> currentCollidingPairs;
	private Map<CollisionStatus, List<ReadOnlyEntityPair>> collisionStateMap;
	
	public CollisionChecker(){
		previousCollidingPairs = new ArrayList<>();
		currentCollidingPairs = new ArrayList<>();
		collisionStateMap = new HashMap<>();
	}
	
	public void pass(List<ReadOnlyEntityPair> allPairs){
		previousCollidingPairs = new ArrayList<>(currentCollidingPairs);
		currentCollidingPairs.clear();
		determineCollisionPairs(allPairs);
		filterCollisionPairs();
	}
	public Map<CollisionStatus, List<ReadOnlyEntityPair>> getCollisionStateMap(){
		return collisionStateMap;
	}
	
	protected abstract void determineCollisionPairs(List<ReadOnlyEntityPair> allPairs);
	
	protected List<ReadOnlyEntityPair> getCurrentCollidingPairs(){
		return currentCollidingPairs;
	}
	
	private void filterCollisionPairs(){
		findExitCollisions();
		findEnterCollisions();
		findStayCollisions();
	}
	
	private void findExitCollisions(){
		List<ReadOnlyEntityPair> exitPairs = new ArrayList<>(previousCollidingPairs);
		exitPairs.removeAll(currentCollidingPairs);
		collisionStateMap.put(CollisionStatus.EXIT, exitPairs);
	}
	
	private void findEnterCollisions(){
		List<ReadOnlyEntityPair> enterPairs = new ArrayList<>(currentCollidingPairs);
		enterPairs.removeAll(previousCollidingPairs);
		collisionStateMap.put(CollisionStatus.ENTER, enterPairs);
	}
	private void findStayCollisions(){
		List<ReadOnlyEntityPair> stayPairs = new ArrayList<>(currentCollidingPairs);
		stayPairs.retainAll(previousCollidingPairs);
		collisionStateMap.put(CollisionStatus.STAY, stayPairs);
	}
	
}