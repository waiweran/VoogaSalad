package general.newgoal.api;

import general.newgoal.states.State;

/**
 * Created by Justin on 4/30/2017.
 */
public interface TargetAPI {

    public <T extends State, U extends StateAPI> void updateTarget(T state, U stateObj);

}
