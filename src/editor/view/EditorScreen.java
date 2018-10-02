package editor.view;

import javafx.scene.layout.Pane;

/**
 * Interface that defines a screen that can be displayed in the editor.
 * @author Nathaniel Brooke
 * @version 03-30-2017
 */
public interface EditorScreen {

	/**
	 * Returns the Pane representing an editor window (Goals, World, etc)
	 * @return Pane to be displayed for that editor.
	 */
	public Pane getScreen();
	
}
