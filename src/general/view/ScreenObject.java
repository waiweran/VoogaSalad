package general.view;

import javafx.scene.layout.Region;

/**
 * Interface defines an object that can be displayed on screen.
 * @author Nathaniel Brooke
 * @version 03-30-2017
 */
public interface ScreenObject {
	
	/**
	 * Returns the pane to be displayed on screen
	 * @return Pane
	 */
	public Region getView();

}
