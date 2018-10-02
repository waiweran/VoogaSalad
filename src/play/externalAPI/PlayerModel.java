package play.externalAPI;

import java.io.File;
import java.util.List;

import general.gameobjects.Background;
import general.storage.ObservableList;
import javafx.scene.input.KeyCode;

public interface PlayerModel {

	/**
	 * Gets all Game entities that exist on the Screen
	 * 
	 * @return an ObservableList of all entities in the game
	 */
	public ObservableList<? extends PlayEntity> getEntities();

	/**
	 * Updates model given a change in time
	 * 
	 * @param dt
	 *            time difference between steps
	 */
	public void modelStep(double dt);

	/**
	 * Saves the state of the current game (player's progress)
	 * 
	 * @param file
	 *            XML File to save the game in
	 * @throws Exception
	 *             if something goes wrong while saving
	 */
	public void saveGameState(File file) throws Exception;

	/**
	 * Allows the back end to process key presses that affect the state of the
	 * model directly.
	 * 
	 * @param keyCode
	 *            a KeyInput indicating what key was pressed
	 */
	public void onKeyPress(KeyCode press);

	/**
	 * Allows the back end to process key releases that affect the state of the
	 * model directly.
	 * 
	 * @param release
	 *            a KeyInput indicating what key was released
	 */
	public void onKeyRelease(KeyCode keyRelease);

	/**
	 * Gets all current goals that the player can currently try to complete.
	 * 
	 * @return ObservableList of goals that are currently active
	 */
	public ObservableList<PlayGoal> getCurrentGoals();

	/**
	 * Get all background objects saved in GameState
	 * 
	 * @return List of Background objects
	 */
	public List<Background> getBackgrounds();
}
