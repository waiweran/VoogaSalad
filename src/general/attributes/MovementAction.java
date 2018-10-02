package general.attributes;

import java.util.ArrayList;
import java.util.List;

import general.actions.strategies.Strategy;
import general.actions.strategies.movement.CircleMovement;
import general.actions.strategies.movement.CyclicalPath;
import general.actions.strategies.movement.KeyPressMovement;
import general.actions.strategies.movement.NoMovement;
import general.actions.strategies.movement.NormalPath;
import general.actions.strategies.movement.TowardsMovement;

public class MovementAction extends Action {

	public MovementAction(String name, Strategy value) {
		super(name, value);
	}
	
	public MovementAction(String name) {
		this(name, new NoMovement(0));
	}

	@Override
	public List<Strategy> getOptions() {
		List<Strategy> movements = new ArrayList<>();
		movements.add(new NoMovement(0));
		movements.add(new KeyPressMovement(0));
		movements.add(new TowardsMovement(0));
		movements.add(new CircleMovement(0));
		movements.add(new NormalPath(0));
		movements.add(new CyclicalPath(0));
		return movements;
	}

	@Override
	public int compareTo(Strategy o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
