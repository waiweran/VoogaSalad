package general.actions.strategies;

import java.util.HashMap;
import java.util.Map;

import general.Vector;
import general.attributes.Location;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

public class SetLocation extends CollisionStrategy{

	public SetLocation(double waitPeriod) {
		super(waitPeriod);
	}

	
	@Override
	public Map<String, Number> initializeNumericalParameters(){
		Map<String, Number> numParams = new HashMap<>();
		numParams.put("X", 0.0);
		numParams.put("Y", 0.0);
		return numParams;
	}

	@Override
	public void enterCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner,
			double timeDiff) {
		 recipient.getAttribute("Location", Location.class).setValue(
				new Vector((double) getNumericalParameters().get("X"), (double) getNumericalParameters().get("Y")));
	}

	@Override
	public void stayCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner,
			double timeDiff) {
		
	}

	@Override
	public void exitCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner,
			double timeDiff) {
		// TODO Auto-generated method stub
		
	}

}
