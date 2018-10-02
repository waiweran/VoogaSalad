package general.fields.selectionfields;

/**
 * Any object with this interface can be used in a SelectionField.
 * 
 * The purpose of this class is to ensure that classes that use Selection Field
 * have a way to represent themselves as a String. "toString" was not used instead
 * as a precaution in case there is another use for that method (e.g. debugging)
 * @author DhruvKPatel
 *
 */
public interface Selectable {

	/**
	 * Returns String representation of selectable object
	 * @return string
	 */
	public String getStringRepresentation();
}
