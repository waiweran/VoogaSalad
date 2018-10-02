package general.newgoal.api;

import general.newgoal.states.State;

public interface StateAPI {

    /**
     * Sets the state of the condition
     * @param conditionState
     */
    public void setState(State conditionState);

    /**
     * Retrieves the state of the condition
     * @return
     */
    public State getCurrentState();

    /**
     * Retrieves the past state of the condition
     * @return
     */
    public State getPastState();

}
