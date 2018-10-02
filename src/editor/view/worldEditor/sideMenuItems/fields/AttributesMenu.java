package editor.view.worldEditor.sideMenuItems.fields;
import editor.controllers.worldControllers.AttributesController;
import general.ResourceLoader;
import general.view.ScreenObject;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AttributesMenu implements ScreenObject {
	
	AttributesController attributesC;

	public AttributesMenu(AttributesController attributeControl){
		attributesC = attributeControl;
	}
	
	@Override
	public Region getView() {
		return createAttributesMenu();
	}

	private VBox createAttributesMenu(){
		VBox attributesMenu = new VBox();
		Text text = new Text(new ResourceLoader().getDisplayResources().getString("Attributes"));
		text.setId("AttributesHeader");
		attributesMenu.setId("AttributesMenu");
		Region aview = attributesC.getAttributeView().getView();
		aview.setId("AttributesMenu");
		attributesMenu.getChildren().addAll(text, aview);
		return attributesMenu;
	}
	
}
