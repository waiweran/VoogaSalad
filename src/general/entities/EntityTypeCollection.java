package general.entities;

import java.util.Collection;

/**
 * 
 * Allows XStream serialization for a Collection of EntityTypes
 * 
 * @author DhruvKPatel
 *
 */
public class EntityTypeCollection {
	private Collection<EntityType> entityTypes;
	
	/**
	 * Stores collection input of EntityTypes in encapsulated object
	 * @param types
	 */
	public EntityTypeCollection(Collection<EntityType> entityTypes) {
		this.entityTypes = entityTypes;
	}
	
	/**
	 * Retrieves collection of entityTypes
	 * @return
	 */
	public Collection<EntityType> getAllTypes(){
		return entityTypes;
	}

}
