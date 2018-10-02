package editor.controllers.goalsControllers;

import editor.controllers.EditorController;
import editor.controllers.ScreenController;
import editor.externalAPI.EditorGoal;
import editor.view.EditorScreen;
import editor.view.goalsEditor.GoalNodeView;
import editor.view.goalsEditor.GoalTreeScreen;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controls the rendering of and input to the Goal Tree.
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class GoalTreeController implements ScreenController {
	
	private GoalTreeScreen view;

	/**
	 * Initializes a new goal tree controller.
	 * @param rootGoal root of the goal tree.
	 */
	public GoalTreeController(EditorController mainController) {
		view = new GoalTreeScreen(mainController.getMenuController().getMenu());
		generateTree(mainController.getModel().getRootGoal());
	}
	
	@Override
	public EditorScreen getEditorScreen() {
		return view;
	}
	
	/**
	 * Generates the goal tree display.
	 * @param root the root node in the goal tree.
	 */
	private void generateTree(EditorGoal root) {
		view.clearTree();
		view.setTree(generateTreeBranch(root, root, null));
	}
	
	/**
	 * Recursively generates the goal tree view.
	 * @param root the root goal.
	 * @param node the current goal.
	 * @param parent the current goal's parent.
	 * @return VBox representing the goal and its children.
	 */
	private VBox generateTreeBranch(EditorGoal root, EditorGoal node, EditorGoal parent) {
		VBox nodeView = new VBox();
		nodeView.setAlignment(Pos.TOP_CENTER);
		node.getChildren().deleteObservers();
		node.getChildren().addObserver((o, val) -> generateTree(root));
		GoalNodeView goalView = getGoalView(node, parent);
		HBox goalChildren = new HBox(20);
		goalChildren.setAlignment(Pos.TOP_CENTER);
		nodeView.getChildren().addAll(goalView.getView(), goalChildren);
		for(EditorGoal child : node.getChildren()) {
			VBox childNode = generateTreeBranch(root, child, node);
			goalChildren.getChildren().add(childNode);
		}
		return nodeView;
	}
	
	/**
	 * Gets the GoalNodeView representing the given goal.
	 * @param node the goal to represent.
	 * @param parent the goal's parent.
	 * @return the GoalNodeView.
	 */
	private GoalNodeView getGoalView(EditorGoal node, EditorGoal parent) {
		GoalNodeView goalView;
		GoalController goalControl = new GoalController(node, parent);
		goalView = new GoalNodeView(goalControl);
		if(parent == null) {
			goalView.setNotCloseable();
		}
		goalView.loadPresets(node.getName(), node.getDescription());
		return goalView;
	}
}