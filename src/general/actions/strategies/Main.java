package general.actions.strategies;

import java.util.ArrayList;
import java.util.List;

import general.Vector;
import general.actions.strategies.movement.CyclicalPath;
import general.attributes.Location;
import general.attributes.Velocity;
import general.entities.GameEntity;

public class Main {
	public static void main(String[] args) {
		GameEntity a = new GameEntity();
		a.getAttribute("Velocity", Velocity.class).setValue(new Vector(-10, -10));
		
		
		/*CyclicalPath path = new CyclicalPath();//int waitPeriod, List<Vector> givenPath
		path.followPath()*/ 
		System.out.println("intital:" + a.getAttribute("Location", Location.class).getValue());
		
		
		List<Vector> list = new ArrayList<Vector>();
		list.add(Vector.ORIGIN);
		list.add(new Vector(-40, 40));

		/*a.getAttribute("Movement", MovementAction.class).setValue(new NormalPath(0, list));
		a.getAttribute("Movement", MovementAction.class).getValue().getAction(null, null, 1).accept(a);
		System.out.println("end:" + a.getAttribute("Velocity", Velocity.class).getValue());*/ 

		
		CyclicalPath test = new CyclicalPath(1, list);
		System.out.println("test:" + a.getAttribute("Velocity", Velocity.class).getValue());
		CyclicalPath test2 = new CyclicalPath(1, list);
		System.out.println("test:" + a.getAttribute("Velocity", Velocity.class).getValue());
		
	}
}