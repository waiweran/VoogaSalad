package play.model.engine;

import java.util.List;

import general.actions.strategies.Strategy;
import general.attributes.ActivationAction;
import general.attributes.Items;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.items.ItemHolder;
import general.keypress.ButtonList;
import general.keypress.KeyCommand;
import general.storage.GameState;

public class ItemEngine extends Engine {

	@Override
	public void step(GameState state, ButtonList activeKeys, double deltaTime) {
		for (GameEntity entity : state){
			if (entity.containsAttribute("Items")){
				ItemHolder itemHolder = entity.getAttribute("Items", Items.class).getValue();
//				System.out.println(itemHolder.getInventorySize());
				checkForItemSwitch(activeKeys, entity, itemHolder);
				removeItemsFromGameState(itemHolder.getItems(), state);
				checkAndRunActiveItem(state, activeKeys, deltaTime, entity, itemHolder);
			}
		}
		
	}

	private void checkAndRunActiveItem(GameState state, ButtonList activeKeys, double deltaTime, GameEntity entity,
			ItemHolder itemHolder) {
		if (activeKeys.containsCommand(KeyCommand.USE_ITEM, entity)){
			if(itemHolder.hasItems()){
				Strategy activationStrategy = itemHolder.getActiveItem().getReadOnlyAttribute("Activation", ActivationAction.class).getValue();
				entity.run(activationStrategy.getAction(state.getReadOnlyEntityValues(), activeKeys, new Spawner(state, false), deltaTime));
			}
		}

	}

	private void checkForItemSwitch(ButtonList activeKeys, GameEntity entity, ItemHolder itemHolder) {
		if (activeKeys.containsCommand(KeyCommand.SWITCH, entity)){
			itemHolder.switchItem();
		}
	}
	
	private void removeItemsFromGameState(List<ReadOnlyEntity> items, GameState state){
		for (ReadOnlyEntity item : items){
			if (state.getEntities().containsKey(item.getId())){
//				System.out.println("go");
				state.getEntities().remove(item.getId());
			}
		}
	}

}
