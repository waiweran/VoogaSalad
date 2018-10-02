package general.actions.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;

/**
 * 
 * Strategy that does nothing
 * 
 * @author Nobody
 *
 */
public class DoNothing extends Strategy{

	public DoNothing(int waitPeriod) {
		super(waitPeriod);
	}
	
	public DoNothing(){
		super(0);
	}

	@Override
	public Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference) {
		return victim -> {};
	}

	@Override
	public Map<String, Number> getNumericalParameters() {
		return new HashMap<String, Number>();
	}

	@Override
	public Map<String, String> getStringParameters() {
		return new HashMap<String, String>();
	}

}
