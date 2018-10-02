package general.actions.strategies.collision.trigger;
//package general.actions.strategies.trigger;
//
//import java.util.List;
//import java.util.function.Consumer;
//
//import general.actions.strategies.Strategy;
//import general.entities.GameEntity;
//import general.keypress.ButtonList;
//
///**
// * Shoots objects that followed the entity that triggered the effect- TriggerStrategy
// * @author AndresLebbos
// */
//public class TowerShooter extends Strategy{
//	
//	public TowerShooter(List<String> tags, int frequency) {
//		super(tags, frequency);
//	}
//
//	@Override
//	public Consumer<GameEntity> doToEntity(GameEntity entity, List<Character> activeKeys, double totalTime) {
//		return victim -> shootObject(victim, entity);
//	}
//
//	private GameEntity shootObject(GameEntity victim, GameEntity towards) {
//		// TODO create new objects moving towards towards and add to board!!!
//		return victim;
//	}
//
//	@Override
//	public Consumer<GameEntity> getAction(GameEntity entity, ButtonList keys, double timeDifference) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
