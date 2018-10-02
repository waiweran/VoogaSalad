package editor.model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import editor.externalAPI.EditorEntity;
import editor.externalAPI.EditorGoal;
import editor.externalAPI.EditorModel;
import general.Id;
import general.attributes.Tag;
import general.entities.EntityType;
import general.entities.EntityTypeCollection;
import general.entities.GameEntity;
import general.entities.PresetEntity;
import general.exceptions.ImproperFileException;
import general.gameobjects.Background;
import general.newgoal.Goal;
import general.resourceEngine.FileType;
import general.resourceEngine.MultiEngine;
import general.resourceEngine.ResourceEngine;
import general.storage.GameState;
import general.storage.ObservableList;

/**
 * Model for the Editor. Handles saving/loading editor environments and
 * collecting game elements created in the view
 * 
 * @author Gordon
 *
 */
public class EditModel implements EditorModel {

	private final static String PRESETS_FP = "src/presets/";
	private final static String PRESETS_FT = ".xml";
	private final static String ERROR_RESOURCE = "resources.ErrorMessages";
	private final static String BACKEND_RESOURCE = "resources.Backend";
	
	private ResourceBundle errorResource = ResourceBundle.getBundle(ERROR_RESOURCE);
	private ResourceBundle backResource = ResourceBundle.getBundle(BACKEND_RESOURCE);
	private XStream mySerializer = new XStream(new DomDriver());
	private ObservableList<GameEntity> myEntities;
	private ObservableList<PresetEntity> myPresets;
	private ObservableList<Background> myBackgrounds;
	private Map<PresetEntity, File> presetFiles;
	private Goal root;
	private ResourceEngine myResources;

	/**
	 * Initializes the model based on the game save.
	 * 
	 * @param gamefile
	 *            the game save to load.
	 */
	public EditModel(File gamefile) {
		this();
		GameState temp = (GameState) mySerializer.fromXML(gamefile);
		myEntities.addAll(temp.getEntities().values());
		myBackgrounds.addAll(temp.getBackgrounds());
	}

	/**
	 * Initialize a new model to create a brand new game
	 */
	public EditModel() {
		myEntities = new ObservableList<GameEntity>();
		myPresets = new ObservableList<PresetEntity>();
		myBackgrounds = new ObservableList<Background>();
		presetFiles = new HashMap<PresetEntity, File>();
		root = new Goal("", "");
		myResources = new MultiEngine(FileType.DATA);
		
		myResources.pullFiles(PRESETS_FP);
		for (File file : myResources.get(FileType.DATA).values()) {
			try {
				this.addPreset(file);
			} catch (Exception e) {
				// Not a PresetEntity saved
				continue;
			}
		}
		saveEntityTypes();
	}

	public Id createDefaultInstance() {
		GameEntity temp = new GameEntity();
		myEntities.add(temp);
		return temp.getId();
	}

	@Override
	public Id createNewInstance(EditorEntity preset) {
		try {
			PresetEntity presetEntity = (PresetEntity) mySerializer.fromXML(presetFiles.get((PresetEntity) preset));
			GameEntity temp = new GameEntity(presetEntity);
			myEntities.add(temp);
			return temp.getId();
		} catch (Exception e) {
			throw new RuntimeException("Preset");
		}
	}

	@Override
	public GameEntity request(Id id) {
		for (GameEntity e : myEntities) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new RuntimeException("Entity with Id " + id + " does not exist");
	}

	@Override
	public void delete(Id id) {
		for(GameEntity ge : myEntities){
			if(ge.getId().equals(id)){
				myEntities.remove(ge);
				return;
			}
		}
	}

	@Override
	public EditorGoal getRootGoal() {
		return root;
	}

	@Override
	public ObservableList<GameEntity> getEntities() {
		return myEntities;
	}

	@Override
	public void saveGameState(File file) throws ImproperFileException {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			mySerializer.toXML(new GameState(myEntities, myBackgrounds, root), fileOut);
			this.saveEntityTypes();
		} catch (Exception e) {
			throw new ImproperFileException(errorResource.getString("InvalidFile"), e);
		}
	}

	@Override
	public void loadGameState(File file) throws ImproperFileException {
		try {
			GameState temp = (GameState) mySerializer.fromXML(file);
			myEntities = new ObservableList<>(temp.getEntityValues());
			myBackgrounds = new ObservableList<>(temp.getBackgrounds());
			this.saveEntityTypes();
		} catch (Exception e) {
			throw new ImproperFileException(errorResource.getString("InvalidFile"), e);
		}
	}

	@Override
	public void savePreset(Id id) throws ImproperFileException {
		GameEntity temp = request(id);
		try {
			File dest = new File(PRESETS_FP + temp.getAttribute("Tag", Tag.class).getValue().getName() + PRESETS_FT);
			FileOutputStream fileOut = new FileOutputStream(dest);
			mySerializer.toXML(temp.getPreset(), fileOut);
			this.addPreset(dest);
		} catch (Exception e) {
			throw new ImproperFileException(errorResource.getString("InvalidFile"), e);
		}
	}

	@Override
	public ObservableList<PresetEntity> getPresets() {
		return myPresets;
	}

	private void addPreset(File f) {
		PresetEntity temp = (PresetEntity) mySerializer.fromXML(f);
		myPresets.add(temp);
		presetFiles.put(temp, f);
	}
	
	private void saveEntityTypes(){
		try{
			File dest = new File(PRESETS_FP + backResource.getString("GameType") + PRESETS_FT);
			FileOutputStream fileOut = new FileOutputStream(dest);
			Collection<EntityType> types = new ArrayList<EntityType>();
			for(PresetEntity pe : myPresets){
				types.add(pe.getAttribute("Tag", Tag.class).getValue());
			}
			mySerializer.toXML(new EntityTypeCollection(types), fileOut);
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public ObservableList<Background> getBackgrounds(){
		return myBackgrounds;
	}
	
}
