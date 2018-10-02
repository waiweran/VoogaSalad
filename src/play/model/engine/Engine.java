package play.model.engine;

import general.keypress.ButtonList;
import general.storage.GameState;

import java.util.Observable;
import java.util.Observer;

public abstract class Engine extends Observable{
	
	public abstract void step(GameState state, ButtonList activeKeys, double deltaTime);

}
