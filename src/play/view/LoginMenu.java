package play.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginMenu extends Stage{

	private static final String BACKGROUND = "backgrounds/load.png";
	
	private VBox vPane;

/*
	/**
	 * The constructor of the load menu class where the user will upload game data to start the game
	 * @param daStage
	 /**
	public LoginMenu(Stage daStage) {

	}
	private VBox setLogin(){
		ButtonBuilder ButtonLogin=new ButtonBuilder("create",event->Login());
		Vpane.getChildren().addAll(ButtonLogin.getButton());
		lab.setText("username");
		
		return Vpane;	
	}
	private Object Login() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	private CreateAccount createAccount;
	private LoginAccount logAccount;
	
	private VBox setLogin(){
		ButtonBuilder ButtonLogin=new ButtonBuilder("Create Account", event-> createAccount.setVisible(true));
		ButtonBuilder ButtonLog=new ButtonBuilder("Login", event-> logAccount.setVisible(true));
		ButtonBuilder FinalButton= new ButtonBuilder("Exit", event-> System.exit(0));
		vPane.getChildren().addAll(ButtonLogin.getButton(),ButtonLog.getButton(),FinalButton.getButton());
		return vPane;
	}
	
	public LoginMenu(Stage daStage){
		ButtonBuilder ButtonLogin=new ButtonBuilder("Create Account", event-> createAccount.setVisible(true));
		ButtonBuilder ButtonLog=new ButtonBuilder("Login", event-> logAccount.setVisible(true));
		ButtonBuilder FinalButton= new ButtonBuilder("Exit", event-> System.exit(0));
		vPane.getChildren().addAll(ButtonLogin.getButton(),ButtonLog.getButton(),FinalButton.getButton());
		StackPane pane=new StackPane();
		Scene theScene= new Scene (pane);
		pane.getChildren().addAll(buildBackground(pane),vPane);
		daStage.setScene(theScene);
		daStage.show();
	}
	private ImageView buildBackground(StackPane pane){
		Image image= new Image(getClass().getClassLoader().getResourceAsStream(BACKGROUND));
		ImageView background = new ImageView(image);
		background.fitHeightProperty().bind(pane.heightProperty());
		background.fitWidthProperty().bind(pane.widthProperty());
		return background;
	}
}


