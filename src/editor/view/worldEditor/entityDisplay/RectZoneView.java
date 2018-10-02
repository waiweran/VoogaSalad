package editor.view.worldEditor.entityDisplay;

import editor.externalAPI.EditorAttribute;
import general.ResourceLoader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import play.model.collisionmanager.Zone;

public class RectZoneView extends ZoneView {
	
	private Rectangle view;

	public RectZoneView(EditorAttribute<Zone> a) {
		super(a);
		view = new Rectangle();
		view.setFill(Color.web(ResourceLoader.EDITOR_GRAPHICS.getString("Color" + a.getName()), 0.5));
	}

	@Override
	public Shape getView() {
		return view;
	}

	@Override
	protected void updateZone() {


	}

}
