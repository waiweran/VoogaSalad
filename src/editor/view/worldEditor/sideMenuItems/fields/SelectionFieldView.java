package editor.view.worldEditor.sideMenuItems.fields;

import general.exceptions.IncorrectFieldException;
import general.fields.Field;
import general.fields.SelectionField;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class SelectionFieldView extends FieldView {

	public SelectionFieldView(Field<?> f) {
		super(f);
	}
	
	@Override
	protected Node makeFieldView(Field<?> f) {
		return makeSelectionFieldView((SelectionField<?>) f);
	}
	
	/**
	 * Makes a ComboBox displaying the SelectionField.
	 * @param f the SelectionField to display.
	 * @return the ComboBox.
	 */
	private ComboBox<String> makeSelectionFieldView(SelectionField<?> f) {
		ComboBox<String> box = new ComboBox<>();
		try {
			box.setItems(FXCollections.observableList(f.getOptions()));
		} catch (IncorrectFieldException e2) {
			throw new RuntimeException("Selection Field did not present options", e2);
		}
		box.getSelectionModel().select(f.getValue().getStringRepresentation());
		box.setOnAction(e -> {
			try {
				f.setValue(box.getValue());
			} catch (IncorrectFieldException e1) {
				throw new RuntimeException("Field did not accept selection box value "
						+ box.getValue(), e1);
			}
		});
		return box;
	}

}
