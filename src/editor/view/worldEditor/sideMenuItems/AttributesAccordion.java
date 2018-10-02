package editor.view.worldEditor.sideMenuItems;

import java.util.ArrayList;
import java.util.List;

import general.ResourceLoader;
import general.storage.ObservableList;
import javafx.scene.control.TitledPane;

/**
 * An Accordion menu of Entities.
 * @author Harry Liu
 * @version 04-27-2017
 */
public class AttributesAccordion extends AccordionView<AttributeView> {

	public AttributesAccordion(ObservableList<AttributeView> attributeList){
		super(attributeList);
	}

	@Override
	protected List<TitledPane> makeMenus(List<AttributeView> attributeViews) {
		List<TitledPane> out = new ArrayList<>();
		for(int i = 0; i < Integer.parseInt(ResourceLoader.ATTRIBUTE_NAMES.getString("NumCategories")); i++) {
			AttributeView atv = attributeViews.get(i);
			out.add(new TitledPane(ResourceLoader.ATTRIBUTE_NAMES.getString("Name_" + i), atv.getView()));
		}
		return out;
	}

}
