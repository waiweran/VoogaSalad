package editor.view.worldEditor.sideMenuItems;

import general.ResourceLoader;
import general.view.ScreenObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class AttributeView implements ScreenObject {
	
	private ObservableList<Pane> list;
	private ListView<Pane> view;

	/**
	 * Sets up a ListView of attributes.
	 * @param attributes the graphical representation of the attributes.
	 */
	public AttributeView() {
		list = FXCollections.observableArrayList();
		view = new ListView<Pane>(list);
		view.setMaxWidth(Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("AttributeMaxWidth")));
		view.setMaxHeight(0);
		clear();
	}

	/**
	 * Adds an attribute to the display.
	 * @param attribute the attribute to add.
	 */
	public void addAttribute(Pane attribute) {
		list.add(attribute);
		view.setMaxHeight(list.size() * Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("AttributeHeight")));
	}

	/**
	 * Clears the attribute display.
	 */
	public void clear() {
		view.setMaxHeight(0);
		list.clear();
	}

	@Override
	public Region getView() {
		return view;
	}

}
