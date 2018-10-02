package general.actions.strategies.movement;

import general.Vector;

public abstract class LinearStrategy extends MovementStrategy {

	public LinearStrategy(int waitPeriod) {
		super(waitPeriod);
	}

	protected boolean atPoint(Vector towards, Vector victim) {
		return (int) towards.getX() == (int) victim.getX() && (int) towards.getY() == (int) victim.getY();
	}
}
