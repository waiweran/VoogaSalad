package general.actions.strategies.spawning;

import java.util.List;
import java.util.function.Consumer;

import general.Vector;
import general.actions.strategies.Strategy;
import general.attributes.Location;
import general.entities.EntityType;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

/**
 * 
 * Defines an Entity's ability to spawn other entities
 * 
 * @author DhruvKPatel
 *
 */
public class ContinuousSpawner extends Strategy {

	private double waitPeriod;
	private int spawnLimit;
	private EntityType entityType;
	private boolean isActive;
	
	/**
	 * Constructs a Spawner given 
	 * @param entityType Type of entity that will spawn
	 * @param waitPeriod Delay time between successive spawns
	 * @param spawnLimit Number of Entities at which Spawning stops occurring (-1 for infinite)
	 * @param isActive Whether spawner is active
	 */
	public ContinuousSpawner(EntityType entityType, double waitPeriod, int spawnLimit, boolean isActive) {
		super(waitPeriod, spawnLimit);
		
		this.entityType = entityType;
		this.waitPeriod = waitPeriod;
		this.spawnLimit = spawnLimit;
		this.isActive = isActive;
	}
	
	
	/**
	 * @return Delay time between successive spawns
	 */
	public double getWaitPeriod(){
		return waitPeriod;
	}
	
	/**
	 * @return spawnLimit Number of Entities at which Spawning stops occurring (-1 for infinite)
	 */
	public int getSpawnLimit(){
		return spawnLimit;
	}
	
	/**
	 * @return Type of entity that will spawn
	 */
	public EntityType getEntityType(){
		return entityType;
	}
	
	/**
	 * @return Whether spawner is active
	 */
	public boolean isActive(){
		return isActive;
	}


	@Override
	protected Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference) {
		
		return self -> {
			if(isActive){
				GameEntity spawned = spawner.spawnEntity(getEntityType());
				
				// Spawned item should be initial position of spawner entity
				Vector myCenter = self.getAttribute("Location", Location.class).getValue();
				spawned.getAttribute("Location", Location.class).setValue(myCenter);
			}
		};
		
	}

}
