package editor.view.goalsEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import editor.view.worldEditor.sideMenuItems.fields.FieldViewFactory;
import general.ResourceLoader;
import general.actions.strategies.Strategy;
import general.attributes.Attribute;
import general.attributes.Tag;
import general.fields.Field;
import general.view.ScreenObject;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import play.view.ButtonBuilder;

/**
 * Displays a single condition on screen.
 * @author Nathaniel Brooke
 * @author salo
 * @version 04-30-2017
 */
public class ConditionView implements ScreenObject {
	
	private final ResourceBundle resources;
	
	private ConditionChooser mainView;
	private List<String> conditionTypes;
	private Tag targetEntity; // Entity 1
	private BorderPane condMain;
	private List<Attribute<?>> attributes;
	private Attribute<?> selectedAttr; // Attribute
	private List<Strategy> strategies;
	private Strategy selectedStrat; // Strategy
	private Tag secondEntity; // Entity 2 (if strat)

	/**
	 * Initializes the condition view.
	 * @param chooser the chooser this view is displayed in.
	 * @param attr the Attribute list to select from.
	 * @param strat the Strategy list to select from.
	 */
	public ConditionView(ConditionChooser chooser, List<Attribute<?>> attr, List<Strategy> strat) {
		mainView = chooser;
		attributes = attr;
		strategies = strat;
		resources = new ResourceLoader().getDisplayResources();
		conditionTypes = new ArrayList<String>();
		conditionTypes.add("Strategy"); //TODO clean this up
		conditionTypes.add("Attribute");
		targetEntity = new Tag("Tag");
		setupView();
	}

	/**
	 * Sets up the Condition view.
	 */
	private void setupView() {
		condMain = new BorderPane();
		HBox condition = new HBox(5);
		condition.setAlignment(Pos.CENTER_LEFT);
		Text ifText = new Text(resources.getString("If"));
		Button tagBtn = new ButtonBuilder(resources.getString("Entity"),
				e -> pickTag(targetEntity)).getButton();
		ComboBox<String> condType = new ComboBox<>();
		ComboBox<String> condSpec = new ComboBox<>();
		condType.getItems().addAll(conditionTypes);
		condType.setOnAction(e -> setConditionType(condType.getValue(), condSpec, condition));
		condition.getChildren().addAll(ifText, tagBtn, condType, condSpec);
		condMain.setLeft(condition);
		Button remove = new ButtonBuilder(resources.getString("Remove"), 
				e -> mainView.removeCondition(this)).getButton();
		condMain.setRight(remove);
	}
	
	@Override
	public Region getView() {
		return condMain;
	}
	
	/**
	 * @return the Attribute the user selected.
	 */
	public Attribute<?> getSelectedAttribute() {
		return selectedAttr;
	}
	
	/**
	 * @return the Strategy that the user selected.
	 */
	public Strategy getSelectedStrategy() {
		return selectedStrat;
	}
	
	/**
	 * @return the first entity entered.
	 */
	public Tag getFirstEntity() {
		return targetEntity;
	}
	
	/**
	 * @return the second entity entered. Only present if strategy.
	 */
	public Tag getSecondEntity() {
		return secondEntity;
	}
	
	private void setConditionType(String type, ComboBox<String> condSpec, HBox view) {
		if(type.equals("Attribute")) {
			List<String> attrNames = attributes.stream().map(a -> a.getName()).collect(Collectors.toList());
			condSpec.getItems().addAll(attrNames);
			condSpec.setOnAction(e -> selectAttribute(condSpec.getSelectionModel().getSelectedIndex(), view));
		}
		else if(type.equals("Strategy")) {
			List<String> stratNames = strategies.stream().map(s -> s.getStringRepresentation()).collect(Collectors.toList());
			condSpec.getItems().addAll(stratNames);
			condSpec.setOnAction(e -> selectStrategy(condSpec.getSelectionModel().getSelectedIndex()));
			secondEntity = new Tag("Tag");
			Button tagBtn = new ButtonBuilder(resources.getString("Entity"),
					e -> pickTag(secondEntity)).getButton();
			view.getChildren().add(tagBtn);
		}
	}
	
	private void pickTag(Tag tag) {
		Alert tagChooser = new Alert(AlertType.INFORMATION);
		HBox tagFields = new HBox(5);
		FieldViewFactory factory = new FieldViewFactory();
		for(Field<?> f : tag.getFieldsList()) {
			tagFields.getChildren().add(factory.makeFieldView(f));
		}
		tagChooser.setGraphic(tagFields);
		tagChooser.show();
	}
	
	private void selectAttribute(int index, HBox view) {
		view.getChildren().add(new Text(" = "));
		selectedAttr = attributes.get(index);
		FieldViewFactory factory = new FieldViewFactory();
		for(Field<?> f : selectedAttr.getFieldsList()) {
			view.getChildren().add(factory.makeFieldView(f));
		}
	}
	
	private void selectStrategy(int index) {
		selectedStrat = strategies.get(index);
	}

}
