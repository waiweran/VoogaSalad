package general.newgoal.states.substates;

import general.newgoal.api.StateAPI;
import general.newgoal.api.TargetAPI;
import general.newgoal.states.State;

public class Failed extends State {

    @Override
    public <T extends TargetAPI, U extends StateAPI> void doAction(T subCondition, U message) {
        subCondition.updateTarget(this, message);
    }

    @Override
    public boolean neededToPass() {
        return false;
    }

    @Override
    public boolean neededToFail() {
        return false;
    }

	@Override
	public boolean inProgress() {
		return false;
	}

}
