package editor.view.worldEditor.entityDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import editor.externalAPI.EditorAttribute;
import editor.externalAPI.EditorEntity;
import general.ResourceLoader;
import general.Vector;
import general.attributes.Heading;
import general.attributes.Location;
import general.attributes.Size;
import general.attributes.Tag;
import general.attributes.UIDataAttribute;
import general.attributes.ZoneAttribute;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import play.model.collisionmanager.Zone;

/**
 * Displays a single entity on screen.
 * Each EntityView must only be displayed once.
 * @author Nathaniel Brooke
 * @version 04-07-2017
 *
 */
public class EntityView {
		
	private EditorEntity myEntity;
	private Image myView;
	private ImageView worldView;
	private List<CircleZoneView> zoneViews;

	/**
	 * Initializes the entity view with the correct image.
	 * @param entity the entity to display
	 */
	public EntityView(EditorEntity entity) {
		myEntity = entity;
		worldView = new ImageView();
		zoneViews = new ArrayList<>();
		loadImage();
		makeZones();
		autoUpdate();
	}

	/**
	 * @return Pane representing the entity in the side menu.
	 */
	public Pane getMenuView() {
		ImageView img = new ImageView(myView);
		img.setFitHeight(Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("EntitySizeMenu")));
		img.setFitWidth(Integer.parseInt(ResourceLoader.EDITOR_GRAPHICS.getString("EntitySizeMenu")));
		Text name = new Text(myEntity.getAttribute("Tag", Tag.class).getValue().getName());
		VBox box = new VBox(5);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(img, name);
		return box;
	}
	
	/**
	 * @return ImageView representing the entity in the game world.  Can only be displayed once.
	 */
	public ImageView getWorldView() {
		loadImage();
		makeWorldView();
		return worldView;
	}
	
	/**
	 * @return Shapes representing the zones that this Entity has.
	 */
	public List<CircleZoneView> getZoneViews() {
		return zoneViews;
	}
	
	/**
	 * Sets what happens to this EntityView when clicked.
	 * @param onClick Function that runs on click.
	 */
	public void setOnMouseClick(Consumer<EditorEntity> onClick) {
		worldView.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			onClick.accept(myEntity);
			e.consume();
		});
	}
	
	/**
	 * Sets what happens to this EntityView when dragged.
	 * @param onPress When the drag is initiated with a button press.
	 * @param onDrag When the mouse is dragged with the button down.
	 * @param onRelease When the mouse is released.
	 */
	public void setOnDrag(BiConsumer<EntityView, MouseEvent> onPress,
			BiConsumer<EntityView, MouseEvent> onDrag,
			BiConsumer<EntityView, MouseEvent> onRelease) {
		worldView.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
			onPress.accept(this, e);
			e.consume();
		});
		worldView.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
			onDrag.accept(this, e);
			e.consume();
		});
		worldView.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
			onRelease.accept(this, e);
			e.consume();
		});
	}
	
	/**
	 * Loads the image to display this Entity
	 */
	private void loadImage() {
		String imgLoc = myEntity.getAttribute("UIDataAttribute", UIDataAttribute.class).getValue().getImageLocation();
		myView = new Image(imgLoc); 
		worldView.setImage(myView);
	}
	
	/**
	 * Generates the ImageView to display in the world.
	 */
	private void makeWorldView() {
		double scale = myEntity.getAttribute("Size", Size.class).getValue();
		if(scale <= 0) worldView.setVisible(false);
		else{
		Vector location = myEntity.getAttribute("Location", Location.class).getValue().flip();
		double angle = myEntity.getAttribute("Heading", Heading.class).getValue();
		worldView.setX(location.getX() - scale/2);
		worldView.setY(location.getY() - scale/2);
		worldView.setRotate(angle);
		worldView.setFitHeight(scale);
		worldView.setFitWidth(scale);
		worldView.setVisible(true);
		}
	}

	/**
	 * Sets automatic update of properties by observing the entity this displays.
	 */
	private void autoUpdate() {
		myEntity.getAttribute("Location", Location.class).addObserver((o, arg) -> {
			makeWorldView();
			updateZones();
		});
		myEntity.getAttribute("Heading", Heading.class).addObserver((o, arg) -> makeWorldView());
		myEntity.getAttribute("UIDataAttribute", UIDataAttribute.class).addObserver((o, arg) -> loadImage());
		myEntity.getAttribute("Size", Size.class).addObserver((o, arg) -> makeWorldView());

	}
	
	/**
	 * Generates zone views for displaying on screen.
	 */
	private void makeZones() {
		for(EditorAttribute<?> a : myEntity.getViewableAttributes()) {
			if(a.getValue() instanceof Zone) {
				EditorAttribute<Zone> zoneAttr = myEntity.getAttribute(a.getName(), ZoneAttribute.class);
				zoneViews.add(new CircleZoneView(zoneAttr));
			}
		}
	}
	
	/**
	 * Updates the zone views on screen.
	 */
	private void updateZones() {
		Vector location = myEntity.getAttribute("Location", Location.class).getValue().flip();
		for(CircleZoneView v : zoneViews) {
			v.setLocation(location);
		}
	}
	
	/**
	 * @return EditorEntity that this view represents.
	 */
	public EditorEntity getEntity() {
		return myEntity;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof EntityView 
				&& myEntity.equals(((EntityView) other).myEntity);
	}

}
