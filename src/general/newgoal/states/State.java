package general.newgoal.states;

import general.newgoal.api.StateAPI;
import general.newgoal.api.TargetAPI;

import java.util.ResourceBundle;

public abstract class State {

	private static final ResourceBundle DEFAULT_GOAL_STATES = ResourceBundle.getBundle("general.newgoal.resources.GoalState");

	public abstract <T extends TargetAPI, U extends StateAPI> void doAction(T subCondition, U message);

	public abstract boolean neededToPass();

	public abstract boolean inProgress();
	
	public abstract boolean neededToFail();

	@Override
	public String toString() {
		return DEFAULT_GOAL_STATES.getString(this.getClass().getSimpleName());
	}

	@Override
	public int hashCode() {
		return this.getClass().getSimpleName().hashCode();
	}
	
	

}
