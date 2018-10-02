package general.actions.strategies.movement;

import java.util.ArrayList;
import java.util.List;

import general.Vector;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.keypress.ButtonList;

public class CyclicalPath extends PathMovement {

	private List<Vector> path;
	private int follower;
	
	public CyclicalPath(int waitPeriod, List<Vector> givenPath) {
		super(waitPeriod, givenPath);
		path = givenPath;
		follower = 0;
	}
	
	public CyclicalPath(int waitPeriod) {
		this(waitPeriod, new ArrayList<>());
		path.add(Vector.ORIGIN);
	}

	protected GameEntity followPath(GameEntity victim, List<ReadOnlyEntity> entities, ButtonList keys, double totalTime){
		if(atPoint(path.get(follower), getLocation(victim).getValue()))
			follower = (follower+1)%path.size();
		new TowardsMovement(0, path.get(follower)).getAction(entities, keys, null, totalTime).accept(victim);
		return victim;
	}

	@Override
	protected void setPath(List<Vector> newPath) {
		path = newPath;
	}
}