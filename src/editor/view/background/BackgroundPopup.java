package editor.view.background;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import editor.controllers.BackgroundsController;
import general.ResourceLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Display for Background Selection.
 * @author Nathaniel Brooke
 * @version 04-25-2017
 */
public class BackgroundPopup  {
	
	private static final double DEFAULT_ZOOM = 0.3;

	private List<BackgroundView> backgrounds;
	private BackgroundsController control;
	private Stage stage;
	private BorderPane root;
	private Pane worldView;
	private double zoom;
	
	/**
	 * Initializes the Background selection view.
	 */
	public BackgroundPopup(BackgroundsController controller) {
		zoom = DEFAULT_ZOOM;
		backgrounds = new ArrayList<>();
		control = controller;
		stage = new Stage();
		root = new BorderPane();
		root.setTop(makeControls());
		worldView = new Pane();
		root.setCenter(worldView);
		worldView.toBack();
		Scene scene = new Scene(root, 1000, 650);
		stage.setScene(scene);
	}
	
	/**
	 * Shows the background editor.
	 */
	public void showPopup() {
		stage.show();
		control.loadBackgrounds();
	}
	
	/**
	 * Adds a new background to the background editor.
	 * @param b the new BackgroundView to add.
	 */
	public void newBackground(BackgroundView b) {
		backgrounds.add(b);
		worldView.getChildren().add(b.getView());
		worldView.getChildren().add(b.getResizeBall());
		b.setPosition(b.getLocation().scalarMultiply(zoom));
		b.setSize(b.getSize().scalarMultiply(zoom));
		b.getView().setTranslateX(stage.getWidth()/2);
		b.getView().setTranslateY(stage.getHeight()/2);
		b.getResizeBall().setTranslateX(stage.getWidth()/2);
		b.getResizeBall().setTranslateY(stage.getHeight()/2);
		b.setPosition(b.getLocation().add(b.getSize().negate().scalarMultiply(0.5)));
	}
	
	public void loadBackground(BackgroundView b) {
		backgrounds.add(b);
		worldView.getChildren().add(b.getView());
		worldView.getChildren().add(b.getResizeBall());
		b.setPosition(b.getLocation().scalarMultiply(zoom));
		b.setSize(b.getSize().scalarMultiply(zoom));
		b.getView().setTranslateX(stage.getWidth()/2);
		b.getView().setTranslateY(stage.getHeight()/2);
		b.getResizeBall().setTranslateX(stage.getWidth()/2);
		b.getResizeBall().setTranslateY(stage.getHeight()/2);
	}
	
	/**
	 * Removes a background from the background editor.
	 * @param b the background to remove.
	 */
	public void removeBackground(BackgroundView b) {
		backgrounds.remove(b);
		worldView.getChildren().remove(b);
	}
	
	/**
	 * @return an unmodifiable list of the backgrounds being displayed.
	 */
	public List<BackgroundView> getBackgrounds() {
		return Collections.unmodifiableList(backgrounds);
	}
	
	/**
	 * @return the current zoom factor by which everything is scaled.
	 */
	public double getZoom() {
		return zoom;
	}
	
	/**
	 * Generates the controls pane in the background editor.
	 * @return the controls pane.
	 */
	private Pane makeControls() {
		HBox controls = new HBox(10);
		controls.setAlignment(Pos.CENTER_LEFT);
		controls.setPadding(new Insets(5, 5, 5, 5));
		Text zoomLabel = new Text(new ResourceLoader().getDisplayResources().getString("Zoom"));
		Slider zoomSlider = new Slider(0.01, 1, zoom);
		zoomSlider.setOnMouseDragged(e -> setZoom(zoomSlider.getValue()));
		Button addBtn = new Button(new ResourceLoader().getDisplayResources().getString("NewBackground"));
		addBtn.setOnAction(e -> control.newBackground());
		Button saveBtn = new Button(new ResourceLoader().getDisplayResources().getString("Save"));
		saveBtn.setOnAction(e -> control.saveBackgrounds());
		controls.getChildren().addAll(zoomLabel, zoomSlider, addBtn, saveBtn);
		return controls;
	}
	
	/**
	 * Sets the zoom of all elements in the background editor.
	 * @param zoomVal the zoom value to set. (1 is true size)
	 */
	private void setZoom(double zoomVal) {
		for(BackgroundView b : backgrounds) {
			b.setPosition(b.getLocation().scalarMultiply(zoomVal/zoom));
			b.setSize(b.getSize().scalarMultiply(zoomVal/zoom));
		}
		zoom = zoomVal;

	}

}
