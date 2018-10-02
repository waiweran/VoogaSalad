package facebook;

import java.net.URL;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args){
		launch(args);
	}

	public void initialize(URL url, ResourceBundle rb){

	}

	private static void authUser(ActionEvent event, Label message){

		String domain = "https://www.cs.duke.edu/courses/compsci308/spring17/";
		String appId = "1885657521648661";
//		String authURL = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
//				+ "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
//				+ "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
//				+ "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
//				+ "manage_notifications,manage_pages,publish_actions, read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";
		String authURL = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
		+ "email,public_profile,user_friends";

		System.setProperty("webdriver.chrome.driver", "src/facebook/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get(authURL);
		String accessToken;

		while(true){

			if(!driver.getCurrentUrl().contains("facebook.com")){
				String url = driver.getCurrentUrl();
				accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

				driver.quit();

				FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
				User user = fbClient.fetchObject("me",User.class);

				message.setText(user.getEmail());

			}
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		Button button = new Button("press");
		Label message = new Label();
		VBox box = new VBox(button, message);
		pane.getChildren().add(box);
		button.setOnAction(e -> authUser(e, message));
		Scene myScene;
		Stage myStage;

		//		String accessToken = "EAACEdEose0cBANMNuKHQs7ggZAsyYw0jF53fd8ZCyKU0ySweRGPj7ZAmn1aqNJhPiVc4N3PxupfcxWOPbdZCVZC6r1OZAKU6LlPqwWr9gEBc4fnnJd4JlOHJnZCjSNZCXKS2Yyo1n86oxQCqZAefvYRt5vpQcFI2CP7juGnXF3w7UQ9AVvbNkuZAUpjKBhkzKuKi4ZD";
		//		
		//		FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
		//		
		//		User me = fbClient.fetchObject("me", User.class, Parameter.with("fields", "email"));
		//		
		//		System.out.println(me.getEmail());

		myStage = new Stage();
		myScene = new Scene(pane, 400, 400, Color.BLUE);
		myStage.setScene(myScene);
		myStage.show();
		
	}
}
