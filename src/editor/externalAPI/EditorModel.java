package editor.externalAPI;

import java.io.File;
import java.util.List;

import general.Id;
import general.exceptions.ImproperFileException;
import general.gameobjects.Background;
import general.storage.ObservableList;

public interface EditorModel {

	/**
	 * Pass in an EditorEntity instance that can either be a preset Entity or a
	 * generic Entity. Creates a new instance with the same attributes and type
	 * as the EditorEntity passed in. Returns an ID of the new Entity instance
	 * created in the Model
	 * 
	 * @return Id of the newly created instance so view can access the new
	 *         entity in the model
	 */
	public Id createNewInstance(EditorEntity preset);

	/**
	 * Retrieves an entity in the model based on the given ID. Returns an
	 * EditorEntity interface for View to access information about the Entity
	 * 
	 * @param id
	 *            the Id of the entity to get
	 * @return EditorEntity with the requested Id
	 */
	public EditorEntity request(Id id);

	/**
	 * Delete the Entity from Model with the given Id
	 * 
	 * @param id
	 *            Id of the Entity to be removed from Model
	 */
	public void delete(Id id);

	/**
	 * @return EditorGoal that is the root goal in the model's goal tree
	 */
	public EditorGoal getRootGoal();

	/**
	 * Provides View with an updating list of Entities that exist in the model.
	 * 
	 * @return ObservableList of every entity in the model.
	 */
	public ObservableList<? extends EditorEntity> getEntities();

	/**
	 * Saves the current GameState to the given file
	 * 
	 * @param file
	 *            File to save GameState into
	 * @throws ImproperFileException
	 *             if something goes wrong while saving
	 */
	public void saveGameState(File file) throws ImproperFileException;

	/**
	 * Loads a GameState from the given file
	 * 
	 * @param file
	 *            File to load GameState from
	 * @throws ImproperFileException
	 *             if something goes wrong while loading
	 */
	public void loadGameState(File file) throws ImproperFileException;

	/**
	 * Saves a preset for an EditorEntity.
	 * 
	 * @param id
	 *            the Id of the EditorEntity being saved
	 * @throws ImproperFileException
	 *             if something goes wrong while saving
	 */
	public void savePreset(Id id) throws ImproperFileException;

	/**
	 * Gets all saved presets
	 * 
	 * @return an List of entities that can be added to the world.
	 */
	public ObservableList<? extends EditorEntity> getPresets();
	
	/**
	 * Gets all the background objects
	 * @return
	 */
	public ObservableList<Background> getBackgrounds();
}
