package play.model.collisionmanager;

import general.Vector;
import general.attributes.Location;
import general.attributes.Velocity;
import general.entities.ReadOnlyEntity;

/**
 * Abstract class used to handle two objects colliding. //TODO: update comments
 * Concrete implementations describe how to modify objects on collision.
 * @author Nathaniel Brooke
 * @author Advait Reddy
 * @author Alex Boss
 * @version 04-02-2017
 */
public class CollisionCalculator {
	
	/**
	 * Calculates the velocity component perpendicular to the collision plane.
	 * This is the velocity in the direction that the object was hit.
	 * @return Vector of the perpendicular velocity
	 */
	public Vector getPerpendicularVelocity(ReadOnlyEntity actor, ReadOnlyEntity recipient) {
		Vector radial = actor.getReadOnlyAttribute("Location", Location.class).getValue().add(
				        recipient.getReadOnlyAttribute("Location", Location.class).getValue().negate());
		double dotProd = recipient.getReadOnlyAttribute("Velocity", Velocity.class).getValue().dot(radial);
		double projLength = dotProd/radial.magnitude();
		Vector projection = radial.scalarMultiply(projLength/radial.magnitude());
		return projection;
	}
	
	/**
	 * Calculates the velocity component parallel to the collision plane.
	 * This velocity component that does not move the object closer to the collision point.
	 * @return Vector of the parallel velocity.
	 */
	public Vector getParallelVelocity(ReadOnlyEntity actor, ReadOnlyEntity recipient) {
		Vector perpendicular = getPerpendicularVelocity(actor, recipient);
		Vector parallel = recipient.getReadOnlyAttribute("Velocity", Velocity.class).getValue().add(
				          perpendicular.negate());
		return parallel;
	}
	
	/**
	 * Determines whether the velocity is in the direction of the collision.
	 * @return true if the velocity is moving towards the collision, false if it's moving away.
	 */
	public boolean isMovingTowardsCollision(ReadOnlyEntity actor, ReadOnlyEntity recipient) {
		Vector radial = actor.getReadOnlyAttribute("Location", Location.class).getValue().add(
				        recipient.getReadOnlyAttribute("Location", Location.class).getValue().negate());
		double dotProd = recipient.getReadOnlyAttribute("Velocity", Velocity.class).getValue().dot(radial);
		return dotProd > 0;
	}

}
