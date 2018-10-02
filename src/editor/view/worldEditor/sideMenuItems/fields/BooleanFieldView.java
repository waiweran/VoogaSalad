package editor.view.worldEditor.sideMenuItems.fields;

import general.exceptions.IncorrectFieldException;
import general.fields.BooleanField;
import general.fields.Field;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public class BooleanFieldView extends FieldView {

	public BooleanFieldView(Field<?> f) {
		super(f);
	}
	
	@Override
	protected Node makeFieldView(Field<?> f) {
		return makeBooleanFieldView((BooleanField) f);
	}
	
	/**
	 * Makes a CheckBox displaying the BooleanField.
	 * @param f the BooleanField to display.
	 * @return the CheckBox.
	 */
	private CheckBox makeBooleanFieldView(BooleanField f) {
		CheckBox box = new CheckBox(f.getName());
		box.setSelected(f.getValue());
		box.setOnAction(e -> {
			try {
				f.setValue(box.isSelected() + "");
			} catch (IncorrectFieldException e1) {
				throw new RuntimeException("Field did not accept boolean value "
						+ box.isSelected(), e1);
			}
		});
		return box;
	}

}
