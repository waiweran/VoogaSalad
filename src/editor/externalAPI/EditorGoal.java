package editor.externalAPI;

import general.newgoal.conditions.NewCondition;
import general.storage.ObservableList;

/**
 * Interface for editor front-end to interact with goals in the model.
 */
public interface EditorGoal {
	
	/**
	 * Returns the child EditorGoal nodes of the EditorGoal.
     * This method only returns the nodes contained under the branch/subtree
     * that contains this EditorGoal node as the parent.
	 * @return a list of the EditorGoal's children
	 */
	public ObservableList<? extends EditorGoal> getChildren();
	
	/**
	 * Inserts a new EditorGoal node as a direct child of this EditorGoal.
     * This method does not specify the index of the new EditorGoal
     * node's with respect to its siblings.
	 */
	public void addChild();

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
	 * Sets the given string as the new title of this goal.
	 * @param name the new text of the goal's title
	 */
	public void setName(String name);
	
	/**
	 * Sets the given text as the new description of this goal.
	 * @param text the text to replace the description/objectives
	 */
	public void setDescription(String text);
	
	/**
	 * Sets the given Condition as the condition on which this goal is completed.
     * This Condition specifies a constraint needs to be met to complete this goal.
	 * @param c the the Condition to be set as the objective of the EditorGoal.
	 */
	public void addCondition(NewCondition c);

}
