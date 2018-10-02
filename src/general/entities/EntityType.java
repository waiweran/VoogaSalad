package general.entities;

import general.fields.selectionfields.Selectable;

import java.util.ResourceBundle;

/**
 * This class encapsulates the Type (String) and SubType (String)
 * @author DhruvKPatel
 *
 */
public class EntityType implements Comparable<EntityType>, Selectable {
	private String name, type, subType;

	private static final String BACKEND_BUNDLE = "resources/Backend";
	private ResourceBundle backendResource;
	
	public EntityType(String name, String type, String subType){
		this.name = name;
		this.type = type;
		this.subType = subType;
	}
	
	public EntityType(EntityType old) {
		this(old.getName(), old.getType(), old.getSubType());
	}

	public EntityType(){
		this("Name", "Type", "SubType");
		
	}
	/**
	 * Returns name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns main type
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Returns subtype
	 */
	public String getSubType(){
		return subType;
	}

	@Override
	public int compareTo(EntityType o) {
		return this.getType().compareTo(o.getType());
	}


	@Override
	public boolean equals(Object o) {
		boolean ret = false;
		if (o instanceof EntityType) {
			String any = backendResource.getString("any");
			EntityType other = (EntityType) o;
			ret = (name.equals(other.name) || name.equals(any) || other.name.equals(any));
			ret = ret & (type.equals(other.type) || type.equals(any) || other.type.equals(any));
			ret = ret & (subType.equals(other.type) || subType.equals(any) || other.subType.equals(any));
		}
		return ret;

	}

	@Override
	public String getStringRepresentation() {
		return String.format("%s (%s, %s)", name, type, subType);
	}
}
