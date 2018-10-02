package play.view;

import general.ResourceLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import play.view.Controllas.LoadMenuControlla;

/**
 * The class where the user is prompted to upload a file. This will start PlayerWindow.
 * @author Harry Liu
 * @version 04-01-2017
 */
public class LoadMenu {

	private String BACKGROUND = "backgrounds/load.png";

	/**
	 * The constructor of the load menu class where the user will upload game data to start the game
	 * @param daStage
	 */
	public LoadMenu(Stage daStage) {
		daStage.initStyle(StageStyle.UTILITY);
		
		LoadMenuControlla controller = new LoadMenuControlla();
		
		ButtonBuilder uploadFile = new ButtonBuilder (new ResourceLoader().getDisplayResources().getString("uploadButton"), event -> controller.runGame(daStage));


		StackPane pane = new StackPane();

		Scene theScene = new Scene(pane);

		VBox vBox = new VBox(10);
		Label text = new Label("Play Me...");
		vBox.getChildren().addAll(text, uploadFile.getButton());
		vBox.setAlignment(Pos.CENTER);

		theScene.getStylesheets().add(getClass().getResource("/styling/load.css").toExternalForm());
		pane.getChildren().addAll(buildBackground(pane), vBox);
		daStage.setScene(theScene);	
		daStage.setFullScreen(false);
		daStage.show();
	}

	/**
	 * Creates the background of the loadPage
	 * @return background ImageView of the background
	 */
	private ImageView buildBackground(StackPane pane){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BACKGROUND));
		ImageView background = new ImageView(image);
		background.fitWidthProperty().bind(pane.widthProperty());
		background.fitHeightProperty().bind(pane.heightProperty());
		return background;
	}
}
