package general.newgoal.messages;

import general.newgoal.api.StateAPI;
import general.newgoal.states.State;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Justin on 4/27/2017.
 */

public abstract class Message<T> extends Observable implements Comparable<T>, StateAPI, Observer{

    private State currentConditionState, pastConditionState;

    /**
     * Sets the state of the condition
     * @param conditionState
     */
    @Override
    public void setState(State conditionState){
        this.pastConditionState = this.currentConditionState;
        this.currentConditionState = conditionState;
    }

    /**
     * Retrieves the state of the condition
     * @return
     */
    @Override
    public State getCurrentState(){
        return this.currentConditionState;
    }

    /**
     * Retrieves the past state of the condition
     * @return
     */
    @Override
    public State getPastState(){
        return this.pastConditionState;
    }
}
