package play.view;

import java.util.Collection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ButtonBuilder {
	private Button Button;

	/**
	 * constructor for Buttons with event  
	 * @param name
	 * @param newButton
	 */
	public ButtonBuilder(String name,  EventHandler<ActionEvent> newButton){
		Button = new Button();
		Button.setText(name);
		Button.setOnAction(newButton);
	}
	/**
	 * Constructor to see where the button should be 
	 * @param label
	 * @param row
	 * @param col
	 * @param buttonPress
	 */
	public ButtonBuilder(String name, int row, int col, EventHandler<ActionEvent> newButton) {
		Button = new Button();
		GridPane.setConstraints(Button, col, row);
		Button.setText(name);
		Button.setOnAction(newButton);
	}
	/**
	 * method add buttons from different types of collection to the Pane
	 * @param buttons
	 * @param daPane
	 */
	public static void addButtonsTo(Collection<ButtonBuilder> buttons, Pane daPane){
		for(ButtonBuilder button : buttons){
			daPane.getChildren().add((Node) button.getButton());
		}
	}
	
	public Button getButton() {
		return Button;
	}
}