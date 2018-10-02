package general.attributes;

import java.util.ArrayList;
import java.util.List;

import general.actions.strategies.HealEntity;
import general.actions.strategies.Strategy;

public class ActivationAction extends Action {
	
	public ActivationAction(String name, Strategy value) {
		super(name, value);
	}
	
	public ActivationAction(String name) {
		this(name, new HealEntity(0));
	}

	@Override
	public List<Strategy> getOptions() {
		List<Strategy> movements = new ArrayList<>();
		movements.add(new HealEntity(0));
		return movements;
	}

	@Override
	public int compareTo(Strategy o) {
		return 0;
	}
}
