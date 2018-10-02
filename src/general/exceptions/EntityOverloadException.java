package general.exceptions;

/**
 * 
 * @author DhruvKPatel
 *
 */
public class EntityOverloadException extends Exception implements VoogaException {
	
	private static final long serialVersionUID = 1L;
	
	String message;
	
	public EntityOverloadException(String message) {
		this.message = message;
	}
	
	@Override
	public String message() {
		return message;
	}

}
