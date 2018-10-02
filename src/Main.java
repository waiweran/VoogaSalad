
import java.io.IOException;

import editor.controllers.EditorController;
import editor.externalAPI.EditorEntity;
import editor.model.EditModel;
import general.Id;
import general.attributes.Tag;
import general.entities.EntityType;
import general.exceptions.ImproperFileException;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import play.view.LoadMenu;

/**
 * Main class of VoogaSalad.  Starts the program.
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Remove the below code when attributes no longer change
		EditModel temp = new EditModel();
		Id myId = temp.createDefaultInstance();
		EditorEntity entity = temp.request(myId);
		entity.getAttribute("Tag", Tag.class).setValue(new EntityType("Entity", "Type", "Sub-Type"));
		try {
			temp.savePreset(myId);
		} catch (ImproperFileException e) {
			throw new RuntimeException(e);
		}
		// TODO Remove the above code when attributes no longer change

		// TODO Remove the below code when editor and player are integrated
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Start a Program");
		alert.getButtonTypes().setAll(new ButtonType("Player"), new ButtonType("Editor"));
		ButtonType selected = alert.showAndWait().get();
		// TODO Remove the below code when editor and player are integrated

		if(selected.getText().equals("Editor")) {
			new EditorController(stage);
		}
		else {
			new LoadMenu(stage);
		}
	}
		
	/**
	 * Entry point of program, launches application.
	 * @param args command line arguments, unused.
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
