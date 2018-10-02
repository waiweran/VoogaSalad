package editor.controllers.worldControllers;

import java.util.HashMap;
import java.util.Map;

import editor.externalAPI.EditorAttribute;
import editor.externalAPI.EditorEntity;
import editor.externalAPI.EditorModel;
import editor.view.worldEditor.sideMenuItems.AttributeView;
import editor.view.worldEditor.sideMenuItems.AttributesAccordion;
import editor.view.worldEditor.sideMenuItems.fields.FieldViewFactory;
import general.ResourceLoader;
import general.exceptions.ImproperFileException;
import general.fields.Field;
import general.storage.ObservableList;
import general.view.ExceptionHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Controls the Attributes view pane.
 * Creates the attributes displayed in the pane.
 * @author Nathaniel Brooke
 * @version 04-09-2017
 */
public class AttributesController {

	private static final int MAX_FIELDS = 4;
			
	private EditorModel model;
	private AttributesAccordion view;
	private ObservableList<AttributeView> viewList;
	private Map<EditorAttribute<?>, HBox> attributes;
	
	/**
	 * Initializes the Attributes controller and view.
	 */
	public AttributesController(EditorModel gameModel) {
		model = gameModel;
		viewList = new ObservableList<>();
		for(int i = 0; i < Integer.parseInt(ResourceLoader.ATTRIBUTE_NAMES.getString("NumCategories")); i++) {
			viewList.add(new AttributeView());
		}
		view = new AttributesAccordion(viewList);
		attributes = new HashMap<>();
	}
	
	/**
	 * @return the AttributeView that this controller controls.
	 */
	public AttributesAccordion getAttributeView() {
		return view;
	}

	/**
	 * Displays the Attributes of the given entity in the view.
	 * @param selected the entity whose attributes are displayed.
	 */
	public void showAttributes(EditorEntity selected) {
		try {
			FieldViewFactory factory = new FieldViewFactory();
			for(AttributeView view : viewList) {
				view.clear();
			}
			viewList.get(0).addAttribute(new HBox(makeSave(selected)));
			int count = 0;
			int viewNum = 0;
			AttributeView view = viewList.get(viewNum);
			for(EditorAttribute<?> a : selected.getViewableAttributes()) {
				if(Integer.parseInt(ResourceLoader.ATTRIBUTE_NAMES.getString("Num_" + viewNum)) <= count) {
					count = 0;
					view = viewList.get(++viewNum);
				}
				HBox attributeView = new HBox(8);
				attributes.put(a, attributeView);
				view.addAttribute(makeAttributeView(factory, a));
				a.addObserver((o, arg) -> {
					if(arg != null) showAttributes(selected);
				});
				count++;
			}
		} catch(Exception e) {
			throw new RuntimeException("Resource Files defining Attributes are incorrect", e);
		}

	}
	
	/**
	 * Hides the Attributes pane
	 */
	public void hideAttributes() {
		viewList.stream().forEach(attributeView -> attributeView.clear());
	}
	
	/**
	 * Makes an attribute view.
	 * @param factory the FieldViewFactory to make fields.
	 * @param a the Attribute to display.
	 * @return view of the attribute.
	 */
	private HBox makeAttributeView(FieldViewFactory factory, EditorAttribute<?> a) {
		HBox attributeView = attributes.get(a);
		attributeView.getChildren().clear();
		attributeView.setAlignment(Pos.CENTER_LEFT);
		attributeView.getChildren().add(new Label(ResourceLoader.ATTRIBUTE_NAMES.getString(a.getName())));
		for(Field<?> f : a) {
			attributeView.getChildren().add(factory.makeFieldView(f));
		}
		if(attributeView.getChildren().size() > MAX_FIELDS) {
			makePopup(a, factory, attributeView);
		}
		return attributeView;
	}

	/**
	 * Turns an Attribute view into a button that opens a pop-up.
	 * @param attributeName the name of the attribute.
	 * @param attributeView the Attribute view to transform.
	 */
	private void makePopup(EditorAttribute<?> a, FieldViewFactory factory, HBox attributeView) {
		VBox options = new VBox(8);
		for(Field<?> f : a) {
			HBox field = new HBox(10);
			field.getChildren().add(new Text(f.getName()));
			field.getChildren().add(factory.makeFieldView(f));
			options.getChildren().add(field);
		}
		attributeView.getChildren().clear();
		Button button = new Button("Options");
		button.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setGraphic(options);
			alert.setTitle(a.getName());
			alert.show();
		});
		attributeView.getChildren().addAll(new Text(a.getName()), button);
	}
	
	/**
	 * Generates the title of the Attributes Pane
	 * @param selected the EditorEntity whose attributes are being displayed.
	 * @return the title pane.
	 */
	private Button makeSave(EditorEntity selected) {
		Button save = new Button(ResourceLoader.ATTRIBUTE_NAMES.getString("SavePresets"));
		save.setOnAction(e -> {
			try {
				model.savePreset(selected.getId());
			} catch (ImproperFileException e1) {
				new ExceptionHandler().showAlert(e1);
			}
		});
		return save;
	}

}
