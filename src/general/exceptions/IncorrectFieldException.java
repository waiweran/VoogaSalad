package general.exceptions;

/**
 * This class represents what might go wrong when choosing values in Attribute Fields
 * 
 * @author DhruvKPatel
 * 
 * Adapted from XMLException:
 * @author Robert C. Duvall
 */

public class IncorrectFieldException extends Exception {

    // for serialization
    private static final long serialVersionUID = 1L;

    /**
     * Create an exception based on an issue in our code.
     */
    public IncorrectFieldException (String message, Object ... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public IncorrectFieldException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public IncorrectFieldException (Throwable cause) {
        super(cause);
    }

}
