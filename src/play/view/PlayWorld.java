package play.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import general.Id;
import general.Vector;
import general.view.ScreenObject;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Displays the game world during game play.
 * @author Harry Liu
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class PlayWorld implements ScreenObject{

	private Map<Id, ImageView> entities;
	private Pane playerView;
	private List<ImageView> backgrounds;

	/**
	 * Initializes the world view and entities within it.
	 */
	public PlayWorld() {
		entities = new HashMap<Id, ImageView>();
		playerView = new Pane();
		backgrounds = new ArrayList<>();
	}

	/**
	 * Removes the given entity from the screen.
	 * @param id the ID of the entity to remove.
	 */
	public void remove(Id id) {
		playerView.getChildren().remove(entities.remove(id));
	}

	/**
	 * Adds an entity's view to the screen.
	 * @param id the ID of the entity added.
	 * @param newEntity the view of the entity.
	 */
	public void add(Id id, ImageView newEntity) {
		if (!entities.containsKey(id)) {
			entities.put(id, newEntity);
			playerView.getChildren().add(newEntity);
		}
	}
	
	/**
	 * Loads the background into the world.
	 */
	public void loadBackgrounds(List<ImageView> bkgds) {
		for(ImageView bg : bkgds) {
			playerView.getChildren().add(backgrounds.size(), bg);
			backgrounds.add(bg);
		}
	}
	
	public void moveBackgrounds(Vector offset) {
		offset = offset.flip();
		for(ImageView bg : backgrounds) {
			bg.setTranslateX(offset.getX());
			bg.setTranslateY(offset.getY());
		}
	}
	
	public void setStartingShift(Vector offset) {
		for(ImageView bg : backgrounds) {
			bg.setX(bg.getX() + offset.getX());
			bg.setY(bg.getY() + offset.getY());
		}
	}

	@Override
	public Pane getView() {
		return playerView;
	}

}