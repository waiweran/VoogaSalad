package play.model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import general.Id;
import general.attributes.Attribute;
import general.entities.GameEntity;
import general.exceptions.ImproperFileException;
import general.gameobjects.Background;
import general.keypress.ButtonList;
import general.storage.GameState;
import general.storage.ObservableList;
import javafx.scene.input.KeyCode;
import play.externalAPI.PlayEntity;
import play.externalAPI.PlayGoal;
import play.model.engine.Engine;
import play.model.engine.EngineFactory;

/**
 * Model for the Player. Handles loading and saving a game. Also will handle all
 * game calculations and changes
 * 
 * @author Gordon
 *
 */

public class PlayModel implements play.externalAPI.PlayerModel {
	private final static String ERROR_RESOURCE = "resources.ErrorMessages";
	private final static String OBSERVER_RESOURCE = "resources.Observer";
	private final static ResourceBundle errors = ResourceBundle.getBundle(ERROR_RESOURCE);
	private final static ResourceBundle OBSERVERS = ResourceBundle.getBundle(OBSERVER_RESOURCE);

	private GameState gameState;
	private XStream mySerializer = new XStream(new DomDriver());
	private ObservableList<GameEntity> myEntities;
	private List<Background> myBackgrounds;
	private ButtonList keys;
	private List<Engine> engines;
	private Map<Id, GameEntity> activeEntities;
	
	public PlayModel(File file) {
		keys = new ButtonList();
		EngineFactory engineFactory = new EngineFactory();
		engines = engineFactory.getEngines();
		gameState = (GameState) mySerializer.fromXML(file);
		gameState.addObserver((o, arg) -> addEntity(arg));
		myEntities = new ObservableList<GameEntity>(gameState.getEntityValues());
		myBackgrounds = new ArrayList<Background>(gameState.getBackgrounds());		
		activeEntities = new HashMap<>();
	}

	@Override
	public ObservableList<? extends PlayEntity> getEntities() {
		return myEntities;
	}

	@Override
	public void modelStep(double dt) {
		for (Engine e : engines) {
			e.step(gameState, keys, dt);
		}
		updateGameState(gameState.getEntities());
		updateObservableEntities();
	}

	@Override
	public void saveGameState(File file) throws Exception {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			mySerializer.toXML(new GameState(myEntities, myBackgrounds, gameState.getRootGoal()), fileOut);
		} catch (Exception e) {
			throw new ImproperFileException(errors.getString("InvalidFile"), e);
		}
	}

	@Override
	public void onKeyPress(KeyCode press) {
		keys.add(press);
	}

	@Override
	public void onKeyRelease(KeyCode release) {
		keys.delete(release);
	}

	@Override
	public ObservableList<PlayGoal> getCurrentGoals() {
		return new ObservableList<>();
	}

//	@Override
//	public ObservableList<PlayEntity> getItems() {
////		itemList
//		for (GameEntity entity : gameState){
//			
//		}
//		return null;
//	}
//	
	private void updateGameState(Map<Id, GameEntity> entities) {
		activeEntities.clear();
		for (Id id : entities.keySet()){
			if (entities.get(id).isActive()){
				activeEntities.put(id, entities.get(id));
			}
		}
		entities.clear();
		entities.putAll(activeEntities);
	}
	
	private void updateObservableEntities(){
		for (int i = myEntities.size() - 1; i >= 0; i--){
			if (!gameState.getEntities().containsKey(myEntities.get(i).getId())){
				myEntities.remove(i);
			}
		}
	}

	private void addEntity(Object obj) {
		if(obj instanceof GameEntity){
			myEntities.add((GameEntity) obj);
		}
	}
	
	public List<Background> getBackgrounds(){
		return myBackgrounds;
	}
}
