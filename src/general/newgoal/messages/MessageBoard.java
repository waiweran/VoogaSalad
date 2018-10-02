package general.newgoal.messages;

import general.newgoal.Goal;
import general.newgoal.api.MessageBoardAPI;
import general.newgoal.conditions.NewCondition;
import general.newgoal.messages.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Justin
 * @author Ashka
 * @version 4/27/2017.
 */
public class MessageBoard<T extends Message> extends Observable implements MessageBoardAPI<T>, Observer {

    List<T> messages;
    HashMap<Class, ArrayList<Message>> messageMap;

    public MessageBoard(){
        this.messages = new ArrayList<>();
    }

    @Override
    public List<T> getMessages() {
        List<T> placeholder = new ArrayList<>(this.messages);
        this.clearBoard();
        return placeholder;
    }

    @Override
    public void add(T message) {
    	message.addObserver(this);
        this.messages.add(message);
    }

    @Override
    public void clearBoard() {
        this.messages.clear();
    }

    public void addActiveGoal(Goal activeGoal){
    	activeGoal.addObserver(this);
    }
    
    public T getMessageAt(int i){
		return messages.get(i);
    }
    
    public HashMap<Class, ArrayList<Message>> getMessageMap(){
		return messageMap;
    }
    
    
    public void addtoMap(Message message){
    	if(messageMap.containsKey(message.getClass())){
    		ArrayList<Message> li = messageMap.get(message.getClass());
    		li.add(message);
    		messageMap.replace(message.getClass(),li );
    	}
    	else{
    		messageMap.put(message.getClass(), new ArrayList<Message>());
    	}
    }

	@Override
	public void update(Observable o, Object arg) {
	}
    
}