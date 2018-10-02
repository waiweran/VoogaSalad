package server;

public class PropertiesInfoException extends SessionException {

	private static final long serialVersionUID = 1L;

	public PropertiesInfoException(String name) {
		super("Couldn't find the key " + name + " in properties file");
	}
}
