package editor.view.menuOptions;

import editor.controllers.MenuController;
import general.ResourceLoader;
import javafx.scene.control.MenuItem;

/**
 * Creates and Displays the File menu.
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class FileMenu extends MenuItemView {
	
	/**
	 * Initializes the file menu.
	 * @param controller the menu controller.
	 */
	public FileMenu(MenuController controller) {
		super(controller, new ResourceLoader().getDisplayResources().getString("File"));
	}
	
	@Override
	protected void buildMenu() {
		MenuItem newOption = makeMenuItem("New", e -> getController().newClicked());
		MenuItem openOption = makeMenuItem("Open", e -> getController().loadClicked());
		MenuItem saveOption = makeMenuItem("Save", e -> getController().saveClicked());
		MenuItem saveAsOption = makeMenuItem("SaveAs", e -> getController().saveAsClicked());
		getMenu().getItems().addAll(newOption, openOption, saveOption, saveAsOption);
	}

}
