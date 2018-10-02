package facebook;

import java.io.IOException;
import java.util.logging.Level;

import com.sun.javafx.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Loginmanager {
	 private Scene scene;

	  public Loginmanager(Scene scene) {
	    this.scene = scene;
	  }

	  /**
	   * Callback method invoked to notify that a user has been authenticated.
	   * Will show the main application screen.
	   */ 
	  public void authenticated(String sessionID) {
	    showMainView(sessionID);
	  }

	  
	  public void showLoginScreen() {
	    try {
	      FXMLLoader loader = new FXMLLoader(
	        getClass().getResource("login.fxml")
	      );
	      scene.setRoot((Parent) loader.load());
	      Logincontroller controller = 
	        loader.<Logincontroller>getController();
	      controller.initManager(this);
	    } catch (IOException ex) {
	       System.out.println("hello");
	    }
	  }

	  private void showMainView(String sessionID) {
	    try {
	      FXMLLoader loader = new FXMLLoader(
	        getClass().getResource("mainview.fxml")
	      );
	      scene.setRoot((Parent) loader.load());
	      MainViewController controller = 
	        loader.<MainViewController>getController();
	      controller.initSessionID(this, sessionID);
	    } catch (IOException ex) {
	    	 System.out.println("nothing");
	    	//Logger.getLogger(Loginmanager.class.getName()).log(Level.SEVERE, null, ex);
	    }
	  }

	public void logout() {
		showLoginScreen();// TODO Auto-generated method stub
		
	}
}
