package general.exceptions;

/**
 * Exception to use if game file cannot be correctly read.
 * @author Nathaniel
 *
 */
public class ImproperFileException extends Exception {

	private static final long serialVersionUID = 1L;

	public ImproperFileException(String message) {
		super(message);
	}

	public ImproperFileException(Throwable cause) {
		super(cause);
	}

	public ImproperFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
