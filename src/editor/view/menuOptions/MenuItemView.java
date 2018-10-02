package editor.view.menuOptions;

import editor.controllers.MenuController;
import general.ResourceLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Abstract class representing an item within the top menu bar.
 * @author Nathaniel Brooke
 * @version 04-10-2017
 */
public abstract class MenuItemView {
	
	private Menu menu;
	private MenuController control;
	
	/**
	 * Initializes a new MenuItemView
	 * @param controller the MenuController controlling this menu.
	 * @param name the name of this menu.
	 */
	public MenuItemView(MenuController controller, String name) {
		control = controller;
		menu = new Menu(name);
		buildMenu();
	}
	
	/**
	 * @return the file Menu.
	 */
	public Menu getMenu() {
		return menu;
	}
	
	/**
	 * Populates the menu.
	 */
	protected abstract void buildMenu();
	
	/**
	 * @return the menu controller.
	 */
	protected MenuController getController() {
		return control;
	}
	
	/**
	 * Makes a menu item with the given text and action.
	 * @param resourceText the string to get the label text from the resource file.
	 * @param onClick what happens when the menu is clicked.
	 * @return the MenuItem
	 */
	protected MenuItem makeMenuItem(String resourceText, EventHandler<ActionEvent> onClick) {
		MenuItem item = new MenuItem(new ResourceLoader().getDisplayResources().getString(resourceText));
		item.setOnAction(onClick);
		return item;
	}

}
