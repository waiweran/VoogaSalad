package tests;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import editor.externalAPI.EditorEntity;
import editor.model.EditModel;
import general.Id;
import general.attributes.Tag;
import general.attributes.ZoneAttribute;
import general.entities.EntityType;

public class TestEditModel {

	public static void main(String[] args) throws Exception {
		XStream mySerializer = new XStream(new DomDriver());
		
//		EditModel temp = new EditModel();
//		temp.createDefaultInstance();
//		FileOutputStream fileout = new FileOutputStream(new File("src/games/test.xml"));
//		mySerializer.toXML(temp, fileout);
//		
//		Object o= mySerializer.fromXML(new File("src/presets/Basic.xml"));
//		PresetEntity temp = (PresetEntity) o;
//		AttributeHolder hold = temp.getAttributes();
//		System.out.println(hold.get("Health", Health.class).getValue());

		
//		EditModel editModel = new EditModel();
//		ObservableList<EditorEntity> temp = editModel.getPresets();
//		System.out.println(temp.size());

		EditModel editModel = new EditModel();
		Id myId = editModel.createDefaultInstance();
		EditorEntity entity = editModel.request(myId);
		entity.getAttribute("Tag", Tag.class).setValue(new EntityType("Basic", "Type", "Sub-Type"));
		editModel.savePreset(myId);
		
//		GameEntity test = new GameEntity();
//		PresetEntity test1 = test.getPreset();
//		FileOutputStream fileout = new FileOutputStream(new File("src/presets/Basic.xml"));
//		mySerializer.toXML(test1, fileout);
//		
//		PresetEntity test2 = (PresetEntity) mySerializer.fromXML(new File("src/presets/Basic.xml"));
		
//		MovementAction test = new MovementAction("test");
//		FileOutputStream fileout = new FileOutputStream(new File("src/presets/matest.xml"));
//		mySerializer.toXML(test, fileout);
//		MovementAction test1 = (MovementAction) mySerializer.fromXML(new File("src/presets/matest.xml"));
	}

}
