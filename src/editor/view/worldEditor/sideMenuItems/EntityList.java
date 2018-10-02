package editor.view.worldEditor.sideMenuItems;

import java.util.List;
import java.util.stream.Collectors;

import editor.controllers.worldControllers.EntityMenuController;
import editor.view.worldEditor.EditorWorld;
import editor.view.worldEditor.entityDisplay.EntityView;
import general.ResourceLoader;
import general.storage.ObservableList;
import general.view.ScreenObject;
import javafx.geometry.Pos;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Displays a list of Entities that can be added to the game.
 * @author Nathaniel Brooke
 * @version 04-03-2017
 */
public class EntityList implements ScreenObject {
	
	private String myCategory;
	private EntityMenuController control;
	private FlowPane entityView;

	/**
	 * Initializes the EntityView.
	 * @param entities views of the Entities to display.
	 * @param category the name of the category of entities displayed.
	 */
	public EntityList(ObservableList<EntityView> entities, String category, EntityMenuController controller) {
		myCategory = category;
		control = controller;
		makeEntitiesView();
		entities.addObserver((o, arg) -> populateEntitiesView(entities));
		populateEntitiesView(entities);
	}
	
	/**
	 * @return the category of the entities in this view.
	 */
	public String getCategory() {
		return myCategory;
	}

	@Override
	public Region getView() {
		return entityView;
	}
	
	/**
	 * Sets up the Entities view
	 */
	private void makeEntitiesView() {
		entityView = new FlowPane();
		entityView.setId("acordian");
		entityView.setMinWidth(Integer.parseInt(
				ResourceLoader.EDITOR_GRAPHICS.getString("SideMenuWidth")));
		entityView.setAlignment(Pos.TOP_LEFT);
	}
	
	/**
	 * Populates the view with Entities
	 * @param entities graphical representation of Entities that can be placed on map.
	 */
	private void populateEntitiesView(List<EntityView> entities) {
		List<HBox> boxes = entities.stream().map(entity -> {
			HBox b = new HBox();
			b.getChildren().add(entity.getMenuView());
			b.setOnDragDetected(e -> {
				Dragboard db = b.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(EditorWorld.DRAGDROP_CONSTANT);
				db.setContent(content);
				db.setDragView(entity.getWorldView().getImage());
				control.setAddToWorld(entity.getEntity());
				e.consume();
			});
			return b;
		}).collect(Collectors.toList());
		entityView.getChildren().setAll(boxes);
	}

}
