package editor.view.worldEditor.sideMenuItems.fields;

import general.exceptions.IncorrectFieldException;
import general.fields.Field;
import general.view.ExceptionHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class ValueFieldView extends FieldView {
	
	
	public ValueFieldView(Field<?> f) {
		super(f);
	}
	
	@Override
	protected Node makeFieldView(Field<?> f) {
		return makeValueFieldView(f);
	}
	
	/**
	 * Makes a JavaFX TextField displaying the ValueField.
	 * @param field the ValueField to display.
	 * @return the TextField.
	 */
	private Node makeValueFieldView(Field<?> field) {
		TextField input = new TextField();
		input.setMaxWidth(70);
		input.setText(field.getValue().toString());
		input.setOnKeyReleased(e -> {
			if(input.getText().length() > 0) {
				try {
					field.setValue(input.getText());
				} catch (IncorrectFieldException e1) {
					new ExceptionHandler().showAlert(e1);
				}
			}
		});
		input.setPromptText(field.getName());
		input.setTooltip(new Tooltip(field.getName()));
		return input;
	}

}
