package general.actions.strategies.collision.hit;

import java.util.HashMap;
import java.util.Map;

import general.Vector;
import general.actions.strategies.CollisionStrategy;
import general.attributes.Velocity;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

public class DynamicFieldsTest extends CollisionStrategy{


	public DynamicFieldsTest(int waitPeriod) {
		super(waitPeriod);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enterCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner,
			double timeDiff) {
		
		recipient.run(e -> {
			e.getAttribute("Velocity", Velocity.class).setValue(
					new Vector((Double) getNumericalParameters().get("X vel"), 
							(Double) getNumericalParameters().get("Y vel")));
		});
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
	
	public String getStringRepresentation(){
		return "DynamicFieldsTest";
	}
	
	@Override
	public Map<String, Number> initializeNumericalParameters(){
		Map<String, Number> testMap = new HashMap<>();
		testMap.put("Y vel", 0.0);
		testMap.put("X vel", 0.0);
		return testMap;
	}
	

}
