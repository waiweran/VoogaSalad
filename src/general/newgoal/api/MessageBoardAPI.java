package general.newgoal.api;

import general.newgoal.messages.Message;

import java.util.List;

public interface MessageBoardAPI<T extends Message>{

	/**
	 * Returns a list of all the actions in the message board
	 * @return List of messages
     */
	public List<T> getMessages();

	/**
	 * Adds an action message to the actions queue
	 */
	public void add(T message);

	/**
	 * Clears the actions board
	 */
	public void clearBoard();

}
