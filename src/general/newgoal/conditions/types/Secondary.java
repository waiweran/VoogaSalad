package general.newgoal.conditions.types;

import general.newgoal.conditions.NewCondition;

/**
 * Conditions that must be completed in order for Condition to pass
 * 
 * @author Justin
 * @author Ashka 
 * @version 4/28/2017.
 */
public class Secondary implements ConditionType {

	public Secondary(NewCondition c){
	}
	
	@Override
	public boolean necessary() {
		return false;
	}
}
