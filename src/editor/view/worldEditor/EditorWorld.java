package editor.view.worldEditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import editor.controllers.worldControllers.WorldController;
import editor.view.background.BackgroundView;
import editor.view.worldEditor.entityDisplay.CircleZoneView;
import editor.view.worldEditor.entityDisplay.EntityView;
import editor.view.worldEditor.entityDisplay.ZoneView;
import general.Id;
import general.Vector;
import general.view.ScreenObject;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * Displays the primary view of the world on screen.
 * @author Nathaniel Brooke
 * @version 04-07-2017
 */
public class EditorWorld implements ScreenObject {
	
	/**
	 * String used to indicate that drag/drop's target is the world.
	 */
	public static final String DRAGDROP_CONSTANT = "Entity";
	
	private Map<Id, EntityView> entities;
	private List<BackgroundView> backgrounds;
	private Pane worldView;
	private WorldController control;
	private Vector positionOffset;
	private Vector moveOffset;

	/**
	 * Initializes a new EditorWorld world view.
	 * @param controller the WorldController that controls this view.
	 */
	public EditorWorld(WorldController controller) {
		entities = new HashMap<>();
		backgrounds = new ArrayList<>();
		worldView = new Pane();
		control = controller;
		positionOffset = Vector.ORIGIN;
		setupInputs();
	}
	
	/**
	 * Removes the given entity from the screen.
	 * @param id the Id of the entity to remove.
	 */
	public void remove(Id id) {
		EntityView remove = entities.remove(id);
		worldView.getChildren().remove(remove.getWorldView());
		for(CircleZoneView s : remove.getZoneViews()) {
			worldView.getChildren().remove(s.getView());
		}
	}
	
	/**
	 * Adds a new entity to the screen.
	 * @param id the Id of the new entity.
	 * @param newEntity the ImageView to display for that entity
	 */
	public void add(Id id, EntityView newEntity) {
		if(!entities.containsKey(id)) {
			entities.put(id, newEntity);
			for(ZoneView s : newEntity.getZoneViews()) {
				s.getView().setTranslateX(positionOffset.getX());
				s.getView().setTranslateY(positionOffset.getY());
				worldView.getChildren().add(backgrounds.size(), s.getView());
			}
			worldView.getChildren().add(newEntity.getWorldView());
		}
	}
	
	public Set<Id> getDisplayedIDs() {
		return Collections.unmodifiableSet(entities.keySet());
	}
	
	/**
	 * Sets the given entity to appear selected. Shows zones.
	 * @param id the ID of the entity to select.
	 */
	public void select(Id id) {
		entities.get(id).getZoneViews().forEach(zv -> zv.getView().setVisible(true));
	}
	
	/**
	 * Sets the given entity to appear not selected. Hides zones.
	 * @param id the ID of the entity to deselect
	 */
	public void deselect(Id id) {
		entities.get(id).getZoneViews().forEach(zv -> zv.getView().setVisible(false));
	}
	
	/**
	 * Adds the given background to the world view.
	 * @param newBackground the background to add.
	 */
	public void addBackground(BackgroundView newBackground) {
		newBackground.getView().setTranslateX(positionOffset.getX());
		newBackground.getView().setTranslateY(positionOffset.getY());
		worldView.getChildren().add(backgrounds.size(), newBackground.getView());
		backgrounds.add(newBackground);
	}
	
	/**
	 * Clears all backgrounds from the world view.
	 */
	public void clearBackgrounds() {
		worldView.getChildren().removeAll(
				backgrounds.stream().map(e -> e.getView()).collect(Collectors.toList()));
		backgrounds.clear();
	}
		
	@Override
	public Region getView() {
		return worldView;
	}
	
	/**
	 * Sets up inputs from the mouse to place and move objects
	 */
	private void setupInputs() {
		setupScreenDrag();
		setupAddingEntities();
	}

	/**
	 * Sets up inputs from mouse to move the screen
	 */
	private void setupScreenDrag() {
		worldView.setOnMousePressed(e -> {
			moveOffset = new Vector(e.getX(), e.getY());
			control.deselect();
		});
		worldView.setOnMouseDragged(e -> {
			Vector offset = (moveOffset.negate().offset(e.getX(), e.getY()));
			updateOffset(offset);
			moveOffset = new Vector(e.getX(), e.getY());
		});
	}

	/**
	 * Updates the offset of all children in the world view.
	 * @param offset the new translate offset of all children.
	 */
	private void updateOffset(Vector offset) {
		positionOffset = positionOffset.add(offset);

		worldView.getChildren().forEach(node -> {
			node.setTranslateX(positionOffset.getX());
			node.setTranslateY(positionOffset.getY());
		});
	}

	/**
	 * Sets up accepting drops to add entities
	 */
	private void setupAddingEntities() {
		worldView.setOnDragOver(e -> {
			if(e.getDragboard().getString().equals(DRAGDROP_CONSTANT)) {
				e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			e.consume();
		});
		worldView.setOnDragDropped(e -> {
			Vector v = new Vector(e.getX(), e.getY());
			control.addEntityToWorld(v.add(positionOffset.negate()));
			updateOffset(Vector.ORIGIN);
			e.setDropCompleted(true);
			e.consume();
		});
	}

}
