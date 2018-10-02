package general.actions.strategies;

import general.attributes.Items;
import general.attributes.KeyBindings;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.keypress.ButtonList;
import general.keypress.KeyCommand;

public class PickUp extends CollisionStrategy {

	public PickUp(int waitPeriod) {
		super(waitPeriod);
	}
	
	@Override
	public void enterCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		System.out.println(recipient.getAttribute("KeyBindings", KeyBindings.class).getValue());
		if(activeKeys.containsCommand(KeyCommand.INTERACT, recipient)){
			System.out.println("hit");
			recipient.getAttribute("Items", Items.class).getValue().add(actor);
		}
	}

	@Override
	public void stayCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		enterCollision(actor, recipient, activeKeys, spawner, timeDiff);
	}

	@Override
	public void exitCollision(ReadOnlyEntity actor, GameEntity recipient, ButtonList activeKeys, Spawner spawner, double timeDiff) {
		
	}

}
