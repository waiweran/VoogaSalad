//package general.actions.strategies.miscellaneous;
//
//import java.util.List;
//import java.util.function.Consumer;
//
//import general.entities.GameEntity;
//
///**
// * Spawns entities - MiscellaneousStrategy
// * @author AndresLebbos
// */
//public class EntitySpanner extends MiscellaneousStrategy{
//	private GameEntity spannerEntity;
//	
//	public EntitySpanner(List<String> tags, int frequency, GameEntity sEntity) {
//		super(tags, frequency);
//		spannerEntity = sEntity;
//	}
//
//	@Override
//	public Consumer<GameEntity> doToEntity(GameEntity entity, List<Character> activeKeys, double totalTime) {
//		return victim -> span();
//	}
//
//	private GameEntity span() {
//		// TODO create a new object and add it!
//		return spannerEntity;
//	}
//}