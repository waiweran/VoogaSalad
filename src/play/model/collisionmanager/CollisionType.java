package play.model.collisionmanager;

public enum CollisionType {
	
	DIRECT	("Direct"),
	EFFECT	("Effect");
	
	private final String indicator;
	
	private CollisionType(String indicator) {
		this.indicator = indicator;
	}
	
	public String toString() {
		return indicator;
	}

}
