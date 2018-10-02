package general.exceptions;

public class AttributeClassNotFound extends Exception implements VoogaException  {

	private static final long serialVersionUID = 1L;

	@Override
	public String message() {
		return "Attribute lass does not exist for given input";
	}

}
