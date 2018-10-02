package editor.view.background;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import general.Vector;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Displays a Background in the background editor.
 * @author Nathaniel Brooke
 * @version 04-28-2017
 */
public class BackgroundView {
	
	private String imageURL;
	private Vector originalSize;
	private ImageView view;
	private Circle resize;

	/**
	 * Initializes a new BackgroundView
	 * @param image the URL of an image to display for the background.
	 */
	public BackgroundView(String image) {
		imageURL = image;
		view = new ImageView(image);
		setupResizing();
		originalSize = new Vector(view.getBoundsInLocal().getWidth(), 
				view.getBoundsInLocal().getHeight());
		setSize(originalSize);
	}
	
	/**
	 * Sets the size of the background image
	 * @param newSize the new background width and height.
	 */
	public void setSize(Vector newSize) {
		view.setFitWidth(newSize.getX());
		view.setFitHeight(newSize.getY());
		resize.setCenterX(view.getX() + newSize.getX());
		resize.setCenterY(view.getY() + newSize.getY());
	}
	
	/**
	 * Sets the position of the background
	 * @param newPosition the position of the top-right corner.
	 */
	public void setPosition(Vector newPosition) {
		view.setX(newPosition.getX());
		view.setY(newPosition.getY());
		resize.setCenterX(newPosition.getX() + view.getFitWidth());
		resize.setCenterY(newPosition.getY() + view.getFitHeight());
	}
	
	/**
	 * Sets what happens to this BackgroundView when clicked.
	 * @param onClick Function that runs on click.
	 */
	public void setOnClick(Consumer<BackgroundView> onClick) {
		view.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			onClick.accept(this);
			e.consume();
		});
	}
	
	/**
	 * Sets what happens to this BackgroundView when dragged.
	 * @param onPress When the drag is initiated with a button press.
	 * @param onDrag When the mouse is dragged with the button down.
	 * @param onRelease When the mouse is released.
	 */
	public void setOnDrag(BiConsumer<BackgroundView, MouseEvent> onPress,
			BiConsumer<BackgroundView, MouseEvent> onDrag,
			BiConsumer<BackgroundView, MouseEvent> onRelease) {
		view.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
			onPress.accept(this, e);
			e.consume();
		});
		view.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
			onDrag.accept(this, e);
			e.consume();
		});
		view.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
			onRelease.accept(this, e);
			e.consume();
		});
	}	
	
	/**
	 * @return the location of the background view.
	 */
	public Vector getLocation() {
		return new Vector(view.getX(), view.getY());
	}
	
	/**
	 * @return the size of the background view.
	 */
	public Vector getSize() {
		return new Vector(view.getFitWidth(), view.getFitHeight());
	}
	
	/**
	 * @return the original, starting size of the background view.
	 */
	public Vector getOriginalSize() {
		return originalSize;
	}
	
	/**
	 * @return the URL of the displayed image.
	 */
	public String getImageURL() {
		return imageURL;
	}
	
	/**
	 * @return the ImageView representing the background.
	 */
	public ImageView getView() {
		return view;
	}
	
	/**
	 * @return the Circle used to resize the image.
	 */
	public Circle getResizeBall() {
		return resize;
	}
	
	/**
	 * Sets up the code required to resize the image when the circle is dragged.
	 */
	private void setupResizing() {
		resize = new Circle(0, 0, 7);
		resize.setFill(Color.CORNFLOWERBLUE);
		resize.setOnMouseEntered(e -> resize.setFill(Color.DEEPPINK));
		resize.setOnMouseExited(e -> resize.setFill(Color.CORNFLOWERBLUE));
		resize.setOnMouseDragged(e -> setSize(
				new Vector(e.getX() - view.getX(), e.getY() - view.getY())));
	}

}
