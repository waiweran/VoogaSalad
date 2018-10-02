package editor.controllers.worldControllers;

import editor.controllers.EditorController;
import editor.controllers.ScreenController;
import editor.externalAPI.EditorEntity;
import editor.externalAPI.EditorModel;
import editor.view.EditorScreen;
import editor.view.worldEditor.WorldEditorScreen;
import editor.view.worldEditor.sideMenuItems.fields.AttributesMenu;

/**
 * Controls the main screen in the world editor.
 * @author Nathaniel Brooke
 * @version 04-07-2017
 */
public class WorldEditorController implements ScreenController {
	
	private EditorModel model;
	private WorldEditorScreen view;
	private EntityMenuController menuControl;
	private WorldController worldControl;
	private AttributesController attributeControl;
	private AttributesMenu attributesMenu;

	/**
	 * Initializes the world editor controller.
	 * @param mainController the main editor controller.
	 */
	public WorldEditorController(EditorController mainController) {
		model = mainController.getModel();
		attributeControl = new AttributesController(model);
		menuControl = new EntityMenuController(model, attributeControl);
		attributesMenu = new AttributesMenu(attributeControl);
		worldControl = new WorldController(model, this);
		view = new WorldEditorScreen(this, worldControl.getWorldView(), 
				menuControl.getEntitiesView(), attributesMenu);
	}
	
	public void setSelectedEntity(EditorEntity entity) {
		menuControl.showAttributes(entity);
	}
	

	public void deleteSelectedEntity() {
		deselectEntity();
		EditorEntity remove = menuControl.getSelectedEntity();
		model.delete(remove.getId());
	}
	
	public void deselectEntity() {
		menuControl.hideAttributes();
	}
	
	public EditorEntity getAddEntity() {
		return menuControl.getAddToWorld();
	}

	@Override
	public EditorScreen getEditorScreen() {
		return view;
	}

}
