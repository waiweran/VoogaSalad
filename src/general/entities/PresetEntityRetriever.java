package general.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import general.attributes.Tag;
import general.resourceEngine.FileType;
import general.resourceEngine.MultiEngine;
import general.resourceEngine.ResourceEngine;

/**
 * Retrives all preset entities currently saved
 * @author DhruvKPatel
 */
public class PresetEntityRetriever {
	
	private final static String PRESETS_FILE_PATH = "src/presets/";
	private final static String TYPES_FILE = "GameTypes.xml";
	private XStream serializer = new XStream(new DomDriver());
	private ResourceEngine entityFinder;


	/**
	 * Constructs a Retriever and loads all proper files
	 * from presets folder.
	 */
	public PresetEntityRetriever() {
		serializer = new XStream(new DomDriver());
	
		entityFinder = new MultiEngine(FileType.DATA);
		entityFinder.pullFiles(PRESETS_FILE_PATH);
	}
	
	/**
	 * Returns a Preset Entity given an entityType
	 * @param entityType
	 * @return preset entity
	 */
	public PresetEntity getPresetEntity(EntityType entityType) {

		Collection<File> entityFiles = entityFinder.get(FileType.DATA).values();

		try {
			
			File presetFile = entityFiles.stream()
					.filter(file -> {
						try {
							PresetEntity entity = (PresetEntity) serializer.fromXML(file);
							return entity.getAttribute("Tag", Tag.class).getValue().equals(entityType);
						}
						catch (Exception e){
							// file not a preset 
							return false;
						}
					})
					.findFirst()
					.get();

			return (PresetEntity) serializer.fromXML(presetFile);
			
		} catch (Exception e) {
			
			// Returns null if preset doesn't exist or load
			return null;
			
		}
		
	}
	
	/**
	 * Returns a collection of of all entity types in presets
	 */
	public Collection<EntityType> getPossibleEntityTypes() {
		
		try {
			entityFinder.pullFiles(PRESETS_FILE_PATH);
			File file = entityFinder.get(FileType.DATA, TYPES_FILE);
			EntityTypeCollection types = (EntityTypeCollection) serializer.fromXML(file);
			return types.getAllTypes();
		}
		catch (Exception e){
			return new ArrayList<>();
//			throw new RuntimeException(String.format("%s.xml could not load (preset EntityTypes)", TYPES_FILE));
		}
		
	}
	

}
