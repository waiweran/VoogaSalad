package play.model.messageboard;

import java.util.List;
import java.util.Observer;

public interface MessageBoard extends Observer{
	
	public List<ActionMessage> getActionMessages();
	
	public void add(ActionMessage message);

}
