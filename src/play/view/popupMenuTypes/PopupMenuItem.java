package play.view.popupMenuTypes;

import general.view.ScreenObject;

/**
 * Class represents an item in the pop-up menu in the play environment
 * that can be accessed via the press of a button.  Subclasses define
 * specific, concrete menu items.
 * @author Nathaniel Brooke
 * @version 03-30-2017
 */
public abstract class PopupMenuItem implements ScreenObject {

	private String itemName;
	
	/**
	 * Constructor that creates an item in the pop-up menu
	 * @param name the name of the menu item
	 */
	protected PopupMenuItem(String name) {
		itemName = name;
	}
	
	/**
	 * Returns the name of the menu item.
	 * @return name
	 */
	public String getName() {
		return itemName;
	}
			
}
