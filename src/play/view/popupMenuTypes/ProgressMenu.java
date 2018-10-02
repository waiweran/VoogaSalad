package play.view.popupMenuTypes;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import play.view.ButtonBuilder;
import play.view.LoadMenu;
import play.view.Controllas.PlayWorldControlla;
import play.view.Controllas.ProgressControlla;

/**
 * Creates the menu item displaying the two buttons for progress: Save and Exit
 * @author Harry Liu
 * @version 04-05-2017
 */
public class ProgressMenu extends PopupMenuItem {

	private Stage daStage;
	private PlayWorldControlla controlla;
	
	public ProgressMenu(Stage theStage, PlayWorldControlla controller) {
		super("Progress");
		daStage = new Stage();
		controlla = controller;
	}

	@Override
	public StackPane getView() {
		return createProgressPane(daStage);
	}

	/**
	 * Creates the box that holds the progress buttons (save and exit)
	 * @param daStage
	 * @return
	 */
	private StackPane createProgressPane(Stage daStage){
		VBox ButtonBox = new VBox(10);
		ButtonBox.setAlignment(Pos.CENTER);
		ButtonBuilder saveButton = new ButtonBuilder ("Save", event -> new ProgressControlla(controlla).saveGameState());
		ButtonBuilder exitButton = new ButtonBuilder ("New Game", event -> returntoMain(daStage));
		ButtonBox.getChildren().addAll(saveButton.getButton(),exitButton.getButton());
		ButtonBox.setAlignment(Pos.CENTER);
		StackPane ProgressPane = new StackPane();
		ProgressPane.getChildren().addAll(ButtonBox);
		ProgressPane.setId("Test");
		return ProgressPane;
	}

	
	/**
	 * Opens up a new LoadMenu
	 * @param daStage
	 */
	private void returntoMain(Stage daStage){
		new LoadMenu(daStage);
	}
	
}
