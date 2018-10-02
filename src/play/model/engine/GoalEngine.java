package play.model.engine;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import general.entities.EntityType;
import general.keypress.ButtonList;
import general.newgoal.Goal;
import general.newgoal.conditions.NewCondition;
import general.newgoal.messages.Message;
import general.newgoal.messages.MessageBoard;
import general.newgoal.messages.types.ActionMessage;
import general.newgoal.messages.types.AttributeMessage;
import general.newgoal.messages.types.IntangibleMessage;
import general.newgoal.states.substates.Incomplete;
import general.storage.GameState;
/**
 * @author Justin
 * @author Ashka
 */
public class GoalEngine extends Engine{

    private MessageBoard<ActionMessage> actionMessageBoard;
    private MessageBoard<AttributeMessage> attributeMessageBoard;
    private MessageBoard<IntangibleMessage> intangibleMessageBoard;

    @Override
    public void step(GameState state, ButtonList activeKeys, double deltaTime) {
    	//list of all active goals
       	ArrayList<Goal> active = getActiveGoals(state.getRootGoal());
    	
       	for (Goal eachGoal : state.getRootGoal().getChildren()){
       		if (!eachGoal.isActive() && eachGoal.getCurrentState() instanceof Incomplete){
       			eachGoal.setActive(true);
       			for (NewCondition eachCondition : eachGoal.getConditions()){
        			setChanged();
        			notifyObservers(setActiveGoals(state, eachCondition.getTargetMessage()));
        			
        		}
       		}
       	}
       	
       	
        //Given a targetMessage of type AttributeMessage;
//        use message board for all checks?
//
//        set active goals (sets active conditions to check against)
//
//        go through all subconditions with non-action messages
//                use target to filter out all entities of that type
//                filter out attribute from that entity
//                create a new message to check against the target (via execute)
    }
    
	private ArrayList<Goal> getActiveGoals(Goal rootGoal) {
		ArrayList<Goal> myGoal = new ArrayList<Goal>();
		for (Goal eachGoal : rootGoal.getChildren()){
			if(eachGoal.isActive()){
				myGoal.add(eachGoal);
			}
		}
		return myGoal;
	}

	//filer out attribute messages
    public List setActiveGoals(GameState state, Message targetMessage){
        if(targetMessage instanceof AttributeMessage) {
            state.getEntities().values().stream()
                    .filter(entity -> {
                        return entity.getAttribute("EntityType", EntityType.class).equals(((ActionMessage) targetMessage).getActorType());
                    })
                    .collect(Collectors.toList());
        }
		return null;
    }

}