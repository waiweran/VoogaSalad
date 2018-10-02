package server;

public class PropertiesFileException extends SessionException {
	private static final long serialVersionUID = 1L;

	public PropertiesFileException(String name) {
		super("Couldn't find the file " + name + ".");
	}
}
