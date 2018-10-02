package editor.controllers;

import editor.view.EditorScreen;

/**
 * Interface that all controllers that control a specific editor scene implement.
 * Used to retrieve the EditorScreen they control for display purposes.
 * @author Nathaniel Brooke
 * @version 04-02-2017
 */
public interface ScreenController {
	
	/**
	 * @return the EditorScreen that this controls.
	 */
	public EditorScreen getEditorScreen();

}
