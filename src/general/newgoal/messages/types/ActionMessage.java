package general.newgoal.messages.types;

import general.actions.strategies.Strategy;
import general.entities.EntityType;
import general.newgoal.messages.Message;

import java.util.Observable;

public class ActionMessage extends Message<ActionMessage> {

	private EntityType actorType;
	private EntityType targetType;
	private Strategy strategy;

	public ActionMessage(EntityType actorType, EntityType targetType, Strategy strategy){

		this.actorType = actorType;
		this.targetType = targetType;
		this.strategy = strategy;
	}

	public EntityType getActorType() {
		return this.actorType;
	}

	public EntityType getTargetType() {
		return this.targetType;
	}

	public Strategy getStrategy() {
		return this.strategy;
	}

	@Override
	public int compareTo(ActionMessage o) {
		return 0;
	}

	@Override
	public void update(Observable o, Object arg) {

	}


	@Override
	public boolean equals(Object obj) {

		if(obj instanceof ActionMessage){
			ActionMessage attributeMessage = (ActionMessage) obj;
			if(attributeMessage.getActorType().equals(this.getActorType()) && attributeMessage.getStrategy().getClass().equals(this.getStrategy().getClass())
					&& (attributeMessage.getTargetType().equals(this.getTargetType()))){
				return true;
			}
		}

		return false;

	}

}