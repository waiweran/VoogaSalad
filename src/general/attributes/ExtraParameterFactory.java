package general.attributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import general.fields.Field;
import general.fields.valuefields.DoubleValueField;
import general.fields.valuefields.IntegerValueField;
import general.fields.valuefields.StringValueField;

/**
 * 
 * @author Alex
 * A factory for creating fields from Maps of extra parameters for action attributes 
 */
public class ExtraParameterFactory {


	private final static String FIELD_DEFINITIONS = "resources/ExtraParameters";
	private final static ResourceBundle resources = ResourceBundle.getBundle(FIELD_DEFINITIONS);
	
	public ExtraParameterFactory(){
	}
	
	/**
	 * 
	 * @param numericalParams A map of parameter names to Number values 
	 * @return A List of fields with proper names and values generated from the given map
	 */
	public List<Field<?>> generateNumericalFields(Map<String, Number> numericalParams){
		List<Field<?>> fields = new ArrayList<Field<?>>();
		for(String key : numericalParams.keySet()){
			Number val = numericalParams.get(key);
			if(val instanceof Integer){
				fields.add(new IntegerValueField(key, (Integer) val));
			}
			else if (val instanceof Double){
				fields.add(new DoubleValueField(key, (Double) val));
			}
		}
		return fields;
	}
	
	/**
	 * 
	 * @param stringParams A map of parameter names to String values
	 * @return A List of fields with proper names and values generated from the given map
	 */
	public List<Field<?>> generateStringFields(Map<String, String> stringParams){
		List<Field<?>> fields = new ArrayList<Field<?>>();
		for(String key : stringParams.keySet()){
			String val = stringParams.get(key);
			fields.add(new StringValueField(key, val));
		}
		return fields;
	}
	
	/**
	 * 
	 * @param fields A List of fields with any values
	 * @return A Map of proper names to String values if there are any String fields in the given list
	 */
	public Map<String, String> generateStringMap(Collection<Field<?>> fields){
		Map<String, String> stringMap = new HashMap<>();
		for(Field<?> field : fields){
			if(field.getValue() instanceof String){
				stringMap.put(field.getName(), field.getString());
			}
		}
		return stringMap;
	}
	
	/**
	 * 
	 * @param fields A List of fields with any values
	 * @return A Map of proper names to numerical values if there are any Number fields in the given list
	 */
	public Map<String, Number> generateNumericalMap(Collection<Field<?>> fields){
		
		Map<String, Number> numericalMap = new HashMap<>();
		for(Field<?> field : fields){
			if(field.getValue() instanceof Integer ){
				numericalMap.put(field.getName(), field.getInteger());
			}
			else if(field.getValue() instanceof Double ){
				numericalMap.put(field.getName(), field.getDouble());
			}
		}
		return numericalMap;
	}
}
