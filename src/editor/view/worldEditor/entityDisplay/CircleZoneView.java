package editor.view.worldEditor.entityDisplay;

import editor.externalAPI.EditorAttribute;
import general.ResourceLoader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import play.model.collisionmanager.Zone;

/**
 * Displays a zone as a circle in the editor.
 * @author Nathaniel Brooke
 * @version 04-17-2017
 */
public class CircleZoneView extends ZoneView {
	
	private Circle view;
	
	/**
	 * Initializes a new zone view.
	 * @param a the Zone attribute to view.
	 */
	public CircleZoneView(EditorAttribute<Zone> a) {
		super(a);
		view = new Circle();
		view.setFill(Color.web(ResourceLoader.EDITOR_GRAPHICS.getString("Color" + a.getName()), 0.5));
	}
	
	@Override
	public Shape getView() {
		return view;
	}
	
	@Override
	protected void updateZone() {
		Zone zone = getAttribute().getValue();
		view.setCenterX(getLocation().getX() + zone.getOffset().flip().getX());
		view.setCenterY(getLocation().getY() + zone.getOffset().flip().getY());
		view.setRadius(zone.getRadius());
	}

}
