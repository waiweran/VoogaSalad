package play.externalAPI;

import general.newgoal.conditions.NewCondition;
import general.storage.ObservableList;

import java.util.List;

/**
 * Interface for player front-end to interact with goals in the model.
 */
public interface PlayGoal {
	
	/**
	 * @return the name of this Goal, for display purposes.
	 */
	public String getName();
	
	/**
	 * Enables the user to see text describing what is required to complete this goal
	 * @return the String description of the goal (i.e. "kill all enemies")
	 */
	public String getDescription();

	/**
	 * Enables the user to see which goals
	 * @ Observable List of the goals
	 */
	public ObservableList<PlayGoal> getPlayGoalList();

	/**
	 * @return current Goal Conditions
	 */
	public List<NewCondition> getConditions();

}
