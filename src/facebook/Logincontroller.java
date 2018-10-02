package facebook;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Logincontroller {
	private TextField user;
	private TextField password;
	private Button loginButton;
	public void initialize(){}
	public void initManager(final Loginmanager loginManager) {
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				String sessionID = authorize();
				if (sessionID != null) {
					loginManager.authenticated(sessionID);
				}
			}
		});
	}

	/**
	 * Check authorization credentials.
	 * 
	 * If accepted, return a sessionID for the authorized session
	 * otherwise, return null.
	 */   
	private String authorize() {
		return 
				"open".equals(user.getText()) && "sesame".equals(password.getText()) 
				? generateSessionID() 
						: null;
	}

	private static int sessionID = 0;

	private String generateSessionID() {
		sessionID++;
		return "xyzzy - session " + sessionID;
	}
}

