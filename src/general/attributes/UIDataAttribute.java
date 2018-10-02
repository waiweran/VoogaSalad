package general.attributes;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import editor.model.UIData;
import general.entities.EntityType;
import general.fields.Field;
import general.fields.ImageSelectionField;
import general.fields.valuefields.StringValueField;
import general.resourceEngine.FileType;
import general.resourceEngine.MultiEngine;
import general.resourceEngine.ResourceEngine;

public class UIDataAttribute extends Attribute<UIData> {
	private static final String IMAGES_FP = "src/images/";
	private List<String> images;
	
	public UIDataAttribute(String name, UIData data){
		super(name, data);
	}
	
	public UIDataAttribute(String name){
		this(name, new UIData());
	}
	
	public UIDataAttribute(UIDataAttribute old){
		super(old.getName(), new UIData(old.getValue()));
	}

	@Override
	protected List<Field<?>> updateFields(UIData value) {	
		List<Field<?>> fields = new ArrayList<>();
		ResourceEngine temp = new MultiEngine(FileType.IMAGES);
		images = new ArrayList<String>();
		temp.pullFiles(IMAGES_FP);
		for(File f : temp.get(FileType.IMAGES).values()){
			images.add(f.toURI().toString());
		}
		fields.add(new ImageSelectionField("Image File", value.getImageLocation() ,images));
		return fields;
	}

	@Override
	protected UIData updateValue(LinkedHashMap<String, Field<?>> fields) {
		UIData data = new UIData();
		data.setImageLocation(fields.get("Image File").getString());
		return data;
	}

	@Override
	public int compareTo(UIData o) {
		return 0;
	}
}
