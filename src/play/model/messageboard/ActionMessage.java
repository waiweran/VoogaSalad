package play.model.messageboard;

import general.actions.strategies.Strategy;
import general.entities.ReadOnlyEntity;

public class ActionMessage {
	
	private ReadOnlyEntity actor;
	private ReadOnlyEntity recipient;
	private Strategy strategy;
	
	public ActionMessage(ReadOnlyEntity actor, ReadOnlyEntity recipient, Strategy strategy){
		this.actor = actor;
		this.recipient = recipient;
		this.strategy = strategy;
	}

	public ReadOnlyEntity getActor() {
		return actor;
	}

	public ReadOnlyEntity getRecipient() {
		return recipient;
	}

	public Strategy getStrategy() {
		return strategy;
	}

}
