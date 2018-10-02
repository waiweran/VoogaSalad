package general.actions.strategies.movement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import general.Vector;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.fields.Field;
import general.fields.RangeField;
import general.keypress.ButtonList;
import general.keypress.KeyCommand;

/**
 * 
 * Changes entity velocity for key press
 * 
 * @author DhruvKPatel
 *
 */
public class KeyPressMovement extends MovementStrategy {
	private static final double DEFAULT_SPEED = 1;
	private double speed = DEFAULT_SPEED;

	public KeyPressMovement(int waitPeriod) {
		super(waitPeriod);
	}

	@Override
	public Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference) {
		return self -> {
			
			double dvx = 0;
			double dvy = 0;
			
			if(keys.containsCommand(KeyCommand.UP, self)) dvy += speed;
			if(keys.containsCommand(KeyCommand.DOWN, self)) dvy -= speed;
			if(keys.containsCommand(KeyCommand.RIGHT, self)) dvx += speed;
			if(keys.containsCommand(KeyCommand.LEFT, self)) dvx -= speed;
			
			getVelocity(self).setValue(new Vector(dvx, dvy));
			
		};
	}

	@Override
	public Collection<? extends Field<?>> getFields() {
		Collection<Field<?>> fields = new ArrayList<>();
		fields.add(new RangeField("Speed", speed, 0, 5));
		return fields;
	}

	@Override
	public void setFields(LinkedHashMap<String, Field<?>> fields) {
		speed = fields.get("Speed").getValue(Double.class);
	}
	
}
