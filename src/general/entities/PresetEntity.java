package general.entities;

import java.util.List;

import editor.externalAPI.EditorAttribute;
import editor.externalAPI.EditorEntity;
import general.Id;
import general.attributes.Attribute;
import general.attributes.AttributeHolder;

public class PresetEntity implements EditorEntity {
	private final AttributeHolder attributes;
	
	public PresetEntity(AttributeHolder attributesIn) {
		attributes = attributesIn;
	}
	
	public AttributeHolder getAttributes(){
		return attributes;
	}

	@Override
	public List<EditorAttribute<?>> getViewableAttributes() {
		return attributes.getEditorAttributes();
	}

	@Override
	public void addAttribute(EditorAttribute<?> a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Id getId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T getAttribute(String s, Class<T> c) {
		return attributes.get(s, c);
	}

	@Override
	public Attribute<?> getAttribute(String attributeID) {
		throw new UnsupportedOperationException();
	}
}
