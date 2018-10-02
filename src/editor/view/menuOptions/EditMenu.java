package editor.view.menuOptions;

import editor.controllers.MenuController;
import general.ResourceLoader;
import javafx.scene.control.MenuItem;

public class EditMenu extends MenuItemView {

	public EditMenu(MenuController controller) {
		super(controller, new ResourceLoader().getDisplayResources().getString("Edit"));
	}

	@Override
	protected void buildMenu() {
		MenuItem bkgdOption = makeMenuItem("Background", e -> getController().backgroundClicked());
		getMenu().getItems().addAll(bkgdOption);
	}

}
