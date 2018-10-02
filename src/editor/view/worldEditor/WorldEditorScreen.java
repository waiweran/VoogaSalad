package editor.view.worldEditor;

import editor.controllers.worldControllers.WorldEditorController;
import editor.view.EditorScreen;
import editor.view.worldEditor.sideMenuItems.TabView;
import editor.view.worldEditor.sideMenuItems.fields.AttributesMenu;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Holds the scene used to edit the main world in the game.
 * @author Nathaniel Brooke
 * @version 04-02-2017
 */
public class WorldEditorScreen implements EditorScreen {

	private BorderPane root;
	private WorldEditorController control;

	/**
	 * Initializes the main world editor scene.
	 * @param world the EditorWorld to display in the editor.
	 * @param menu the SideMenu to display in the editor.
	 */
	public WorldEditorScreen(WorldEditorController controller, EditorWorld world, 
			TabView entitiesMenu, AttributesMenu attributesMenu) {
		control = controller;
		root = new BorderPane();
		root.setCenter(world.getView());
		root.setLeft(entitiesMenu.getView());
		root.setRight(attributesMenu.getView());
		setupKeys();
	}

	@Override
	public Pane getScreen() {
		return root;
	}

	private void setupKeys() {
		root.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.BACK_SPACE)
					|| e.getCode().equals(KeyCode.DELETE)) {
				control.deleteSelectedEntity();
			}
		});
	}

}
