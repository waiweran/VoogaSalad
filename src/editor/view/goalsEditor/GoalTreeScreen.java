package editor.view.goalsEditor;

import editor.view.EditorScreen;
import editor.view.MenuBar;
import general.view.Scrollable;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 * Manages the scene that displays the goal tree.
 * @author Nathaniel Brooke
 * @version 04-02-2017
 */
public class GoalTreeScreen implements EditorScreen {
	
	private BorderPane root;
	private VBox treeView;

	/**
	 * Initializes a new GoalTreeScene and renders the goal tree.
	 */
	public GoalTreeScreen(MenuBar menu) {
		setupTreeView(menu);
	}

	@Override
	public Pane getScreen() {
		return root;
	}

	/**
	 * Loads a new tree view into the scene.
	 * @param tree the Pane representing the new tree.
	 */
	public void setTree(Pane tree) {
		treeView.getChildren().add(tree);
	}
	
	/**
	 * Clears the goal tree.
	 */
	public void clearTree() {
		treeView.getChildren().clear();
	}
	
	/**
	 * Sets up the tree view before the tree is generated.
	 */
	private void setupTreeView(MenuBar menu) {
		root = new BorderPane();
		treeView = new VBox();
		treeView.setId("GoalsBackground");
		treeView.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
		treeView.setAlignment(Pos.TOP_CENTER);
		treeView.setMinWidth(Screen.getPrimary().getBounds().getWidth() - 20);
		root.setCenter(new Scrollable(treeView, Screen.getPrimary().getBounds().getWidth()).getView());
	}

}
