package general.newgoal.conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import general.newgoal.api.StateAPI;
import general.newgoal.api.TargetAPI;
import general.newgoal.conditions.util.Comparison;
import general.newgoal.conditions.util.Join;
import general.newgoal.messages.Message;
import general.newgoal.states.State;

/**
 * @author J Wang
 * @author Ashna
 */
public class NewCondition<T extends Message> extends Observable implements Observer, StateAPI, TargetAPI {

    private List<NewCondition> parallelConditions;

    private State currentConditionState, pastConditionState, comparisonState, defaultState;

    private String conditionDescription;

    private Comparison messageComparison;
    private T targetMessage;

    private List<T> messageList;

    private int numOfExecutions;

    private StateMap messageStateMap;

    private static final int DEFAULT_VAL = 1;

    //TODO: when adding messages from gamestate to the condition, its state is set to the default state defined by the condition

    /**
     * Default constructor; uses EQUALS comparison
     * @param targetMessage
     */
    public NewCondition(T targetMessage, State comparisonState, State defaultState){
        this(targetMessage, Comparison.EQUALS, comparisonState, defaultState, DEFAULT_VAL);
    }

    /**
     * Default constructor; uses EQUALS comparison
     * @param targetMessage
     */
    public NewCondition(T targetMessage, State comparisonState,  State defaultState, int numOfExecutions){
 
        this(targetMessage, Comparison.EQUALS, comparisonState, defaultState, numOfExecutions);
    }

    /**
     * SubCondition to compare messages against the targetMessage via messageComparison
     * @param targetMessage ex time greater than 20
     * @param messageComparison
     * @param comparisonState
     * 
     * flip logic in failed state
     */
    public NewCondition(T targetMessage, Comparison messageComparison, State comparisonState, State defaultState, int numOfExecutions){

        this.defaultSettings();

        this.messageComparison = messageComparison;
        this.targetMessage = targetMessage;
        this.comparisonState = comparisonState;
        this.defaultState = defaultState;
        this.numOfExecutions = numOfExecutions;

        this.setState(this.defaultState);

    }

    /**
     * Dictates what is set by default for each condition
     */
    private void defaultSettings(){
        this.messageList = new ArrayList<T>();
    }

    /**
     * Return target message
     */
    public T getTargetMessage(){
        return this.targetMessage;
    }
    
    /**
     * Default set of method calls for sending observable notifications
     */
    private void stateChangeNotification(){
        this.setChanged();
        this.notifyObservers();
    }

    public void addParallelCondition(NewCondition xCondition, Join conditionJoin){
        //TODO: Implement Join
        parallelConditions.add(xCondition);
    }

    public void setConditionDescription(String conditionDescription){
        this.conditionDescription = conditionDescription;
    }

    public String getConditionDescription(){
        return this.conditionDescription;
    }

    /*Primary Methods*/
    /**
     * Execute the comparison
     * @param checkMessage
     */
    public void execute(T checkMessage){
        for(Message message : messageList){
            messageComparison.checkValue(checkMessage, targetMessage, comparisonState, this);
        }
    }

    public void addMessageToObserve(T observeMessage){
        this.messageList.add(observeMessage);
    }

    /*Parent Methods*/

    @Override
    public void setState(State conditionState) {
        this.pastConditionState = this.currentConditionState;
        this.currentConditionState = conditionState;
        this.stateChangeNotification();
    }

    @Override
    public State getCurrentState() {
        return this.currentConditionState;
    }


    @Override
    public State getPastState() {
        return pastConditionState;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public <T extends State, U extends StateAPI> void updateTarget(T state, U stateObj) {
        
    	this.messageStateMap.moveItem(state, stateObj);
        
        if (state.neededToPass() && messageStateMap.containsState(state)){
        	if(messageStateMap.numOfState(state) >= this.numOfExecutions){
        		this.setState(state);
        	}
        }
       
    }
}
