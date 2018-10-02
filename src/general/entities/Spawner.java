package general.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import general.attributes.Tag;
import general.exceptions.EntityOverloadException;
import general.resourceEngine.FileType;
import general.resourceEngine.MultiEngine;
import general.resourceEngine.ResourceEngine;
import general.storage.GameState;

/**
 * This class adds the ability to spawn entities
 * 
 * @author DhruvKPatel
 *
 */
public class Spawner {
	private final static String PRESETS_FILE_PATH = "src/presets/";
	private static final int MAX_ENTITIES_PER_TYPE = 100;
	private XStream serializer = new XStream(new DomDriver());


	private GameState game;
	private Boolean access;
	private ResourceEngine entityFinder;
	
	/**
	 * Constructs a Spawner
	 * 
	 * @param game
	 * 		GameState of current play
	 * @param spawnAccess
	 * 		Modifier that decides whether spawning is possible.
	 * 		If false, when you try to spawn a certain type, an 
	 * 		Exception will be thrown.
	 * 		
	 * 		If true, spawner will work as normal
	 */
	public Spawner(GameState game, Boolean spawnAccess){
		this.game = game;
		this.access = spawnAccess;
		
		entityFinder = new MultiEngine(FileType.DATA);
		entityFinder.pullFiles(PRESETS_FILE_PATH);
	}
	
	/**
	 * Constructs a Spawner
	 * (Defaults to full access)
	 * 
	 * @param game
	 */
	public Spawner(GameState game){
		this(game, true);
	}
	
	
	/**
	 * Adds entity to field from given Tag. Checks
	 * names of current entities on field to determine
	 * a unique name for Tag with the same Type and Subtype
	 * 
	 * @param Tag
	 * @return Game Entity: Entity that was spawned
	 * 	
	 */
	public GameEntity spawnEntity(EntityType entityType){
		if(!access) return null;
		
		File presetFile = getPreset(entityType, serializer);
		PresetEntity originalPreset = (PresetEntity) serializer.fromXML(presetFile);
		
		EntityType newType;
		GameEntity newEntity;
		
		try {
			newType = getUniqueCopyType(entityType);
			newEntity = new GameEntity(originalPreset);
			newEntity.getAttribute("Tag", Tag.class).setValue(newType);
			game.addEntity(newEntity);
			
		} catch (EntityOverloadException e) {
			newEntity = null;
			// Do nothing - after max spawn, spawning just stops
		}
		
		return newEntity;
	}
	
	
	private File getPreset(EntityType entityType, XStream serializer) {

		Collection<File> entityFiles = entityFinder.get(FileType.DATA).values();

		return entityFiles.stream()
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
	}

	private EntityType getUniqueCopyType(EntityType original) throws EntityOverloadException {
		String originalName = original.getName();
		int maxIndex = 1;
		
		Collection<GameEntity> entities = getEntitiesOfType(original).collect(Collectors.toCollection(ArrayList::new));
		if(entities.size() == 0) return new EntityType(original);
		
		String testName = String.format("%s_%d", originalName, maxIndex);
		
		while(maxIndex < MAX_ENTITIES_PER_TYPE){
			for(GameEntity entity: entities){
				String name = entity.getAttribute("Tag", Tag.class).getValue().getName();
				if(!name.equals(testName)) return new EntityType(testName, original.getType(), original.getSubType());
			}
			maxIndex++;
		}
		throw new EntityOverloadException(String.format("Too many entities on the field of Tag: %s", original.getStringRepresentation()));		
	}
	
	private Stream<GameEntity> getEntitiesOfType(EntityType otherType){
		// name is not considered - only type & sub-type
		return game.getEntities().values().stream()
				.filter(entity -> {
							EntityType myType = entity.getAttribute("Tag", Tag.class).getValue();
							return myType.getType().equals(otherType.getType()) && myType.getSubType().equals(otherType.getSubType());
						});
	}
	
}
