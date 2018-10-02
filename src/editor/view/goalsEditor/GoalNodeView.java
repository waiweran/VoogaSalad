package editor.view.goalsEditor;

import editor.controllers.goalsControllers.GoalController;
import general.ResourceLoader;
import general.view.ScreenObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Display for a single goal node within the goal tree.
 * @author Nathaniel Brooke
 * @author salo
 * @version 04-02-2017
 */
public class GoalNodeView implements ScreenObject {
	
	private ResourceLoader resources;
	private Button conditions;
	private TextField titleWriter; 
	private TextArea descriptionWriter;
	private BorderPane goalView;
	private Button add, remove;
	private GoalController control;

	/**
	 * Initializes an editable goal to put in the Goal tree.
	 * @param conditions List of conditions
	 */
	public GoalNodeView(GoalController controller) {
		control = controller;
		resources = new ResourceLoader();
		initializeInputs();
		makePane();
	}
	
	/**
	 * Makes this GoalNodeView such that it cannot be deleted.
	 * Used for making the root node impossible to delete.
	 */
	public void setNotCloseable() {
		remove.setDisable(true);
	}
	
	/**
	 * Loads values from back end into goal display.
	 * @param title the goal title.
	 * @param description the goal description text.
	 * @param condition the currently selected condition name.
	 */
	public void loadPresets(String title, String description) {
		titleWriter.setText(title);
		descriptionWriter.setText(description);
	}

	@Override
	public Pane getView() {
		return goalView;
	}
	
	/**
	 * Initializes the inputs in the goal view.
	 * @param conditions the list of possible conditions to display.
	 */
	private void initializeInputs() {
		conditions = new Button(resources.getDisplayResources().getString("Conditions"));
		conditions.setOnAction(e -> control.editConditions());
		titleWriter = new TextField();
		titleWriter.setPromptText(resources.getDisplayResources().getString("Title"));
		titleWriter.setOnKeyReleased(e -> control.setTitle(titleWriter.getText()));
		descriptionWriter = new TextArea();
		descriptionWriter.setPromptText(resources.getDisplayResources().getString("Description"));
		descriptionWriter.setOnKeyReleased(e -> control.setDescription(descriptionWriter.getText()));
		descriptionWriter.setMaxWidth(Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("GoalWidth")));
		descriptionWriter.setMaxHeight(Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("GoalHeight")));
		add = new Button(resources.getDisplayResources().getString("Add"));
		add.setTooltip(new Tooltip(resources.getDisplayResources().getString("AddTip")));
		add.setOnAction(e -> control.addGoal());
		remove = new Button(resources.getDisplayResources().getString("Remove"));
		remove.setTooltip(new Tooltip(resources.getDisplayResources().getString("RemoveTip")));
		remove.setOnAction(e -> control.remove());
	}
	
	/**
	 * Generates the goal node view pane.
	 */
	private void makePane() {
		goalView = new BorderPane();
		Label title = new Label(resources.getDisplayResources().getString("Goal"));
		title.setId("GoalTitle");
		HBox topBox = new HBox(10);
		topBox.getChildren().addAll(title, titleWriter);
		topBox.setPadding(new Insets(10,0, 10, 0));
		topBox.setAlignment(Pos.CENTER);
		HBox buttonBox = new HBox(5);
		buttonBox.getChildren().addAll(add, remove);
		buttonBox.setPadding(new Insets(10,0, 0, 0));
		VBox bottomBox = new VBox(10);
		bottomBox.getChildren().addAll(descriptionWriter, conditions);
		goalView.setLeft(topBox);
		goalView.setBottom(bottomBox);
		goalView.setRight(buttonBox);
		goalView.setMaxWidth(Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("GoalWidth")));
	}

}
