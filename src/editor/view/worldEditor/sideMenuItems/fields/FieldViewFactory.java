package editor.view.worldEditor.sideMenuItems.fields;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import general.fields.Field;
import javafx.scene.Node;

/**
 * Creates views of fields for the attribute viewer.
 * @author Nathaniel Brooke
 * @version 04-10-2017
 */
public class FieldViewFactory {
	
	private static final ResourceBundle FIELDS = ResourceBundle.getBundle("resources.Fields");
	private static final String VIEW_PATH = "editor.view.worldEditor.sideMenuItems.fields.";
	
	/**
	 * Generates an editable view of the given field.
	 * @param field the field to view.
	 * @return Node containing an editable view.
	 */
	public Node makeFieldView(Field<?> field) {
		try {
			FieldView view;
			String className = field.getClass().getSimpleName();
			String viewName = VIEW_PATH + FIELDS.getString(className);
			Class<?> viewType = Class.forName(viewName);
			Constructor<?> constructor = viewType.getConstructor(Field.class);
			view = (FieldView) constructor.newInstance(field);
			return view.getNode();
		} 
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new RuntimeException(e);
		}
	}




}
