package general.actions.strategies.collision;

public enum CollisionStatus {
	
	ENTER	(1),
	STAY 	(0),
	EXIT	(-1),
	NONE	(10);
	
	private final int indicator;
	
	private CollisionStatus(int indicator) {
		this.indicator = indicator;
	}
	
	public int indicator() {
		return indicator;
	}

}
