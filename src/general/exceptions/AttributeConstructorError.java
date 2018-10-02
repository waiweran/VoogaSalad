package general.exceptions;

public class AttributeConstructorError extends Exception implements VoogaException {

	private static final long serialVersionUID = 1L;

	@Override
	public String message() {
		return "Constructor for given attribute not found.";
	}

}
