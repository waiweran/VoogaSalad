package server;

public class IncompleteClassException extends SessionException {

	private static final long serialVersionUID = 1L;

	public IncompleteClassException(String field, String message) {
		super("The " + field + " in class " + message + " was not found.");
	}

}
