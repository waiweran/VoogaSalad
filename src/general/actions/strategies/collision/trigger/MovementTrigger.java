package general.actions.strategies.collision.trigger;

public abstract class MovementTrigger extends TriggerStrategy{

	public MovementTrigger(int waitPeriod, int amountUses) {
		super(waitPeriod, amountUses);
	}
	
	public MovementTrigger(int waitPeriod) {
		super(waitPeriod);
	}

}
