package general;

import java.util.ResourceBundle;

/**
 * Fetches ResourceBundles.
 * @author Nathaniel Brooke
 * @version 04-01-2017
 */
public class ResourceLoader {
	
	/**
	 * ResourceBundle containing resources for reflection 
	 * and file information for the game editor.
	 */
	public static final ResourceBundle EDITOR_RESOURCES = 
			ResourceBundle.getBundle("editor.resources.EditorInfo");
	
	/**
	 * ResourceBundle containing resources for sizing in the edtior.
	 */
	public static final ResourceBundle EDITOR_GRAPHICS = 
			ResourceBundle.getBundle("editor.resources.WindowSizing");
	
	public static final ResourceBundle ATTRIBUTE_NAMES = 
			ResourceBundle.getBundle("resources.AttributeEnglish");
	
	public static final ResourceBundle PLAYER_GRAPHICS = 
			ResourceBundle.getBundle("play.resources.PlayerSizing");
	
	private static final String DEFAULT_LANGUAGE = "English";
	
	private String language;

	/**
	 * Initializes a ResourceLoade with the default language
	 */
	public ResourceLoader() {
		language = DEFAULT_LANGUAGE;
	}
	
	/**
	 * Sets the language of the resource file accesses
	 * @param newLanguage
	 */
	public void setLanguage(String newLanguage) {
		language = newLanguage;
	}
	
	/**
	 * @return String the current language
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * Gets ResourceBundles in general package for the current language
	 * @return ResourceBundle
	 */
	public ResourceBundle getDisplayResources() {
		return ResourceBundle.getBundle("resources." + language);
	}

}
