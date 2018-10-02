package play.view.Controllas;

import java.util.function.Consumer;

import general.Vector;
import general.attributes.Heading;
import general.attributes.Location;
import general.attributes.Size;
import general.attributes.UIDataAttribute;
import general.attributes.Visibility;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import play.externalAPI.PlayEntity;

/**
 * Class that updates the player view by loading images and updates entities on the screen.
 * @author Harry Liu
 *
 */
public class PlayerView {

	private PlayEntity playerEntity;
	private Image myView;
	private ImageView playerView;
	private Consumer<PlayerView> moveFunction;
	
	public PlayerView(PlayEntity playEntity){
		playerEntity = playEntity;
		playerView = new ImageView();
		moveFunction = (e -> e.updatePosition(Vector.ORIGIN));
		loadImage();
		autoUpdate();	
	}
	
	/**
	 * @return ImageView representing the entity in the game world.  Can only be displayed once.
	 */
	public ImageView getImageView() {
		loadImage();
		makePlayerView();
		return playerView;
	}
	
	private void loadImage() {
		String img = playerEntity.getAttribute("UIDataAttribute", UIDataAttribute.class).getValue().getImageLocation();
		myView = new Image(img); 
		if(playerEntity.getAttribute("Visibility", Visibility.class).getValue()) {
			playerView.setImage(myView);
		}
	}
	
	private void autoUpdate() {
		playerEntity.getAttribute("Location", Location.class).addObserver((o, arg) -> makePlayerView());
		playerEntity.getAttribute("Heading", Heading.class).addObserver((o, arg) -> makePlayerView());
		playerEntity.getAttribute("UIDataAttribute", UIDataAttribute.class).addObserver((o, arg) -> loadImage());
	}
	
	private void makePlayerView() {
		double scale = playerEntity.getAttribute("Size", Size.class).getValue();
		//double angle = playerEntity.getAttribute("Heading", Heading.class).getValue();
		//playerView.setRotate(angle);
		playerView.setFitHeight(scale);
		playerView.setFitWidth(scale);
		moveFunction.accept(this);
	}
	
	public void updatePosition(Vector offset) {
		offset = offset.flip();
		double scale = playerEntity.getAttribute("Size", Size.class).getValue();
		Vector location = playerEntity.getAttribute("Location", Location.class).getValue().flip();
		playerView.setX(location.getX() - scale/2 + offset.getX());
		playerView.setY(location.getY() - scale/2 + offset.getY());
	}
	
	public void setMoveFunction(Consumer<PlayerView> newMove) {
		moveFunction = newMove;
	}
}
