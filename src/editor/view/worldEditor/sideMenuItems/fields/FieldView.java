package editor.view.worldEditor.sideMenuItems.fields;

import general.fields.Field;
import javafx.scene.Node;

/**
 * Displays a field for editing on screen.
 * @author Nathaniel Brooke
 * @version 04-24-2017
 */
public abstract class FieldView {
	
	private Node myNode;
	
	/**
	 * Initializes the FieldView
	 * @param f the Field to display
	 */
	public FieldView(Field<?> f) {
		myNode = makeFieldView(f);
	}
	
	/**
	 * @return the Node representing this Field.
	 */
	public Node getNode() {
		return myNode;
	}
	
	/**
	 * Makes a Node displaying the field.
	 * @param f the Field to display.
	 * @return the Node.
	 */
	protected abstract Node makeFieldView(Field<?> f);

}
