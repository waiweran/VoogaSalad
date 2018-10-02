package general.view;

import general.ResourceLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Deals with informing the user of exceptions.
 * @author Nathaniel Brooke
 * @version 02-28-2017
 */
public class ExceptionHandler {
	
	private String message;
	private Scene scene;
	public static final double SIZE=400;
	
	public void showException(String exception) {
		message= exception;
	}

	public Scene getScene() {
		return scene;
	}
	
	public void makeAlertPane(String text) {
		Pane root = new Pane();
		scene = new Scene(root,SIZE,SIZE);
		Text sentMessage = new Text(10,50,message);
		sentMessage.setFont(Font.font(20));
		root.getChildren().addAll(sentMessage);
	}
	
	/**
	 * Shows an alert with the default message 
	 */
	public void showAlert() {
		showAlert(new ResourceLoader().getDisplayResources().getString("GeneralErrorMessage"));
	}
	
	/**
	 * Shows an alert with an error message.
	 * @param e the Exception whose message will be displayed.
	 */
	public void showAlert(Exception e) {
		showAlert(e.getMessage());
	}
	
	/**
	 * Shows an alert with the specified text.
	 * @param text in alert.
	 */
	public void showAlert(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(text);
		alert.showAndWait();
	}

}
