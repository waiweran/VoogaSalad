package play.view.Controllas;

import java.util.List;
import java.util.stream.Collectors;

import general.attributes.Items;
import general.attributes.UIDataAttribute;
import general.items.ItemHolder;
import javafx.scene.image.ImageView;
import play.view.popupMenuTypes.ItemsMenu;

public class ItemsControlla {
	
	private PlayWorldControlla control;
	private ItemsMenu view;
	private ItemHolder items;
	private int numItems;
	
	public ItemsControlla(PlayWorldControlla controller) {
		control = controller;
		view = new ItemsMenu(this);
		setupItemsMenu();
	}
	
	public ItemsMenu getView() {
		return view;
	}
	
	private void setupItemsMenu() {
		Items itemsAttribute = control.getPlayer().getAttribute("Items", Items.class);
		items = itemsAttribute.getValue();
		numItems = 0;
		control.addUpdater(dt -> refreshItemMenu());
	}
	
	private void refreshItemMenu() {
		if(items.hasItems() && items.getItems().size() != numItems) {
			List<ImageView> views = items.getItems().stream()
					.map(entity -> new ImageView(entity.getReadOnlyAttribute(
							"UIDataAttribute", UIDataAttribute.class).getValue().getImageLocation()))
					.collect(Collectors.toList());
			view.updateItemsPane(views);
			numItems = views.size();
		}
	}
	
	public void setSelectedItem(int index) {
		items.setActive(index);
		control.getHUDController().refreshItemDisplay();
	}
	
	

}
