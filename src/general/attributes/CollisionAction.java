package general.attributes;

import java.util.ArrayList;
import java.util.List;

import general.actions.strategies.DoNothingCollision;
import general.actions.strategies.Impassable;
import general.actions.strategies.Kill;
import general.actions.strategies.PickUp;
import general.actions.strategies.SetLocation;
import general.actions.strategies.SetVelocity;
import general.actions.strategies.Strategy;
import general.actions.strategies.collision.hit.HurtEntity;

public class CollisionAction extends Action {
	
	private static final int WAITPERIOD = 1;

	public CollisionAction(String name, Strategy value) {
		super(name, value);
	}
	
	public CollisionAction(String name){
		super (name, new Impassable(WAITPERIOD));
	}

	public List<Strategy> getOptions() {
		ArrayList<Strategy> list = new ArrayList<Strategy>();
		list.add(new Impassable(0));
		list.add(new DoNothingCollision(0));
		list.add(new Kill(0));
		list.add(new PickUp(0));
		list.add(new HurtEntity(0));
		list.add(new SetVelocity(0));
		list.add(new SetLocation(0));
		return list;
	}

	@Override
	public int compareTo(Strategy o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
