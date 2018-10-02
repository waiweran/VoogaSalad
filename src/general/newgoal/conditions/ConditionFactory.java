package general.newgoal.conditions;


import general.actions.strategies.Strategy;
import general.attributes.ReadOnlyAttribute;
import general.entities.EntityType;
import general.newgoal.conditions.util.Comparison;
import general.newgoal.messages.MessageFactory;
import general.newgoal.states.State;
/**
 * @author Ashka, Justin
 * @version 4/30/17
 */

public class ConditionFactory {
	private MessageFactory messageFactory;

	public ConditionFactory(){
		this.messageFactory = new MessageFactory();
	}

	public NewCondition newCondition(ReadOnlyAttribute targetAttribute, State comparison, State subState, EntityType readOnlyEntity, int xCount){
		return new NewCondition(this.messageFactory.newMessage(readOnlyEntity, targetAttribute), comparison, subState, xCount);
	}

	public NewCondition newCondition(EntityType entityA, EntityType entityB, Strategy targetStrategy, State subState){
		return new NewCondition(this.messageFactory.newMessage(entityA, entityB, targetStrategy), subState, subState);
	}
}