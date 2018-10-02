package play.view;

import general.view.ScreenObject;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import play.view.Controllas.HeadsUpControlla;

/**
 * Holds the Heads-Up display.
 * @author Harry Liu
 * @author Salo Abraham
 * @author Nathaniel Brooke
 *@version 04-15-17
 */
public class HeadsUpDisplay implements ScreenObject {
	
	private static final double HEALTH_LENGTH = 100;
	
	private double width, height;
	private Pane headsup;
	private HeadsUpControlla control;
	private Rectangle fullHealth, emptyHealth;
	private double totalHealth;

	public HeadsUpDisplay(HeadsUpControlla controller) {
		width = Screen.getPrimary().getVisualBounds().getWidth();
		height = Screen.getPrimary().getVisualBounds().getHeight();
		control = controller;
		createHeadsUp();
	}

	@Override
	public Pane getView() {
		return headsup;
	}
	
	private void createHeadsUp(){
		headsup = new Pane();
		headsup.getChildren().addAll(createItemDisplay(), createHealthDisplay());
	}
	
	private StackPane createItemDisplay() {
		StackPane currentItem = new StackPane();
		Circle itemDisplay = new Circle(0, 0, 60);
		itemDisplay.setFill(Color.LIGHTSTEELBLUE);
		itemDisplay.setOpacity(0.5);
		currentItem.getChildren().addAll(itemDisplay);
		ImageView itemView = control.getCurrentItem();
		itemView.setFitWidth(100);
		itemView.setFitHeight(100);
		currentItem.getChildren().add(control.getCurrentItem());
		currentItem.setLayoutX(width/2 - 60);
		currentItem.setLayoutY(height*0.9 - 70);
		return currentItem;
	}
	
	private HBox createHealthDisplay() {
		HBox health = new HBox();
		fullHealth = new Rectangle(0, 30);
		fullHealth.setFill(Color.GREEN);
		emptyHealth = new Rectangle(0, 30);
		emptyHealth.setFill(Color.RED);
		totalHealth = control.getCurrentHealth();
		fullHealth.setWidth(totalHealth);
		health.getChildren().addAll(fullHealth, emptyHealth);
		control.getMainController().addUpdater(e -> updateHealth());
		health.setLayoutX(width/50);
		health.setLayoutY(height*9.0/10);
		return health;
	}
	
	private void updateHealth() {
		double newVal = control.getCurrentHealth();
		fullHealth.setWidth(newVal*HEALTH_LENGTH/totalHealth);
		emptyHealth.setWidth(HEALTH_LENGTH - newVal*HEALTH_LENGTH/totalHealth);
	}
	
}
