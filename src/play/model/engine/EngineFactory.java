package play.model.engine;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EngineFactory {
	private final static String ERRORS_RESOURCE = "resources/ErrorMessages";
	private final static String ENGINE_RESOURCE = "resources/Engine";
	private final static String CLASS_HEADER = "play.model.engine.";

	private ResourceBundle eResource = ResourceBundle.getBundle(ENGINE_RESOURCE);
	private ResourceBundle errors = ResourceBundle.getBundle(ERRORS_RESOURCE);
	private List<Engine> engines;

	public EngineFactory() {
		engines = new ArrayList<Engine>();
		for (String s : eResource.keySet()) {
			try {
				Class<?> c;
				c = Class.forName(CLASS_HEADER + eResource.getString(s).trim());
				Constructor<?> tempConstructor = c.getConstructor();
				Engine tempEngine = (Engine) tempConstructor.newInstance();
				engines.add(tempEngine);
			} catch (Exception e) {
				throw new RuntimeException(errors.getString("EngineFactory") + s, e);
			}
		}
	}
	
	public List<Engine> getEngines(){
		return engines;
	}
}
