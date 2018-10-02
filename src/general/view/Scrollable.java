package general.view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.stage.Screen;

/**
 * Used to make components scrollable.
 * @author Nathaniel Brooke
 * @author Advait Reddy
 * @version 04-04-2017
 */
public class Scrollable implements ScreenObject {

	private ScrollPane scrollPane;
	
	/**
	 * Creates a ScrollPane from the given pane.
	 * @param pane the input pane to make scrollable.
	 * @param width the width of the ScrollPane.
	 * @return the generated ScrollPane.
	 */
	public  Scrollable(Node pane, double width) {
		scrollPane = new ScrollPane();
		scrollPane.setContent(pane);
		scrollPane.setPrefWidth(width);
		scrollPane.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight());
		scrollPane.setMinViewportWidth(width - 20);
	}

	@Override
	public ScrollPane getView() {
		return scrollPane;
	}

}
