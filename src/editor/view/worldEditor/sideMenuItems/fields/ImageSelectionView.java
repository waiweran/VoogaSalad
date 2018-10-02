package editor.view.worldEditor.sideMenuItems.fields;

import general.ResourceLoader;
import general.exceptions.IncorrectFieldException;
import general.fields.Field;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ImageSelectionView extends FieldView {

	private static final int NUM_COL = 8;
	
	public ImageSelectionView(Field<?> f) {
		super(f);
	}

	@Override
	protected Node makeFieldView(Field<?> f) {
		Button b = new Button(f.getName());
		b.setOnAction(e -> pickImage(f));
		return b;
	}
	
	private void pickImage(Field<?> f) {
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
		try {
			for(String s : f.getOptions()) {
				ImageView view = new ImageView(s);
				view.setFitHeight(100);
				view.setFitWidth(100);
				Button b = new Button();
				b.setGraphic(view);
				b.setOnAction(e -> {
					try {
						f.setValue(s);
						stage.close();
					} catch (IncorrectFieldException e1) {
						throw new RuntimeException(e1);
					}
				});
				imgs.getChildren().add(b);
				if(++cols > NUM_COL) {
					imgRows.getChildren().add(imgs);
					imgs = new HBox(5);
					cols = 0;
				}
			}
			imgRows.getChildren().add(imgs);
		} catch (IncorrectFieldException e) {
			throw new RuntimeException(e);
		}
		stage.setScene(new Scene(root));
		stage.show();
	}

}
