package editor.controllers.goalsControllers;

import java.util.ArrayList;
import java.util.List;

import editor.externalAPI.EditorGoal;
import editor.view.goalsEditor.ConditionChooser;
import general.actions.strategies.DoNothingCollision;
import general.actions.strategies.Strategy;
import general.attributes.Attribute;
import general.attributes.Health;

/**
 * Controls a single goal in the Goal Tree.
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class GoalController {
	
	private EditorGoal myGoal, myParent;
	private ConditionChooser condition;

	/**
	 * Initializes the goal controller.
	 * @param goal the goal this controller controls.
	 * @param parent that goal's parent goal in the goal tree.
	 */
	public GoalController(EditorGoal goal, EditorGoal parent) {
		myGoal = goal;
		myParent = parent;
		condition = new ConditionChooser(this);
	}
	
	/**
	 * Adds a new, blank goal to the tree as a child of this controller's goal.
	 */
	public void addGoal() {
		myGoal.addChild();
	}
	
	/**
	 * Removes the current goal.
	 * Moves this goal's children to its parent.
	 */
	public void remove() {
		myParent.getChildren().remove(myGoal);
	}
	
	/**
	 * Sets the goal's description in the back end.
	 * @param text the goal's description.
	 */
	public void setDescription(String text) {
		myGoal.setDescription(text);
	}
	
	/**
	 * Sets the goal's title in the back end.
	 * @param text the goal's title.
	 */
	public void setTitle(String title) {
		myGoal.setName(title);
	}
	
	/**
	 * Opens the Conditions editor.
	 */
	public void editConditions() {
		condition.showPopup();
	}
	
	public List<Attribute<?>> getAttributes() {
		//myGoal.getAttributeList(); TODO
		List<Attribute<?>> attr = new ArrayList<>();
		attr.add(new Health("Health"));
		return attr;
	}
	
	public List<Strategy> getStrategies() {
		//myGoal.getStrategyList(); TODO
		List<Strategy> strat = new ArrayList<>();
		strat.add(new DoNothingCollision(0));
		return strat;
	}
	
	public void writeConditions() {
		//myGoal.setCondition(c); TODO
	}

}
