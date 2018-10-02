package editor.view.worldEditor.sideMenuItems;

import java.util.List;

import general.ResourceLoader;
import general.storage.ObservableList;
import general.view.ScreenObject;
import general.view.Scrollable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 * An Accordion menu in which multiple units can expand.
 * @author Advait Reddy
 * @author Nathaniel Brooke
 * @version 03-24-2017
 */
public abstract class AccordionView <T> implements ScreenObject {

	private ScrollPane menu;
	private VBox stackedMenu;
	private double menuWidth;
	
	public AccordionView(ObservableList<T> accordionType) {
		menuWidth = Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("SideMenuWidth"));
		stackedMenu = new VBox();
		makeAccordion(accordionType);
		menu = new Scrollable(stackedMenu, menuWidth).getView();
		menu.setId("menu");
		accordionType.addObserver((o, arg) -> makeAccordion(accordionType));
	}

	/**
	 * Makes the menu for the TurtleView pane.
	 * @return ScrollPane containing the menu.
	 */
	private void makeAccordion(List<T> menuItems) {
		stackedMenu.getChildren().setAll(makeMenus(menuItems));
		stackedMenu.setPrefWidth(menuWidth);
		stackedMenu.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 80);
		stackedMenu.setId("sidemenu");
		setAllClosed(stackedMenu);
		setOpen(0);
	}
	
	/**
	 * Generates TitledPanes to represent EntityViews in the AccordionView.
	 * @param entityTypes the EntityViews to put into the AccordionView.
	 * @return List of TitledPanes.
	 */
	protected abstract List<TitledPane> makeMenus(List<T> entityTypes);

	/**
	 * Closes all menu options in the AccordionView.
	 * @param stackedMenu the menu pane.
	 */
	private void setAllClosed(final VBox stackedMenu) {
		for (int i=0; i<stackedMenu.getChildren().size(); i++){
			((TitledPane) stackedMenu.getChildren().get(i)).setExpanded(false);
		}
	}
	
	private void setOpen(int index) {
		if(! stackedMenu.getChildren().isEmpty()) {
			((TitledPane) stackedMenu.getChildren().get(index)).setExpanded(true);
		}
	}

	@Override
	public Region getView() {
		return menu;
	}

}


