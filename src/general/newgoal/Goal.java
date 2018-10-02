package general.newgoal;
/**
 * Creates GoalNodes holding Conditions
 * @author Ashka Stephen
 * @author Justin Wang
 * @version 4-5-17
 */

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import editor.externalAPI.EditorGoal;
import general.newgoal.api.StateAPI;
import general.newgoal.api.TargetAPI;
import general.newgoal.conditions.NewCondition;
import general.newgoal.conditions.StateMap;
import general.newgoal.states.State;
import general.newgoal.states.substates.Failed;
import general.newgoal.states.substates.Incomplete;
import general.newgoal.states.substates.Passed;
import general.storage.ObservableList;
import play.externalAPI.PlayGoal;

public class Goal extends Observable implements EditorGoal, PlayGoal, Iterable<NewCondition>, StateAPI, Observer, TargetAPI{
	private boolean isActive;
	private String myGoalName;
	private String myGoalDescription;

	private StateMap<NewCondition> conditionStateMap;

	private ObservableList<Goal> myChildren;

	private State currentState, pastState;

	/**
	 * Default Constructor for a
	 * Use for the Root
	 */
	public Goal(String name, String desc) {
		this.myGoalName = name;
		this.myGoalDescription = desc;
		this.initDefaults();
	}

	/**
	 * Initialize the defaults;
	 */
	private void initDefaults(){
		this.conditionStateMap = new StateMap<NewCondition>();
		this.myChildren = new ObservableList<Goal>();
		this.isActive = false;
		this.setState(new Incomplete());
	}

	/*Shared Methods*/

	/**
	 * @return the String indicator of the name of the Goal Object that is currently "active"
	 */
	@Override
	public String getName(){
		return this.myGoalName;
	}

	/**
	 * Enables the user to see which goals are in progress and which have been completed
	 * @return the String description of the goal
	 */
	@Override
	public String getDescription(){
		return this.myGoalDescription;
	}

	public boolean isActive(){
		return this.isActive;
	}
	
	public boolean setActive(boolean active){
		return this.isActive = active;
	}
	
	/*EditorGoal Methods*/

	/**
	 * @param c condition to add to the specified Goal
	 */
	@Override
	public void addCondition(NewCondition c) {
		c.addObserver(this);
		conditionStateMap.addItem(c.getCurrentState(), c);
	}

	/**
	 * Adds a child to the current goal
	 * @param goal
     */
	@Override
	public void addChild() {
		Goal newChild = new Goal("", "");
		myChildren.add(newChild);
	}

    /**
	 * Sets the name of the goal
	 * @param name the new text of the goal's title
     */
	@Override
	public void setName(String name) {
		myGoalName = name;
	}

	/**
	 * Sets the desciption of the goal
	 * @param text the text to replace the description/objectives
     */
	@Override
	public void setDescription(String text) {
		myGoalDescription =  text;
	}

	/**
	 * Enables the user to see the goals as editor goals
	 * @ Observable List of the goals
	 */
	@Override
	public ObservableList<Goal> getChildren() {
		return myChildren;
	}

	/**
	 * Enables the user to see the goals as play goals
	 * @ Observable List of the goals
	 */
	@Override
	public ObservableList<PlayGoal> getPlayGoalList(){
		return new ObservableList<PlayGoal>(){{
			addAll(myChildren);
		}};
	}

	/**
	 * @return current Goal Conditions
	 */
	@Override
	public List<NewCondition> getConditions(){
		return conditionStateMap.getAllItems();
	}
	
	@Override
	public Iterator<NewCondition> iterator() {
		return conditionStateMap.getAllItems().iterator();
	}

	@Override
	public void setState(State conditionState) {
		this.pastState = this.currentState;
		this.currentState = conditionState;
	}

	@Override
	public State getCurrentState() {
		return this.currentState;
	}

	@Override
	public State getPastState() {
		return this.pastState;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof NewCondition){
			NewCondition tempNewCondition = (NewCondition) o;
			boolean passConditionState = true;
			boolean failConditionState = false;
			this.conditionStateMap.moveItem(tempNewCondition.getCurrentState(), tempNewCondition);
			for(State state : conditionStateMap){
				passConditionState = passConditionState && state.neededToPass();
				failConditionState = state.neededToFail();
			}
			if(passConditionState){
				setState(new Passed());}
			else if(failConditionState){
				setState(new Failed());}
		}
	}

	@Override
	public <T extends State, U extends StateAPI> void updateTarget(T state, U stateObj) {
		//TODO:
	}

}