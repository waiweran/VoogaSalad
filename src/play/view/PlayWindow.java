package play.view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import play.view.Controllas.PlayWorldControlla;

/**
 * Class for starting the play environment.
 * @author Harry Liu
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class PlayWindow {
	
	/**
	 * Constructor for the play class. Currently only adds in the Items List for Play Window.
	 * @param daStage
	 */
	public PlayWindow(PlayWorldControlla playController, Stage daStage) {
		daStage.setFullScreen(true);
		StackPane theEnvironment = new StackPane();
		HeadsUpDisplay headsUp = playController.getHUDController().getHUD();
		PopupMenu popupMenu = new PopupMenu(playController, daStage);
		theEnvironment.getChildren().addAll(playController.getPlayWorld().getView(), headsUp.getView(), popupMenu.getView());
		Scene theScene = new Scene(theEnvironment);
		theScene.setOnKeyPressed(e -> playController.handleKeyPress(e.getCode(), popupMenu.getView()));
		theScene.setOnKeyReleased(e -> playController.handleKeyRelease(e.getCode()));
		theScene.getStylesheets().add(getClass().getResource("/styling/menu.css").toExternalForm());	
		daStage.setScene(theScene);
	}

}
