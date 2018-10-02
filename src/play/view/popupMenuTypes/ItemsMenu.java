package play.view.popupMenuTypes;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import play.view.Controllas.ItemsControlla;

/**
 * Class that deals with setting up the TilePane for the items display.
 * @author Harry Liu
 * @version 04-05-17
 */
public class ItemsMenu extends PopupMenuItem {
	
	private ItemsControlla control;
	private TilePane view;
	
	public ItemsMenu(ItemsControlla controller) {
		super("Items");
		control = controller;
		view = new TilePane();
		setTilePaneConfigurations(view);
	}

	@Override
	public Pane getView() {
		return view;
	}
	
	public void updateItemsPane(List<ImageView> theItemsList) {
		view.getChildren().clear();
		for (ImageView iV: theItemsList){	
			Button button = new Button();
			button.setGraphic(iV);
			button.setOnAction(event -> control.setSelectedItem(theItemsList.indexOf(iV)));
			iV.setFitWidth(50);
			iV.setFitHeight(50);
			view.getChildren().add(button);
		}
	}
	
	/**
	 * Sets the common configurations for TilePanes.
	 * @param tile
	 */
	private void setTilePaneConfigurations(TilePane tile){
		tile.setPadding(new Insets(5));
		tile.setVgap(5);
		tile.setHgap(5);
		tile.setPrefColumns(3);
	}
}
	

