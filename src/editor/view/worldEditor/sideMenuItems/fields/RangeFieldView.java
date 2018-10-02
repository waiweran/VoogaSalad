package editor.view.worldEditor.sideMenuItems.fields;

import general.exceptions.IncorrectFieldException;
import general.fields.Field;
import general.fields.RangeField;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class RangeFieldView extends FieldView {

	public RangeFieldView(Field<?> f) {
		super(f);
	}
	
	@Override
	protected Node makeFieldView(Field<?> f) {
		return makeRangeFieldView((RangeField) f);
	}
	
	/**
	 * Makes a Slider displaying the RangeField.
	 * @param f the RangeField to display.
	 * @return the Slider and a text display of its current value.
	 */
	private Node makeRangeFieldView(RangeField f) {
		HBox box = new HBox(2);
		Text text = new Text(Math.round(f.getValue()) + "");
		Slider slider = new Slider();
		double min = f.getMin();
		double max = f.getMax();
		if(min < max) {
			slider.setMin(min); 
			slider.setMax(max);
			slider.setValue(f.getValue());
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			slider.setMajorTickUnit(Math.round((max - min)/10)*10);
			slider.setMinorTickCount(5);
			slider.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> updateRangeField(f, text, slider));
			slider.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> updateRangeField(f, text, slider));
			box.getChildren().addAll(slider, text);
		}
		return box;
	}
	
	private void updateRangeField(RangeField f, Text text, Slider slider) {
		try {
			f.setValue(slider.getValue() + "");
			text.setText(Math.round(slider.getValue()) + "");
		} catch (IncorrectFieldException e1) {
			throw new RuntimeException("RangeField did not accept double value "
					+ slider.getValue(), e1);
		}
	}

}
