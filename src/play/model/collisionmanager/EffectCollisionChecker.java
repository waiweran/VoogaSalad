package play.model.collisionmanager;

import java.util.List;

import general.Vector;
import general.attributes.Location;
import general.attributes.ZoneAttribute;

public class EffectCollisionChecker extends CollisionChecker {

	@Override
	protected void determineCollisionPairs(List<ReadOnlyEntityPair> allPairs) {
		for (ReadOnlyEntityPair pair : allPairs){
			Zone effectZoneA = pair.getFirst().getReadOnlyAttribute("EffectZone", ZoneAttribute.class).getValue();
			Zone hitZoneB = pair.getSecond().getReadOnlyAttribute("HitZone", ZoneAttribute.class).getValue();
			Vector locA = pair.getFirst().getReadOnlyAttribute("Location", Location.class).getValue();
			Vector locB = pair.getSecond().getReadOnlyAttribute("Location", Location.class).getValue();
			if (effectZoneA.intersects(hitZoneB, locA, locB)){
				this.getCurrentCollidingPairs().add(pair);
			}
		}
	}

}
