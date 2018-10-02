package play.model.collisionmanager;

import java.util.List;

import general.Vector;
import general.attributes.Location;
import general.attributes.ZoneAttribute;

public class DirectCollisionChecker extends CollisionChecker {

	protected void determineCollisionPairs(List<ReadOnlyEntityPair> allPairs) {
		for (ReadOnlyEntityPair pair : allPairs){
			Zone hitZoneA = pair.getFirst().getReadOnlyAttribute("HitZone", ZoneAttribute.class).getValue();
			Zone hitZoneB = pair.getSecond().getReadOnlyAttribute("HitZone", ZoneAttribute.class).getValue();
			Vector locA = pair.getFirst().getReadOnlyAttribute("Location", Location.class).getValue();
			Vector locB = pair.getSecond().getReadOnlyAttribute("Location", Location.class).getValue();
			if (hitZoneA.intersects(hitZoneB, locA, locB)){
				this.getCurrentCollidingPairs().add(pair);
			}
		}
	}
}
