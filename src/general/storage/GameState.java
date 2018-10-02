package general.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import general.Id;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.gameobjects.Background;
import general.internalAPI.GameStateInternal;
import general.newgoal.Goal;

/**
 * Class that holds all necessary information for a game
 * 
 * @author Gordon
 *
 */
public class GameState extends Observable implements GameStateInternal, Iterable<GameEntity> {
	private HashMap<Id, GameEntity> myEntities;
	private ObservableList<Background> myBackgrounds;
	private Goal root;

	public GameState(List<GameEntity> gameObjects, List<Background> backgrounds, Goal goalIn) {
		myEntities = new HashMap<Id, GameEntity>();
		for (GameEntity e : gameObjects) {
			e.resetObservers();
			myEntities.put(e.getId(), e);
		}
		myBackgrounds = new ObservableList<Background>(backgrounds);
		root = goalIn;
	}

	@Override
	public Map<Id, GameEntity> getEntities() {
		return myEntities;
	}

	public List<GameEntity> getEntityValues(){
		updateEntities();
		ArrayList<GameEntity> values = new ArrayList<>(myEntities.values());
		return values;
	}
	

	private void updateEntities() {
		HashMap<Id, GameEntity> activeEntities = new HashMap<>();
		for (Id id : myEntities.keySet()){
			if (myEntities.get(id).isActive()){
				activeEntities.put(id, myEntities.get(id));
			}
		}
		myEntities = activeEntities;
	}
	
	public List<ReadOnlyEntity> getReadOnlyEntityValues() {
		List<ReadOnlyEntity> values = new ArrayList<>(myEntities.values());
		return values;
	}

	@Override
	public Iterator<GameEntity> iterator() {
		ArrayList<GameEntity> copies = new ArrayList<>();
		copies.addAll(myEntities.values());
		return copies.iterator();
	}

	public void addEntity(GameEntity gameEntityIn) {
		myEntities.put(gameEntityIn.getId(), gameEntityIn);
		setChanged();
		notifyObservers(gameEntityIn);
	}
	
	public ObservableList<Background> getBackgrounds(){
		return myBackgrounds;
	}

	public Goal getRootGoal(){
		return this.root;
	}


	
}
