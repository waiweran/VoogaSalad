package general.attributes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeSet;

/**
 * Creates Attributes from a Attribute name. Will automatically register all
 * attributes from resource file.
 * 
 * This class is based on the "factory" design pattern:
 * http://www.oodesign.com/factory-pattern.html
 * 
 * All commands are instantiated each time it is retrieved from the map
 * 
 * Iterators along its String keys
 * 
 * @author DhruvKPatel
 * @author Gordon Huynh
 */
public class AttributeFactory {

	private static final ResourceBundle ATTRIBUTE_RESOURCE = ResourceBundle.getBundle(Attribute.ATTRIBUTES);
	private static final ResourceBundle OBSERVER_RESOURCE = ResourceBundle.getBundle(Attribute.OBSERVERS);
	private static final ResourceBundle ERRORS = ResourceBundle.getBundle("resources/ErrorMessages");
	private final static String CLASS_HEADER = "general.attributes.";
	private final static String NAME_DELIMITER = "_";
	
	private Map<String, Attribute<?>> registeredAttributes;
	private Map<String, ArrayList<Attribute<?>>> attributeTypes;

	/**
	 * Constructs an empty Attribute factory. Next, fills the factory
	 * automatically with Attributes from resource file
	 * 
	 * @throws Exception
	 * @throws CommandException
	 */
	public AttributeFactory() {
		registeredAttributes = new LinkedHashMap<String, Attribute<?>>();
		attributeTypes = new LinkedHashMap<String, ArrayList<Attribute<?>>>();
		TreeSet<String> keys = new TreeSet<>();
		keys.addAll(ATTRIBUTE_RESOURCE.keySet());
		for (String sAndOrder : keys) {
			String s = sAndOrder.substring(sAndOrder.indexOf(NAME_DELIMITER) + 1); 
			try {
				Class<?> key;
				key = Class.forName(CLASS_HEADER + ATTRIBUTE_RESOURCE.getString(sAndOrder).trim());
				Constructor<?> tempConstructor = key.getConstructor(String.class);
				Attribute<?> value = (Attribute<?>) tempConstructor.newInstance(s);
				this.addAttribute(s, value);
			} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(ERRORS.getString("AttributeFactory") + s, e);
			}
		}
	}

	/**
	 * Returns class of Attribute ID
	 */
	public Map<String, Attribute<?>> getDefaults() {
		return registeredAttributes;
	}

	private void addAttribute(String s, Attribute<?> value) {
		registeredAttributes.put(s, value);
		ArrayList<Attribute<?>> tempList = new ArrayList<Attribute<?>>();
		tempList.add(value);
		if (attributeTypes.containsKey(value.getClass().getSimpleName())) {
			attributeTypes.get(value.getClass().getSimpleName()).addAll(tempList);
		} else {
			attributeTypes.put(value.getClass().getSimpleName(), tempList);
		}
	}
}