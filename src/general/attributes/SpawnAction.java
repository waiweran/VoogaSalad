package general.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import general.actions.strategies.spawning.ContinuousSpawner;
import general.entities.EntityType;
import general.entities.PresetEntityRetriever;
import general.fields.BooleanField;
import general.fields.Field;
import general.fields.SelectionField;
import general.fields.valuefields.DoubleValueField;
import general.fields.valuefields.IntegerValueField;

/**
 * 
 * Defines the ability for an Entity to continously spawn other Entities
 * 
 * @author DhruvKPatel
 *
 */
public class SpawnAction extends Attribute<ContinuousSpawner> {

	private static final ContinuousSpawner DEFAULT_SPAWN = new ContinuousSpawner(new EntityType(), 1000, -1, false);
	
	private PresetEntityRetriever presets;

	public SpawnAction(String name, ContinuousSpawner value) {
		super(name, value);
	}

	public SpawnAction(String name){
		this(name, DEFAULT_SPAWN);
	}
	
	@Override
	protected List<Field<?>> updateFields(ContinuousSpawner value) {
		presets = new PresetEntityRetriever();
		List<Field<?>> fields = new ArrayList<>();
		
		fields.add(new BooleanField("Spawn?", value.isActive()));
		fields.add(new BooleanField("Infinite Spawns?", value.getSpawnLimit() == -1));
		fields.add(new IntegerValueField("Spawn Limit", value.getSpawnLimit()));
		fields.add(new DoubleValueField("Spawn Time Interval", value.getWaitPeriod()));
		
		EntityType type = value.getEntityType();
		List<EntityType> options = new ArrayList<>(presets.getPossibleEntityTypes());
					
		fields.add(new SelectionField<EntityType>("Tag", type, options));
		return fields;
	}

	@Override
	protected ContinuousSpawner updateValue(LinkedHashMap<String, Field<?>> fields) {

		boolean isActive = fields.get("Spawn?").getBoolean();
		boolean isInfinite = fields.get("Infinite Spawns?").getBoolean();
		double waitPeriod = fields.get("Spawn Time Interval").getDouble();
		int spawnLimit = isInfinite ?  -1 : fields.get("Spawn Limit").getInteger();
		EntityType entityType = fields.get("Tag").getValue(EntityType.class);
		
		return new ContinuousSpawner(entityType, waitPeriod, spawnLimit, isActive);
		
	}

	@Override
	public int compareTo(ContinuousSpawner o) {
		return 0;
	}
}
