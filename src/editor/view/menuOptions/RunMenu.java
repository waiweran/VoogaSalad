package editor.view.menuOptions;

import editor.controllers.MenuController;
import general.ResourceLoader;
import javafx.scene.control.MenuItem;

public class RunMenu extends MenuItemView {


		public RunMenu(MenuController controller) {
			super(controller, new ResourceLoader().getDisplayResources().getString("Run"));
		}

		@Override
		protected void buildMenu() {
			MenuItem playGame = makeMenuItem("PlayGame", e -> getController().playGameClicked());
			getMenu().getItems().addAll(playGame);
		}
	}
