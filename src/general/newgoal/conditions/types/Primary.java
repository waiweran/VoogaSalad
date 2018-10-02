package general.newgoal.conditions.types;

import general.newgoal.conditions.NewCondition;

/**
 * Conditions that must be completed in order for Condition to pass
 * 
 * @author Justin
 * @author Ashka 
 * @version 4/28/2017.
 */
public class Primary implements ConditionType {
	
	
	public Primary(NewCondition c){
	}

	@Override
	public boolean necessary() {
		return true;
	}


}
