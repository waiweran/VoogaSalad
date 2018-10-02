package general.actions.strategies.movement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import general.Vector;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.keypress.ButtonList;

// TODO ASK How do I know when the target is killed to go back to previous Action?
public class NormalPath extends PathMovement {

	private List<Vector> path;
	private int follower;
	
	public NormalPath(int waitPeriod, List<Vector> givenPath) {
		super(waitPeriod, givenPath);
		path = givenPath;
		follower = 0;
	}
	
	public NormalPath(int waitPeriod) {
		this(waitPeriod, new ArrayList<>());
		path.add(Vector.ORIGIN);
	}

	protected GameEntity followPath(GameEntity victim, List<ReadOnlyEntity> entities, ButtonList keys, double totalTime){
		if(atPoint(path.get(follower), getLocation(victim).getValue()) && follower<path.size())
			follower++;
		new TowardsMovement(0, path.get(follower)).getAction(entities, keys, null, totalTime).accept(victim);
		return victim;
	}

	@Override
	protected void setPath(List<Vector> newPath) {
		path = newPath;
	}
}

