package general.fields;

import java.util.List;

import general.exceptions.IncorrectFieldException;

public class ImageSelectionField extends Field<String> {
	private List<String> images;

	public ImageSelectionField(String name, String defaultValue, List<String> imagesIn) {
		super(name, defaultValue);
		images = imagesIn;
	}

	@Override
	protected String getValueFromString(String newValue) throws IncorrectFieldException {
		return newValue;
	}
	
	public List<String> getOptions() throws IncorrectFieldException{
		return images;
	}

}
