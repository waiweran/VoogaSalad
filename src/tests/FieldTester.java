package tests;
import java.util.LinkedHashMap;

import general.Vector;
import general.attributes.Attribute;
import general.attributes.Location;
import general.exceptions.IncorrectFieldException;
import general.fields.Field;

public class FieldTester {

	public static void main(String[] args) throws IncorrectFieldException {

		Attribute<Vector> positionb = new Location("position", new Vector(50, 50));
		Attribute<Vector> positionf = (Attribute<Vector>)positionb;
		
		System.out.println(positionb);
		positionb.setValue(new Vector(30, 40));
		System.out.println(positionb);
		
		LinkedHashMap<String, Field<?>> fields = positionf.getFields();
		fields.get("x").setValue("500");
		System.out.println(positionf.getValue());
		fields.get("y").setValue("6000");
		System.out.println(positionb.getValue());
		
		positionf.setValue(new Vector(0, 0.5));
		System.out.println(positionb);
		System.out.println(positionf);
		System.out.println(positionb.getValue());
		System.out.println(positionf.getValue());
	}

}
