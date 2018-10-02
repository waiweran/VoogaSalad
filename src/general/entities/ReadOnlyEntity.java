package general.entities;

import java.util.List;

import general.Id;
import general.actions.strategies.Actionable;
import general.attributes.Attribute;
import general.attributes.ReadOnlyAttribute;

public interface ReadOnlyEntity extends Actionable {
	
	public ReadOnlyEntity getReadOnlyCopy();
	
	public Id getId();
	
	public boolean isActive();
	
	public List<ReadOnlyEntity> getReadOnlyAsList();
	
	public <S, T extends Attribute<S>> ReadOnlyAttribute<S> getReadOnlyAttribute(String attributeID, Class<T> attributeClass);
}
