package play.view.Controllas;

import java.io.File;

import general.ResourceLoader;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import play.externalAPI.PlayerModel;

public class ProgressControlla {

	private PlayWorldControlla control;

	public ProgressControlla(PlayWorldControlla controller) {
		control = controller;
	}

	public void saveGameState() {
		PlayerModel model = control.getModel();
		String saveName = control.getSaveFile().getAbsolutePath();
		int progPos = saveName.lastIndexOf("_progress_");
		int saveNum = 1;
		if(progPos > 0) {
			saveNum = 1 + Integer.parseInt(saveName.substring(0, saveName.indexOf('.')).substring(progPos + 10));
			saveName = saveName.substring(0, progPos);
		}
		else {
			saveName = saveName.substring(0, saveName.indexOf('.'));
		}
		File file = new File(saveName + "_progress_" + saveNum + ".xml");
		Alert saved = new Alert(Alert.AlertType.INFORMATION);
		Stage stage = (Stage) saved.getDialogPane().getScene().getWindow();
		stage.setAlwaysOnTop(true);
		try {
			model.saveGameState(file);
			saved.setHeaderText(new ResourceLoader().getDisplayResources().getString("SaveAlert"));
		} catch (Exception e) {
			saved.setHeaderText(new ResourceLoader().getDisplayResources().getString("GeneralErrorMessage"));
		}
		saved.show();
	}

}
