package server;

public class InexistentCommandException extends SessionException {
	private static final long serialVersionUID = 1L;

    public InexistentCommandException(String message) {
        super("The command " + message + " was not found.");
    }
}
