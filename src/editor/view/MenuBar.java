package editor.view;

import editor.controllers.MenuController;
import editor.view.menuOptions.EditMenu;
import editor.view.menuOptions.FileMenu;
import editor.view.menuOptions.HelpMenu;
import editor.view.menuOptions.RunMenu;
import general.view.ScreenObject;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Maintains the menu bar at the top of the editor screen.
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class MenuBar implements ScreenObject {
	
	private javafx.scene.control.MenuBar menubar;
	private HBox hbox;

	/**
	 * Initializes the menu bar and its menus.
	 */
	public MenuBar(MenuController control) {
		menubar = new javafx.scene.control.MenuBar();
		FileMenu file = new FileMenu(control);
		EditMenu edit = new EditMenu(control);
		RunMenu run = new RunMenu(control);
		HelpMenu help = new HelpMenu(control);
		menubar.getMenus().addAll(file.getMenu(), edit.getMenu(), run.getMenu(), help.getMenu());
		createSwitchSceneButton(control);
	}

	public void createSwitchSceneButton(MenuController control){
		hbox = new HBox();
		SwitchButton sB= new SwitchButton(control);
		Button myButton = sB.getButton();
		myButton.setId("SwitchButton");
		HBox.setHgrow(menubar, Priority.SOMETIMES);
		hbox.getChildren().addAll(menubar, myButton);
	}
	
	@Override
	public Region getView() {
		return hbox;
	}


}
