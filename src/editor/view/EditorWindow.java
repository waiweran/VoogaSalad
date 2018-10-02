package editor.view;

import editor.controllers.EditorController;
import general.ResourceLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Maintains the primary stage of the editor window.
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class EditorWindow {
	
	private BorderPane root;
	private EditorController control;
	private String SPLASH = "backgrounds/EditorEngine.png";

	/**
	 * Sets up the editor window with the opening view.
	 * @param stage the primary stage.
	 */
	public EditorWindow(Stage stage, EditorController controller) {
		control = controller;
		stage.setScene(makeStartingScene());
		stage.show();
	}

	/**
	 * Changes the currently displayed Scene to the given Scene.
	 * @param scene the new Scene to display.
	 */
	public void switchToScreen(Pane screen) {
		root.setCenter(screen);
		root.setTop(control.getMenuController().getMenu().getView());
	}
	
	/**
	 * Generates the starting Scene with the menu and background.
	 * @return startup the startup Scene
	 */
	private Scene makeStartingScene() {
		root = new BorderPane();
		AnchorPane mainDisplay = new AnchorPane();
		ImageView backgroundIV = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(SPLASH)));
		backgroundIV.fitWidthProperty().bind(root.widthProperty());
		backgroundIV.fitHeightProperty().bind(root.heightProperty());
		mainDisplay.getChildren().addAll(backgroundIV, makeStartingButtons());
		root.setCenter(mainDisplay);
		Scene startup = new Scene(root);
		startup.getStylesheets().add(getClass().getResource("/styling/editor.css").toExternalForm());
		System.out.println(this.getClass().getClassLoader());
		return startup;
	}

	private HBox makeStartingButtons() {
		HBox buttons = new HBox(20);
		Button newBtn = new Button(new ResourceLoader().getDisplayResources().getString("New"));
		newBtn.setOnAction(e -> control.getMenuController().newButtonClicked());
		Button loadBtn = new Button(new ResourceLoader().getDisplayResources().getString("Open"));
		loadBtn.setOnAction(e -> control.getMenuController().loadButtonClicked());
		buttons.setId("SplashButtons");
		buttons.getChildren().addAll(newBtn, loadBtn);
		buttons.setLayoutX(Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("StartingButtonsX")));
		buttons.setLayoutY(Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("StartingButtonsY")));
		return buttons;
	}
	
}