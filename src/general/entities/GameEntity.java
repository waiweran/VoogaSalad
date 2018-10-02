package general.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import editor.externalAPI.EditorAttribute;
import editor.externalAPI.EditorEntity;
import general.Id;
import general.actions.strategies.Actionable;
import general.attributes.Attribute;
import general.attributes.AttributeHolder;
import general.attributes.ReadOnlyAttribute;
import general.attributes.Tag;
import general.fields.selectionfields.Selectable;
import play.externalAPI.PlayAttribute;
import play.externalAPI.PlayEntity;

public class GameEntity implements EditorEntity, PlayEntity, Actionable, ReadOnlyEntity, Selectable {
	private Id myId;
	private AttributeHolder attributeHolder;
	private boolean active;

	public GameEntity() {
		myId = new Id();
		attributeHolder = new AttributeHolder();
		active = true;
	}

	public GameEntity(PresetEntity presetsIn) {
		myId = new Id();
		attributeHolder = new AttributeHolder(presetsIn.getAttributes());
		active = true;
	}

	public Id getId() {
		return myId;
	}

	public AttributeHolder getAttributeHolder() {
		return attributeHolder;
	}

	@Override
	public void run(Consumer<GameEntity> action){
		action.accept(this);
	}

	@Override
	public List<PlayAttribute<?>> getAttributes() {
		return attributeHolder.getPlayAttributes();
	}

	@Override
	public List<EditorAttribute<?>> getViewableAttributes() {
		return attributeHolder.getEditorAttributes();
	}

	@Override
	public void addAttribute(EditorAttribute<?> a) {
		throw new UnsupportedOperationException();
		// TODO add an implementation
	}

	public PresetEntity getPreset() {
		return new PresetEntity(attributeHolder);
	}

	/**
	 * Returns Attribute with a certain tag
	 * @param attributeName
	 * @param attributeClass
	 */
	@Override
	public <T> T getAttribute(String attributeName, Class<T> attributeClass) {
		return attributeHolder.get(attributeName, attributeClass);
	}
	
	public boolean containsAttribute(String s){
		return attributeHolder.contains(s);
	}

	/**
	 * Returns Attribute with a certain tag
	 * @param attributeID
	 * @param attributeClass
	 */
	@Override
	public Attribute<?> getAttribute(String attributeID) {
		return attributeHolder.get(attributeID);
	}
	
	public void resetObservers(){
		for(Attribute<?> a : attributeHolder){
			a.deleteObservers();
		}
	}

	@Override
	public ReadOnlyEntity getReadOnlyCopy() {
		return this;
	}

	public String getStringRepresentation() {
		return getAttribute("Tag", Tag.class).getValue().getStringRepresentation();
	}

	@Override
	public <S, T extends Attribute<S>> ReadOnlyAttribute<S> getReadOnlyAttribute(String attributeID, Class<T> attributeClass) {
		return attributeHolder.getReadOnlyAttribute(attributeID, attributeClass);
	}

	@Override
	public List<ReadOnlyEntity> getReadOnlyAsList() {
		List<ReadOnlyEntity> readOnlyList = new ArrayList<ReadOnlyEntity>();
		readOnlyList.add(this.getReadOnlyCopy());
		return readOnlyList;
	}
	
	public boolean equals(Object other){
		if(other == this){
			return true;
		}
		if(other == null){
			return false;
		}
		if(getClass() != other.getClass()){
			return false;
		}
		
		GameEntity pair = (GameEntity) other;
		
		return this.myId == pair.myId;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
