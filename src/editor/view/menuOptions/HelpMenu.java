package editor.view.menuOptions;

import editor.controllers.MenuController;
import javafx.scene.control.MenuItem;

public class HelpMenu extends MenuItemView {

	/**
	 * Initializes the help menu.
	 * @param controller the menu controller.
	 */
	public HelpMenu(MenuController controller) {
		super(controller, "Help");
	}

	@Override
	protected void buildMenu() {
		MenuItem about = makeMenuItem("About", e -> getController().aboutClicked());
		getMenu().getItems().addAll(about);	
	}

}
