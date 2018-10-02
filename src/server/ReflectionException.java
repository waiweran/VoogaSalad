package server;

public class ReflectionException extends SessionException {
	private static final long serialVersionUID = 1L;

    public ReflectionException() {
        super("Error using reflection.");
    }
}
