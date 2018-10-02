package play.view;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {

	private final Image image;
	private final int size;
	private final double offsetX;
	private final double offsetY;
	private final double width;
	private final double height;

	public Sprite(Image image,
			int size,
			double offsetX,
			double offsetY,
			double width,
			double height){
		this.image = image;
		this.size = size;
		this.offsetX   = offsetX;
		this.offsetY   = offsetY;
		this.width     = width;
		this.height    = height;
	}

	private Button createSpriteButton(){
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(size);
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		Button button = new Button();
		button.setGraphic(imageView);
		return button;
	}
	
	public Button getButton(){
		return createSpriteButton();
	}
}
