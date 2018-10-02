package editor.view.worldEditor.sideMenuItems;

import general.view.ScreenObject;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Region;

/**
 * Generates Tabbed pane used to hold ListViews.
 * @author Nathaniel Brooke
 * @author Advait Reddy
 * @version 02-24-2017
 */
public class TabView implements ScreenObject {

	private TabPane tabs;
	
	/**
	 * Initializes an empty TabView.
	 */
	public TabView() {
		tabs = new TabPane();
		tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
	
	/**
	 * Adds a given tab to the tab pane.
	 * @param name of the tab.
	 * @param content Pane that the tab displays.
	 */
	public void addTab(String name, AccordionView<EntityList> content) {
		Tab tab = new Tab(name);
		tab.setContent(content.getView());
		tabs.getTabs().add(tab);
	}

	@Override
	public Region getView() {
		return tabs;
	}
	
}
