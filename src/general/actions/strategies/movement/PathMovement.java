package general.actions.strategies.movement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import general.Vector;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.fields.Field;
import general.fields.valuefields.DoubleValueField;
import general.keypress.ButtonList;

public abstract class PathMovement extends LinearStrategy {

	private List<Vector> path;
	
	public PathMovement(int waitPeriod, List<Vector> pPath) {
		super(waitPeriod);
		path = pPath;
	}

	@Override
	public Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference) {
		return victim -> followPath(victim, entities, keys, timeDifference);
	}

	protected abstract GameEntity followPath(GameEntity victim, List<ReadOnlyEntity> entities, ButtonList keys, double timeDifference);

	protected abstract void setPath(List<Vector> newPath);
	
	@Override
	public Collection<? extends Field<?>> getFields() {
		List<Field<?>> fields = new ArrayList<>();
		for(int i = 1; i <= path.size(); i++) {
			fields.add(new DoubleValueField("Point " + i + " x", path.get(i-1).getX()));
			fields.add(new DoubleValueField("Point " + i + " y", path.get(i-1).getY()));
		}
		fields.add(new DoubleValueField("Point " + (path.size() + 1) + " x", path.get(path.size()-1).getX()));
		fields.add(new DoubleValueField("Point " + (path.size() + 1) + " y", path.get(path.size()-1).getY()));
		return fields;
	}

	@Override
	public void setFields(LinkedHashMap<String, Field<?>> fields) {
		List<Vector> createdPath = new ArrayList<>();
		for(int i = 1; i <= path.size(); i++) 
			createdPath.add(new Vector(fields.get("Point " + i + " x").getDouble(), fields.get("Point " + i + " y").getDouble()));
		Vector last = new Vector(fields.get("Point " + (path.size() + 1) + " x").getDouble(), fields.get("Point " + (path.size() + 1) + " y").getDouble());
		if(! new Vector(fields.get("Point " + path.size() + " x").getDouble(), fields.get("Point " + path.size() + " y").getDouble()).equals(last))
			createdPath.add(last);
		path = createdPath;
		setPath(createdPath);
	}
}
