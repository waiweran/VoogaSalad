package general.actions.strategies.collision.hit;

import java.util.HashMap;
import java.util.Map;

import general.actions.strategies.CollisionStrategy;
import general.attributes.Health;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

public class HurtEntity extends CollisionStrategy {

	public HurtEntity(double waitPeriod, int amountUses) {
		super(waitPeriod, amountUses);
	}
	
	public HurtEntity(double waitPeriod) {
		super(waitPeriod);
	}

	@Override
	public Map<String, Number> initializeNumericalParameters(){
		Map<String, Number> numParams = new HashMap<>();
		numParams.put("Damage", 10);
		return numParams;
	}

	@Override
	public void enterCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner,
			double timeDiff) {
		recipient.getAttribute("Health", Health.class).setValue(recipient.getAttribute("Health", Health.class).getValue() 
				- (Integer) getNumericalParameters().get("Damage"));		
	}

	@Override
	public void stayCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner,
			double timeDiff) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner,
			double timeDiff) {
		// TODO Auto-generated method stub
		
	}

}
