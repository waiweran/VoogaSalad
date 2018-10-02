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
import general.fields.valuefields.DoubleValueField;
import general.keypress.ButtonList;

/**
 * Moves in a parametrized circle - MovementStrategy
 * @author AndresLebbos
 */
public class CircleMovement extends MovementStrategy{

	private Vector center;
	private double radius;
	private double velocity = 0.0;

	/**
	 * Creates a movement strategy that allows an object to move over a parametric circle
	 * @param tags ignored, used for other strategies
	 * @param frequency ignored and set to default in this case, because movement actions occur every iteration
	 * @param cent center of the circle
	 * @param r radius of the circle
	 */
	public CircleMovement(int waitPeriod, Vector cent, double r) {
		super(waitPeriod);
		center = cent;
		radius = r;
	}
	
	public CircleMovement(int waitPeriod) {
		this(waitPeriod, Vector.ORIGIN, 0.0);
	}

	private GameEntity moveAround(GameEntity victim, double timeDifference){
		if(velocity == 0) velocity = getVelocity(victim).getValue().magnitude();
		Vector vicPos = getLocation(victim).getValue();
		//Vector cenPos = center.getAttribute("Location", Location.class).getValue();		
		double angle = vicPos.add(center.negate()).angleRad() + velocity * timeDifference / radius;
		getVelocity(victim).setValue(new Vector(center.getX() + (radius * Math.cos(angle)) - vicPos.getX(), center.getY() + (radius * Math.sin(angle) - vicPos.getY())));
		return victim;
	}

	/**
	 * Returns a consumer with the objective of updating the location of an objective to advance over the circle
	 */
	@Override
	public Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference) {
		return victim -> moveAround(victim, timeDifference);
	}

	@Override
	public Collection<? extends Field<?>> getFields() {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new DoubleValueField("Center x", center.getX()));
		fields.add(new DoubleValueField("Center y", center.getY()));
		fields.add(new DoubleValueField("Radius", radius));
		return fields;
	}

	@Override
	public void setFields(LinkedHashMap<String, Field<?>> fields) {
		center = new Vector(fields.get("Center x").getDouble(), fields.get("Center y").getDouble());
		radius = fields.get("Radius").getDouble();
	}
}