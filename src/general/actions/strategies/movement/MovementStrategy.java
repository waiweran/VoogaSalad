package general.actions.strategies.movement;

import java.util.Collection;
import java.util.LinkedHashMap;

import general.Vector;
import general.actions.strategies.Strategy;
import general.attributes.Location;
import general.attributes.Velocity;
import general.entities.GameEntity;
import general.fields.Field;

/**
 * Defines a strategy that determines the behavior of objects that are not players. 
 * @author Andres Lebbos
 */
public abstract class MovementStrategy extends Strategy {

	/**
	 * Defines a movement strategy that defines the movement behavior of objects that are not players.
	 * @param tags ignored, not used on movement
	 * @param frequency ignored, because it should happen every iteration.
	 */
	public MovementStrategy(int waitPeriod) {
		super(waitPeriod);
	}

	/**
	 * Determines the angle between two vectors
	 * @param first The first vector
	 * @param second The second vector
	 * @return the angle in radians between both vectors
	 */
	protected double angleBetween(Vector first, Vector second) {
		return Math.atan2(first.getY() - second.getY(), first.getX() - second.getX());
	}

	protected Location getLocation(GameEntity target) {
		return target.getAttribute("Location", Location.class);
	}

	protected Velocity getVelocity(GameEntity object) {
		return object.getAttribute("Velocity", Velocity.class);
	}

	public abstract Collection<? extends Field<?>> getFields();
	
	public abstract void setFields(LinkedHashMap<String, Field<?>> fields);
}