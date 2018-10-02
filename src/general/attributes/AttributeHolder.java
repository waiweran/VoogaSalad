package general.attributes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import editor.externalAPI.EditorAttribute;
import play.externalAPI.PlayAttribute;

/**
 * Encapsulates the casting of the objects within the Attribute Map.
 * 
 * @author Advait Reddy, Alex Boss, Gordon Huynh, Dhruv Patel
 *
 */

public class AttributeHolder implements Iterable<Attribute<?>>, ReadOnlyAttributeHolder {

	private final static String ERROR_RESOURCE = "resources/ErrorMessages";
	private final static ResourceBundle resources = ResourceBundle.getBundle(ERROR_RESOURCE);
	private Map<String, Attribute<?>> attributes;

	public AttributeHolder() {
		AttributeFactory factory = new AttributeFactory();
		attributes = factory.getDefaults();
	}

	public AttributeHolder(AttributeHolder ah) {
		attributes = new LinkedHashMap<String, Attribute<?>>();
		for (Attribute<?> a : ah) {
			Attribute<?> tempAttribute = this.copyAttribute(a, a.getValue().getClass());
			attributes.put(a.getName(), tempAttribute);
		}
	}

	/**
	 * Returns an Attribute containing the correct data type as a generic
	 * 
	 * @param attributeID
	 * @param attributeValueClass
	 * @return
	 * @throws Exception
	 */

	public <T> T get(String attributeID, Class<T> attributeClass) {
		try {
			return attributeClass.cast(attributes.get(attributeID));
		} catch (Exception e) {
			throw new RuntimeException(
					attributeID + " " + resources.getString("MismatchedGet") + attributeClass.getSimpleName(), e);
		}
	}

	@Override
	public <S, T extends Attribute<S>> ReadOnlyAttribute<S> getReadOnlyAttribute(String attributeID,
			Class<T> attributeClass) {
		T ro = attributeClass.cast(attributes.get(attributeID));
		return ro;
	}

	public List<EditorAttribute<?>> getEditorAttributes() {
		return new ArrayList<EditorAttribute<?>>(attributes.values());
	}

	public List<PlayAttribute<?>> getPlayAttributes() {
		return new ArrayList<PlayAttribute<?>>(attributes.values());
	}

	/**
	 * Returns an Attribute containing a generic data type
	 * 
	 * @param attributeID
	 * @param attributeValueClass
	 * @return
	 * @throws Exception
	 */
	public Attribute<?> get(String attributeID) {
		return attributes.get(attributeID);
	}

	public boolean contains(String attributeID) {
		if (!attributes.containsKey(attributeID)){
			return false;
		}
		return attributes.get(attributeID).getValue() != null;
	}

	@Override
	public Iterator<Attribute<?>> iterator() {
		return attributes.values().iterator();
	}

	private Attribute<?> copyAttribute(Attribute<?> old, Class<?> value) {
		Attribute<?> ret;
		Class<?> clazz = old.getClass();
		Constructor<?> construct;
		try {
			construct = clazz.getDeclaredConstructor(String.class, value);
			ret = (Attribute<?>) construct.newInstance(old.getName(), old.getValue());
			return ret;
		} catch (NullPointerException e) {
			throw new RuntimeException(
					String.format(resources.getString("SuperClassError"), old.getClass().getSimpleName()));
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			return this.copyAttribute(old, value.getSuperclass());
		}
	}
}
