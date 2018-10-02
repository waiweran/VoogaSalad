package general.attributes;

public interface ReadOnlyAttributeHolder {
	
	public <S, T extends Attribute<S>> ReadOnlyAttribute<S> getReadOnlyAttribute(String attributeID, Class<T> attributeClass);
	
}