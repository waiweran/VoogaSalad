package editor.view;

import java.util.List;

import general.ResourceLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Picks an image from a given list.
 * @author Nathaniel Brooke
 * @version 04-28-2017
 */
public class ImageChooser {

	private static final int NUM_COL = 8;

	public String selected;

	public ImageChooser() {
		selected = "";
	}

	public String pickImage(List<String> options) {
		Stage stage = new Stage();
		BorderPane root = new BorderPane();
		Text top = new Text(new ResourceLoader().getDisplayResources().getString("ChooseImage"));
		top.setFont(Font.font(30));
		VBox topPane = new VBox(top);
		topPane.setAlignment(Pos.CENTER);
		topPane.setPadding(new Insets(5, 0, 10, 0));
		root.setTop(topPane);
		root.setPadding(new Insets(0, 10, 10, 10));
		VBox imgRows = new VBox(10);
		root.setCenter(imgRows);
		HBox imgs = new HBox(5);
		int cols = 0;
		for(String s : options) {
			ImageView view = new ImageView(s);
			view.setFitHeight(100);
			view.setFitWidth(100);
			Button b = new Button();
			b.setGraphic(view);
			b.setOnAction(e -> {
				selected = s;
				stage.close();
			});
			imgs.getChildren().add(b);
			if(++cols > NUM_COL) {
				imgRows.getChildren().add(imgs);
				imgs = new HBox(5);
				cols = 0;
			}
		}
		imgRows.getChildren().add(imgs);
		stage.setScene(new Scene(root));
		stage.showAndWait();
		return selected;
	}

}
