package editor.controllers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import editor.externalAPI.EditorModel;
import editor.model.EditModel;
import editor.view.EditorScreen;
import editor.view.EditorWindow;
import general.ResourceLoader;
import general.view.ExceptionHandler;
import javafx.stage.Stage;

/**
 * Primary controller for the editor environment.
 * Initializes the editor model and view.
 * @author Nathaniel Brooke
 * @version 04-04-2017
 */
public class EditorController {
	
	private MenuController menuController;
	private Map<String, EditorScreen> editors;
	private EditorWindow view;
	private EditorModel model;

	/**
	 * Initializes the editor view and controllers.
	 * @param mainStage
	 */
	public EditorController(Stage mainStage) {
		menuController = new MenuController(this, mainStage);
		editors = new HashMap<>();
		view = new EditorWindow(mainStage, this);
	}
	
	/**
	 * Switches the current editor to the one specified.
	 * @param name the name of the editor to switch to.
	 */
	public void switchToEditor(String name) {
		if(editors.containsKey(name)) {
			view.switchToScreen(editors.get(name).getScreen());
		}
		else {
			try {
				Class<?> newEditorClass = Class.forName(
						ResourceLoader.EDITOR_RESOURCES.getString(name));
				Constructor<?> constructor = newEditorClass.getConstructor(this.getClass());
				ScreenController newEditor = (ScreenController) constructor.newInstance(this);
				EditorScreen newScreen = newEditor.getEditorScreen();
				editors.put(name, newScreen);
				view.switchToScreen(newScreen.getScreen());
			} catch (ClassNotFoundException | SecurityException | 
					InstantiationException | IllegalAccessException | 
					IllegalArgumentException | InvocationTargetException | 
					NoSuchMethodException e) {
				new ExceptionHandler().showAlert();
			}
		}
	}
	
	/**
	 * Loads the editor with a new game.
	 */
	public void newGame() {
		model = new EditModel();
		switchToEditor("World");
	}
	
	/**
	 * Loads the editor with a game file.
	 * @param gamefile game file to edit.
	 */
	public void loadGame(File gamefile) {
		model = new EditModel(gamefile);
		menuController.setSaveFile(gamefile);
		switchToEditor("World");
	}
	
	/**
	 * Gets the menu controller.
	 * @return the menu controller.
	 */
	public MenuController getMenuController() {
		return menuController;
	}
	
	/**
	 * @return the model of the current game.
	 */
	public EditorModel getModel() {
		return model;
	}

}
