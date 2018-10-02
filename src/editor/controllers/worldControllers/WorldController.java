package editor.controllers.worldControllers;

import java.util.ArrayList;

import editor.externalAPI.EditorEntity;
import editor.externalAPI.EditorModel;
import editor.view.background.BackgroundView;
import editor.view.worldEditor.EditorWorld;
import editor.view.worldEditor.entityDisplay.EntityView;
import general.Id;
import general.Vector;
import general.attributes.Heading;
import general.attributes.Location;
import general.gameobjects.Background;

/**
 * Controls the world view within the editor.
 * @author Nathaniel Brooke
 * @version 04-07-2017
 */
public class WorldController {
	
	private EditorModel model;
	private EditorWorld view;
	private WorldEditorController control;
	private double dragHeading;
	private boolean dragStarted;
	
	/**
	 * Initializes a new world controller.
	 * @param model the back end model of the world being displayed.
	 */
	public WorldController(EditorModel gameModel, WorldEditorController mainController) {
		control = mainController;
		model = gameModel;
		view = new EditorWorld(this);
		model.getEntities().addObserver((o, arg) -> updateEntities());
		model.getBackgrounds().addObserver((o, arg) -> updateBackgrounds());
		updateEntities();
		updateBackgrounds();
	}
	
	/**
	 * Sets the selected entity to show its attributes.
	 * @param e the EditorEntity that has been selected.
	 */
	public void setSelected(EditorEntity e) {
		control.setSelectedEntity(e);
		for(EditorEntity de : model.getEntities()) {
			view.deselect(de.getId());
		}
		view.select(e.getId());
	}
	
	
	public void deselect() {
		for(EditorEntity de : model.getEntities()) {
			view.deselect(de.getId());
		}
		control.deselectEntity();
	}
	
	/**
	 * Adds the selected entity to the world, if an entity is selected.
	 * @param location the location to add the entity at.
	 */
	public void addEntityToWorld(Vector location) {
		if(control.getAddEntity() != null) {
			Id id = model.createNewInstance(control.getAddEntity());
			model.request(id).getAttribute("Location", Location.class).setValue(location.flip());
			setSelected(model.request(id));
		}
	}
	
	/**
	 * @return the EditorWorld ScreenObject that this controls.
	 */
	public EditorWorld getWorldView() {
		return view;
	}
	
	/**
	 * Updates all entities on the view.
	 */
	private void updateEntities() {
		ArrayList<Id> displayed = new ArrayList<>();
		displayed.addAll(view.getDisplayedIDs());
		for(EditorEntity entity : model.getEntities()) {
			EntityView entityView = new EntityView(entity);
			entityView.setOnMouseClick(e -> setSelected(e));
			entityView.setOnDrag((entView, press) -> {
				dragHeading = entView.getEntity().getAttribute("Heading", Heading.class).getValue();
				dragStarted = false;
			}, (entView, drag) -> {
				if(!dragStarted) {
					entView.getEntity().getAttribute("Heading", Heading.class).setValue(0.0);
					dragStarted = true;
				}
				entView.getWorldView().setRotate(0);
				entView.getEntity().getAttribute("Location", Location.class).setValue(
						new Vector(drag.getX(), drag.getY()).flip());
			}, (entView, release) -> {
				setSelected(entView.getEntity());
				entView.getEntity().getAttribute("Heading", Heading.class).setValue(dragHeading);
				dragStarted = false;
				System.out.println("Released " + entView.getEntity().getAttribute("Heading", Heading.class).getValue());
			});
			view.add(entity.getId(), entityView);
			displayed.remove(entity.getId());
		}
		for(Id id : displayed) {
			view.remove(id);
		}
	}
	
	/**
	 * Updates all backgrounds on the view.
	 */
	private void updateBackgrounds() {
		view.clearBackgrounds();
		for(Background b : model.getBackgrounds()) {
			BackgroundView bv = new BackgroundView(b.getImageURL());
			bv.setPosition(b.getPosition());
			bv.setSize(b.getSize());
			view.addBackground(bv);
		}
	}

}
