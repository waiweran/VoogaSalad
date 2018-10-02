package general.actions.strategies.collision.hit;

import general.actions.strategies.CollisionStrategy;

public abstract class HitCollision extends CollisionStrategy{

	public HitCollision(int waitPeriod, int amountUses) {
		super(waitPeriod, amountUses);
	}

	public HitCollision(int waitPeriod) {
		super(waitPeriod);
	}

}
