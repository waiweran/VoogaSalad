package facebook;

	import java.io.IOException;
	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.layout.*;
	import javafx.stage.Stage;
	import facebook.Loginmanager;

	/** Main application class for the login demo application */
	public class LoginDemo extends Application {
	  public static void main(String[] args) { launch(args); }
	  @Override public void start(Stage stage) throws IOException {
	    Scene scene = new Scene(new StackPane());
	    
	    Loginmanager loginManager = new Loginmanager(scene);
	    loginManager.showLoginScreen();

	    stage.setScene(scene);
	    stage.show();
	  }
	}

