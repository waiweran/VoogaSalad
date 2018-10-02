package editor.view.goalsEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import editor.controllers.goalsControllers.GoalController;
import general.ResourceLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import play.view.ButtonBuilder;

/**
 * Selects conditions that apply to a given goal
 * @author Nathaniel Brooke
 * @author salo
 * @version 04-30-2017
 */
public class ConditionChooser {
	
	private Stage stage;
	private VBox root;
	private final ResourceBundle resources;
	private GoalController control;
	private List<ConditionView> currentConditions;

	/**
	 * Initializes a new ConditionChooser.
	 * @param controller the  GoalController that controls this chooser.
	 */
	public ConditionChooser(GoalController controller) {
		stage = new Stage();
		root = new VBox(10);
		control = controller;
		resources = new ResourceLoader().getDisplayResources();
		currentConditions = new ArrayList<>();
		setupView();
		Scene scene = new Scene(root, 700, 500);
		stage.setScene(scene);
		//TODO load existing conditions
	}
	
	/**
	 * Displays the ConditionChooser pop-up.
	 */
	public void showPopup() {
		stage.show();
	}
	
	/**
	 * Initializes the view.
	 */
	private void setupView() {
		HBox controls = new HBox(5);
		Button addCond = new ButtonBuilder(resources.getString("NewCondition"), 
				e -> addCondition()).getButton();
		Button save = new ButtonBuilder(resources.getString("Save"), 
				e -> control.writeConditions()).getButton();
		controls.getChildren().addAll(addCond, save);
		root.getChildren().add(controls);
		root.setPadding(new Insets(10, 10, 10, 10));
	}
	
	/**
	 * Adds a new condition to the pop-up window.
	 */
	private void addCondition() {
		ConditionView cond = new ConditionView(this, control.getAttributes(), control.getStrategies());
		currentConditions.add(cond);
		root.getChildren().add(cond.getView());
	}
	
	/**
	 * Removes the given condition from the view.
	 * @param condition the condition to remove.
	 */
	public void removeCondition(ConditionView condition) {
		currentConditions.remove(condition);
		root.getChildren().remove(condition.getView());
	}

}
