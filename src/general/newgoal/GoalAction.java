package general.newgoal;

/**
 * @author Justin
 * @author Ashka
 * @version 4/28/2017.
 */
public class GoalAction {

    private String actionName;
    private String customMessage;

    public GoalAction(String actionName){
        this.changeName(actionName);
    }

    public void changeName(String actionName){
        this.actionName = actionName;
    }
    
    public void setCustomMessage(String s){
    	customMessage = s;
    }
    
    public String getCustomMessage(){
    	return customMessage;
    }
    
    
    
    
    

    //TODO: Composition of custom message

    //Use composition to define the goal action
    //ie kill = given game entity type, target health attribute = 0
    
    
    
    
}
