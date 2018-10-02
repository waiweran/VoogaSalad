package play.view.Controllas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleConsumer;
import java.util.stream.Collectors;

import editor.view.background.BackgroundView;
import general.Vector;
import general.attributes.Location;
import general.attributes.PlayerAttribute;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import play.externalAPI.PlayEntity;
import play.externalAPI.PlayerModel;
import play.model.PlayModel;
import play.view.PlayWindow;
import play.view.PlayWorld;

/**
 * This is the main controller for the PlayWorld. It communicates with the backend to get the entities.
 * @author Harry Liu
 * @author Nathaniel Brooke
 *
 */
public class PlayWorldControlla {

	private static final double DT = 1000/30;

	private Timeline animation;
	private PlayerModel model;
	private PlayWorld pW;
	private List<PlayEntity> displayedEntities;
	private SimpleBooleanProperty status = new SimpleBooleanProperty(false);
	private Location playerPos;
	private Vector center;
	private Vector initialPlayerPos;
	private List<DoubleConsumer> updaters;
	private HeadsUpControlla hud;
	private File savefile;
	private PlayEntity player;

	public PlayWorldControlla(Stage stage, File gameFile){
		savefile = gameFile;
		model = new PlayModel(gameFile);
		center = new Vector(Screen.getPrimary().getVisualBounds().getWidth()/2,
				Screen.getPrimary().getVisualBounds().getHeight()/2);
		updaters = new ArrayList<>();
		pW = new PlayWorld();
		displayedEntities = new ArrayList<>();
		model.getEntities().addObserver((o, arg) -> updateView());
		loadPlayer();
		updateView();
		loadBackgrounds();
		moveToCenter();
		animate();
		hud = new HeadsUpControlla(this);
		new PlayWindow(this, stage);
	}

	private void loadBackgrounds() {
		List<ImageView> backgrounds = model.getBackgrounds().stream().map(bkgd -> {
			BackgroundView view = new BackgroundView(bkgd.getImageURL());
			view.setPosition(bkgd.getPosition());
			System.out.println("Background at " + bkgd.getPosition() + " = " + view.getLocation());
			view.setSize(bkgd.getSize());
			return view.getView();
		}).collect(Collectors.toList());
		pW.loadBackgrounds(backgrounds);
	}
	
	private void loadPlayer() {
		playerPos = getPlayer().getAttribute("Location", Location.class);
		initialPlayerPos = playerPos.getValue();
		PlayerView playerView = new PlayerView(getPlayer());
		playerView.setMoveFunction(e -> {});
		playerView.updatePosition(Vector.ORIGIN);
		pW.add(getPlayer().getId(), playerView.getImageView());
		displayedEntities.add(getPlayer());
	}

	private void updateView() {
		ArrayList<PlayEntity> removed = new ArrayList<>();
		removed.addAll(displayedEntities);
		removed.removeAll(model.getEntities());
		ArrayList<PlayEntity> added = new ArrayList<>();
		added.addAll(model.getEntities());
		added.removeAll(displayedEntities);
		for(PlayEntity a : added) {
			PlayerView playerView = new PlayerView(a);
			playerView.updatePosition(Vector.ORIGIN);
			playerView.setMoveFunction(e -> e.updatePosition(initialPlayerPos.add(playerPos.getValue().negate())));
			pW.add(a.getId(), playerView.getImageView());
		}
		for(PlayEntity r : removed) {
			pW.remove(r.getId());
		}
		displayedEntities.addAll(model.getEntities());
	}
	
	private void moveToCenter() {
		Vector difference = center.add(initialPlayerPos.flip().negate());
		for(Node n : pW.getView().getChildren()) {
			n.setTranslateX(difference.getX());
			n.setTranslateY(difference.getY());
		}
		pW.setStartingShift(difference);
	}

	/**
	 * Will be used to control the complete menu pane rather than the scrollpane.
	 * @param keyCode
	 * @param sp
	 */
	public void handleKeyPress(KeyCode keyPress, Pane menu){
		if (keyPress == KeyCode.M){
			status.set(!status.get());
			menu.setVisible(status.get());
			playPause(!status.get());
		}
		model.onKeyPress(keyPress);
	}

	/**
	 * Will be used to control the complete menu pane rather than the scrollpane.
	 * @param keyCode
	 * @param sp
	 */
	public void handleKeyRelease(KeyCode keyRelease){
		model.onKeyRelease(keyRelease);
	}
	

	public void update(double dt) {
		model.modelStep(dt);
		for(DoubleConsumer update : updaters) {
			update.accept(dt);
		}
		pW.moveBackgrounds(initialPlayerPos.add(playerPos.getValue().negate()));
	}
	
	public void addUpdater(DoubleConsumer updater) {
		updaters.add(updater);
	}
	
	public PlayerModel getModel() {
		return model;
	}
	
	public PlayEntity getPlayer() {
		if(player == null) {
			for(PlayEntity e : model.getEntities()) {
				if(e.getAttribute("Player", PlayerAttribute.class).getValue()) {
					player = e;
					break;
				}
			}
		}
		return player;
	}
	
	private void animate() {
		KeyFrame frame = new KeyFrame(Duration.millis(DT),
				e -> update(Duration.millis(DT).toMillis()));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public void playPause(boolean play) {
		if(play) {
			animation.play();
		}
		else {
			animation.pause();
		}
	}
	
	public HeadsUpControlla getHUDController() {
		return hud;
	}
	
	public PlayWorld getPlayWorld() {
		return pW;
	}
	
	public File getSaveFile() {
		return savefile;
	}
	
	public void setSaveFile(File file) {
		savefile = file;
	}
	
}
