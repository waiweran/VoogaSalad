package general.actions.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import general.attributes.Health;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

public class HealEntity extends Strategy {
	
	public HealEntity(int waitPeriod) {
		super(waitPeriod);
	}
	
	@Override
	public Map<String, Number> initializeNumericalParameters(){
		Map<String, Number> numParams = new HashMap<>();
		numParams.put("Amount", 10);
		return numParams;
	}

	@Override
	protected Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner,
			double timeDifference) {
		return e-> e.getAttribute("Health", Health.class).setValue(e.getAttribute("Health", Health.class).getValue() 
				+ (Integer) getNumericalParameters().get("Amount"));	
	}


}
