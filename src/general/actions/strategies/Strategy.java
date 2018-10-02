package general.actions.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.entities.Spawner;
import general.fields.selectionfields.Selectable;
import general.keypress.ButtonList;

/**
 * This class encapsulates Action Strategies that will run once each
 * step of the model.
 * 
 * @author DhruvKPatel
 * @author Alex Boss
 * @author Advait Reddy
 * @author Andres Lebbos
 */
public abstract class Strategy implements Selectable {
	
	private static final String RESOURCES = "resources/Strategy";
	private final static String ERRORS_RESOURCE = "resources/ErrorMessages";

	private double waitPeriod, timeSinceLastAction;
	
	private int uses;
	
	private Map<String, String> myStringParameters;
	private Map<String, Number> myNumericalParameters;
	
	private static final ResourceBundle resources = ResourceBundle.getBundle(RESOURCES);
	private static final ResourceBundle errors = ResourceBundle.getBundle(ERRORS_RESOURCE);
	
	/**
	 * Constructs a strategy with a certain wait period
	 * 
	 * Note: if "runsEveryTime" is set to "True", the frequency will be ignored (you can set it to anything)
	 * @param runsEveryTime
	 * @param frequency
	 */
	public Strategy(double waitPeriod, int amountUses){
		this.waitPeriod = waitPeriod;
		this.timeSinceLastAction = 0;
		this.uses = amountUses;
		myStringParameters = initializeStringParameters();
		myNumericalParameters = initializeNumericalParameters();
//		System.out.println("Creating new strategy: " + getStringRepresentation());
	}
	
	public Strategy(double waitPeriod) {
		this(waitPeriod, -1);
	}
	
	protected boolean waitPeriodIsOver(double timeDifference){
		timeSinceLastAction += timeDifference;
		if (timeSinceLastAction > waitPeriod && uses != 0){
			timeSinceLastAction = 0;
			uses--;
			return true;
		}
		return false;
	}
	
	/**
	 * This returns a consumer of the action to be done on the GameEntity.
	 * Note: this will be run according to the specific frequency of the strategy.
	 * @param keys
	 * @param spawner
	 * @param timeDifference
	 * @param entity
	 */
	protected abstract Consumer<GameEntity> getEvent(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference);

	public Consumer<GameEntity> getAction(List<ReadOnlyEntity> entities, ButtonList keys, Spawner spawner, double timeDifference) {
		if(waitPeriodIsOver(timeDifference)){
			return getEvent(entities, keys, spawner, timeDifference);
		} 
		return e -> {/*do nothing*/};	
	}
	
	protected ResourceBundle getErrorsResource(){
		return errors;
	}
	
	public String getStringRepresentation(){
		return resources.getString(this.getClass().getSimpleName());
	}
	
	/**
	 * @author Alex
	 * @return A map of parameter names to values of numerical parameters for the specific strategy.
	 * i.e., for HurtEntity strategy, an entry in the map might be
	 * "Damage" -> 69
	 * 
	 * This method is non-abstract but most subclasses will end up overriding it
	 */
	public Map<String, Number> getNumericalParameters(){
		return myNumericalParameters;
	}
	
	/**
	 * @author Alex
	 * @return A map of parameter names to values of string parameters for the specific strategy.
	 * i.e., for ChaseEntity strategy, an entry in the map might be
	 * "TagIChase" -> "Player" 
	 * 
	 * This method is non-abstract but most subclasses will end up Overriding it
	 */ 
	public Map<String, String> getStringParameters(){
//		System.out.println("Getting string parameters for " + getStringRepresentation() + " :" + myStringParameters);
		return myStringParameters;
	}
	
	/**
	 * 
	 * @param numericalParams The new numerical parameters and values you wish to set
	 * Override if the specific strategy actually has numerical parameters
	 */
	public void setNumericalParameters(Map<String, Number> numericalParams){
		myNumericalParameters = numericalParams;
	}
	
	/**
	 * 
	 * @param stringParams The new String parameters and values you wish to set
	 * Override if the specific strategy actually has string parameters
	 */
	public void setStringParameters(Map<String, String> stringParams){
//		System.out.println("Setting string parameters for " + getStringRepresentation() + " :" + stringParams);
		myStringParameters = stringParams;
	}
	
	public Map<String, String> initializeStringParameters(){
		return new HashMap<>();
	}
	
	public Map<String, Number> initializeNumericalParameters(){
		return new HashMap<>();
	}
	
//	public void reInitialize(){
//		myStringParameters = initializeStringParameters();
//		myNumericalParameters = initializeNumericalParameters();
//	}
	
	

		
}