package general.actions.strategies;

import general.attributes.Health;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

public class Kill extends CollisionStrategy {

	public Kill(int waitPeriod) {
		super(waitPeriod);
	}

	@Override
	public void enterCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner,
			double timeDiff) {
		System.out.println("killed");
		recipient.getAttribute("Health", Health.class).setValue(0);
		recipient.setActive(false);		
	}

	@Override
	public void stayCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		
	}

	@Override
	public void exitCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
	
	}
	
	public String getStringRepresentation(){
		return "kill";
	}


}
