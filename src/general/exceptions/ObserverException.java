package general.exceptions;

public class ObserverException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObserverException(String message) {
		super(message);
	}

	public ObserverException(Throwable cause) {
		super(cause);
	}

	public ObserverException(String message, Throwable cause) {
		super(message, cause);
	}

}
