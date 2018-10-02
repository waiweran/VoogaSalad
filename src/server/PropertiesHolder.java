package server;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class PropertiesHolder {
	
	private ResourceBundle options;
	private List<Entry<String, Pattern>> info;

	public PropertiesHolder(){
		info = new ArrayList<>();
	}

	public void addInfo(String loc) throws PropertiesFileException {
		try {
			options = ResourceBundle.getBundle(loc);
			Enumeration<String> iter = options.getKeys();
			while (iter.hasMoreElements()) {
				String key = iter.nextElement();
				String regex = options.getString(key);
				info.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
			}
		} catch (Exception e) {
			throw new PropertiesFileException(loc);
		}
	}

	// TODO check and refactor
	public String getValue(String i) {
		return options.getString(i);
	}

	public String getKey(String name) throws PropertiesInfoException {
		for (Entry<String, Pattern> e : info)
			if (match(name, e.getValue()))
				return e.getKey();
		throw new PropertiesInfoException(name);
	}

	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}

}
