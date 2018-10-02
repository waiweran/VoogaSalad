package play.view.Controllas;

import java.io.File;

import general.ResourceLoader;
import general.view.ExceptionHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadMenuControlla {

	private File myGameFile;
	
	/**
	 * Runs the file chooser and instantiates the play window
	 * @param daStage
	 */
	public void runGame(Stage daStage){
		chooseFile(daStage);
		if(myGameFile != null) {
			try {
				new PlayWorldControlla(daStage, myGameFile);
			}
			catch(Exception e) {
				new ExceptionHandler().showAlert();
			}
		}
	}
	
	/**
	 * Opens up a file chooser for the user to pass in saved game data
	 * @param daStage
	 */
	private void chooseFile(Stage daStage){

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(new ResourceLoader().getDisplayResources().getString("uploadTitle"));
		File userDirectory = new File("src/games");
		fileChooser.setInitialDirectory(userDirectory);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Game Files", "*.xml")
				);
		myGameFile = fileChooser.showOpenDialog(daStage);
	}
	
	public File getFile(){
		return myGameFile;
	}
}