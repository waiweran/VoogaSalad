package editor.view.worldEditor.sideMenuItems;

import java.util.List;
import java.util.stream.Collectors;

import general.storage.ObservableList;
import javafx.scene.control.TitledPane;

/**
 * An Accordion menu of Entities.
 * @author Harry Liu
 * @author Nathaniel Brooke
 * @version 04-27-2017
 */
public class EntitiesAccordion extends AccordionView<EntityList> {
	
	public EntitiesAccordion(ObservableList<EntityList> entityTypes) {
		super(entityTypes);
	}
	
	@Override
	protected List<TitledPane> makeMenus(List<EntityList> entityTypes) {
		
		return entityTypes.stream().map(entity -> new TitledPane(entity.getCategory(), 
				entity.getView())).collect(Collectors.toList());
	}

}


