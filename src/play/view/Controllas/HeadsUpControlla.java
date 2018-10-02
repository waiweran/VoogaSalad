package play.view.Controllas;

import editor.model.UIData;
import general.attributes.Health;
import general.attributes.Items;
import general.attributes.UIDataAttribute;
import general.entities.ReadOnlyEntity;
import general.items.ItemHolder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import play.view.HeadsUpDisplay;

/**
 * Controls the Heads-Up Display
 * @author Nathaniel Brooke
 * @version 04-20-2015
 */
public class HeadsUpControlla {
	
	private PlayWorldControlla control;
	private HeadsUpDisplay view;
	private ItemHolder items;
	private ImageView itemView;
	private ReadOnlyEntity displayedItem;

	public HeadsUpControlla(PlayWorldControlla controller) {
		control = controller;
		setupItemDisplay();
		view = new HeadsUpDisplay(this);
	}
	
	private void setupItemDisplay() {
		itemView = new ImageView();
		Items itemsAttribute = control.getPlayer().getAttribute("Items", Items.class);
		items = itemsAttribute.getValue();
		control.addUpdater(dt -> refreshItemDisplay());
	}
	
	public void refreshItemDisplay() {
		if(items.hasItems()) {
			ReadOnlyEntity activeItem = items.getActiveItem();
			if(displayedItem == null || !displayedItem.equals(activeItem)) {
				UIData itemData = activeItem.getReadOnlyAttribute("UIDataAttribute", UIDataAttribute.class).getValue();
				itemView.setImage(new Image(itemData.getImageLocation()));
				displayedItem = activeItem;
			}
		}
	}
	
	public ImageView getCurrentItem() {
		return itemView;
	}
	
	public double getCurrentHealth() {
		return control.getPlayer().getAttribute("Health", Health.class).getValue();
	}
	
	public PlayWorldControlla getMainController() {
		return control;
	}
	
	public HeadsUpDisplay getHUD() {
		return view;
	}
	
}
