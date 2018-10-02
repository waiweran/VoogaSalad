package play.view.popupMenuTypes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ControlsMenu extends PopupMenuItem {
	private List<String> ControlsList;

	public ControlsMenu() {
		super("Controls");
		ControlsList = new ArrayList<String>();
		ControlsList.add("In progress");
	}

	@Override
	public Pane getView() {
		Pane ControlsPane = new Pane();
		ControlsPane.getChildren().add(setControl());
		return ControlsPane;
	}
	private VBox setControl(){
		VBox controls = new VBox();
		for (int x = 0; x < ControlsList.size(); x++){
			controls.getChildren().add(new Label(ControlsList.get(x)));
		}
		return controls;
	}

}

