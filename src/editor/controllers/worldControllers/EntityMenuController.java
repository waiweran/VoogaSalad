package editor.controllers.worldControllers;

import java.util.HashMap;
import java.util.Map;

import editor.externalAPI.EditorEntity;
import editor.externalAPI.EditorModel;
import editor.view.worldEditor.entityDisplay.EntityView;
import editor.view.worldEditor.sideMenuItems.EntitiesAccordion;
import editor.view.worldEditor.sideMenuItems.EntityList;
import editor.view.worldEditor.sideMenuItems.TabView;
import general.attributes.Tag;
import general.entities.EntityType;
import general.storage.ObservableList;

/**
 * Controls the entity menu on the side of the editor.
 * @author Nathaniel Brooke
 * @version 04-07-2017
 */
public class EntityMenuController {
	
	private EditorModel model;
	private Map<String, Map<String, ObservableList<EntityView>>> menuData;
	private Map<String, ObservableList<EntityList>> menuLists;
	private AttributesController attributes;
	private TabView entities;
	private EditorEntity addToWorld;
	private EditorEntity selected;

	/**
	 * Initializes the entity menu controller and the entity menu.
	 * @param gameModel the game model this menu edits.
	 */
	public EntityMenuController(EditorModel gameModel, AttributesController attributesControl) {
		model = gameModel;
		menuData = new HashMap<>();
		menuLists = new HashMap<>();
		makeMenuStructure();
		entities = new TabView();
		attributes = attributesControl;
		makeEntitiesView();
	}
	
	/**
	 * @return the SideMenu this controls.
	 */
	public TabView getEntitiesView() {
		return entities;
	}
	
	public void setAddToWorld(EditorEntity e) {
		addToWorld = e;
	}
	
	public EditorEntity getAddToWorld() {
		return addToWorld;
	}
	
	/**
	 * Sets an entity to display its attributes.
	 * @param e the entity.
	 */
	public void showAttributes(EditorEntity e) {
		selected = e;
		attributes.showAttributes(e);
	}
	
	/**
	 * Gets the entity whose attributes are being displayed.
	 * @return the EditorEntity
	 */
	public EditorEntity getSelectedEntity() {
		return selected;
	}

	public void hideAttributes() {
		attributes.hideAttributes();
	}

	private void makeMenuStructure() {
		for(EditorEntity e : model.getPresets()) {
			EntityType tag = e.getAttribute("Tag", Tag.class).getValue();
			if(!menuData.containsKey(tag.getType())) {
				menuData.put(tag.getType(), new HashMap<>());
			}
			if(!menuData.get(tag.getType()).containsKey(tag.getSubType())) {
				menuData.get(tag.getType()).put(tag.getSubType(), new ObservableList<>());
			}
			menuData.get(tag.getType()).get(tag.getSubType()).add(new EntityView(e));
		}
		model.getPresets().addObserver((o, arg) -> updateEntitiesView());
	}
	
	/**
	 * Generates the entities lists in the TabView.
	 */
	private void makeEntitiesView() {
		for(String type : menuData.keySet()) {
			entities.addTab(type, new EntitiesAccordion(makeAccordionList(type)));
		}
	}

	/**
	 * Creates a list of EntityList views for an AccordionView.
	 * @param name the name of the EntityList pane.
	 * @return an ObservableList of EntityLists for display.
	 */
	private ObservableList<EntityList> makeAccordionList(String name) {
		ObservableList<EntityList> list = new ObservableList<>();
		for(String subtype : menuData.get(name).keySet()) {
			list.add(new EntityList(menuData.get(name).get(subtype), subtype, this));
		}
		menuLists.put(name, list);
		return list;
	}
	
	/**
	 * Updates the Entity Preset Menu when the preset list changes.
	 */
	private void updateEntitiesView() {
		for(Map<String, ObservableList<EntityView>> map : menuData.values()) {
			for(ObservableList<EntityView> list : map.values()) {
				list.clear();
			}
		}
		for(EditorEntity e : model.getPresets()) {
			EntityType tag = e.getAttribute("Tag", Tag.class).getValue();
			if(!menuData.containsKey(tag.getType())) {
				menuData.put(tag.getType(), new HashMap<>());
				entities.addTab(tag.getType(), new EntitiesAccordion(makeAccordionList(tag.getType())));
			}
			if(!menuData.get(tag.getType()).containsKey(tag.getSubType())) {
				menuData.get(tag.getType()).put(tag.getSubType(), new ObservableList<>());
				menuLists.get(tag.getType()).add(new EntityList(
						menuData.get(tag.getType()).get(tag.getSubType()), tag.getSubType(), this));
			}
			menuData.get(tag.getType()).get(tag.getSubType()).add(new EntityView(e));
		}
	}

}
