package editor.controllers;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;

import editor.view.MenuBar;
import general.ResourceLoader;
import general.exceptions.ImproperFileException;
import general.view.ExceptionHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import play.view.Controllas.PlayWorldControlla;

/**
 * Controls the menu bar.
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class MenuController {
		
	private EditorController editor;
	private Stage currentStage;
	private MenuBar view;
	private File savefile;

	/**
	 * Initializes the menu controller
	 * @param mainController the primary controller for the editor.
	 */
	public MenuController(EditorController mainController, Stage mainStage) {
		editor = mainController;
		currentStage = mainStage;
		view = new MenuBar(this);
	}
	
	/**
	 * Handles clicks to the menu that switches the editor.
	 * @param name the name of the editor to switch to.
	 */
	public void editorMenuClicked(String name) {
		editor.switchToEditor(name);
	}
	
	/**
	 * Handles clicks to the load option in the menu.
	 */
	public void loadClicked() {
		File selected = makeFileChooser(false);
		if(selected != null) {
			Stage newStage = new Stage();
			EditorController newControl = new EditorController(newStage);
			newControl.loadGame(selected);
		}
	}
	
	/**
	 * Handles clicks to the load button in the starting screen.
	 */
	public void loadButtonClicked() {
		File selected = makeFileChooser(false);
		if(selected != null) {
			savefile = selected;
			editor.loadGame(selected);
		}
	}
	
	/**
	 * Handles clicks to the new option in the menu.
	 */
	public void newClicked() {
		Stage newStage = new Stage();
		EditorController newControl = new EditorController(newStage);
		newControl.newGame();
	}
	
	/**
	 * Handles clicks to the new button in the starting screen.
	 */
	public void newButtonClicked() {
		editor.newGame();
	}
	
	/**
	 * Saves the current game.
	 */
	public void saveClicked() {
		if(savefile == null) {
			saveAsClicked();
		}
		else {
			try {
				editor.getModel().saveGameState(savefile);
			} catch (ImproperFileException e) {
				new ExceptionHandler().showAlert(e);
			}
		}
	}
	
	/**
	 * Saves the current game.
	 * User selects the file.
	 */
	public void saveAsClicked() {
		File selected = makeFileChooser(true);
		if(selected != null) {
			savefile = selected;		
			try {
				editor.getModel().saveGameState(savefile);
			} catch (ImproperFileException e) {
				new ExceptionHandler().showAlert(e);
			}
		}
	}
	
	/**
	 * Opens the background editor.
	 */
	public void backgroundClicked() {
		new BackgroundsController(editor).getPopup().showPopup();
	}
		
	/**
	 * Saves and plays the current game.
	 */
	public void playGameClicked() {
		saveClicked();
		if(savefile != null) {
			Stage newStage = new Stage();
			PlayWorldControlla play = new PlayWorldControlla(newStage, savefile);
			currentStage.close();
			newStage.showAndWait();
			play.playPause(false);
			Stage anotherStage = new Stage();
			EditorController newControl = new EditorController(anotherStage);
			newControl.loadGame(savefile);
		}
	}
	
	/**
	 * Sets the save file that this Editor has open.
	 * @param newSave the new save file.
	 */
	public void setSaveFile(File newSave) {
		savefile = newSave;
	}
	
	/**
	 * @return the MenuBar that this controls.
	 */
	public MenuBar getMenu() {
		return view;
	}
	
	/**
	 * Says what the project is about
	 */
	public void aboutClicked() {
		URL url = getClass().getClassLoader().getResource("helpFiles/help.html");

		try {
			Desktop.getDesktop().browse(url.toURI());
		} catch(Exception e) {
			new Alert(AlertType.INFORMATION,"Help page not found");
		}
		
	}
	
	private File makeFileChooser(boolean save) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/games"));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter(
						new ResourceLoader().getDisplayResources().getString("Filetype"), 
						ResourceLoader.EDITOR_RESOURCES.getString("Extension"))
		);
		fileChooser.setInitialDirectory(new File("src/games"));
		if(save) return fileChooser.showSaveDialog(null);
		else return fileChooser.showOpenDialog(null);
	}
}
