package play.model.messageboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class GoalMessenger extends Observable implements MessageBoard{
	
	List<ActionMessage> messages;
	
	public GoalMessenger(){
		messages = new ArrayList<>();
	}

	@Override
	public List<ActionMessage> getActionMessages() {
		List<ActionMessage> placeholder = new ArrayList<>(messages);
		messages.clear();
		return placeholder;
	}

	@Override
	public void add(ActionMessage message) {
		messages.add(message);
	}

}
