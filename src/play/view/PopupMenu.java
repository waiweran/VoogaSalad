package play.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import general.ResourceLoader;
import general.view.ScreenObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import play.view.Controllas.ItemsControlla;
import play.view.Controllas.PlayWorldControlla;
import play.view.popupMenuTypes.GoalsMenu;
import play.view.popupMenuTypes.PopupMenuItem;
import play.view.popupMenuTypes.ProgressMenu;

/**
 * Class that deals with setting the current PopUpMenu and generating the proper navigation header.
 * 
 * @author Harry Liu
 * @author Salo Abraham
 * @version 04-12-17
 */

public class PopupMenu implements ScreenObject {

	private PopupMenuItem myMenu;
	private Image arrowImage;
	private String imagePath = "nav_arrows.png";
	private double arrowColumn;
	private ObservableList<String> theItemList;
	private List<PopupMenuItem> popUpMenus;
	private int currentPosition;
	private AnchorPane finalPane;

	public PopupMenu(PlayWorldControlla controller, Stage theStage) {
		theItemList = FXCollections.observableArrayList();
		for (int x=0; x<15; x++){
			theItemList.add("sword");
		}	
		arrowImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));
		arrowColumn = arrowImage.getWidth()/2;
		initializeMenus(controller, theStage);
	}

	/**
	 * Initialize the menus
	 */
	private void initializeMenus(PlayWorldControlla controller, Stage daStage){
		currentPosition=0;
		finalPane = new AnchorPane();
		finalPane.setVisible(false);
		popUpMenus = new ArrayList<PopupMenuItem>();
		
		Collections.addAll(popUpMenus, new ItemsControlla(controller).getView(), new ProgressMenu(daStage, controller), new GoalsMenu(controller.getModel().getCurrentGoals()));
		myMenu = popUpMenus.get(currentPosition);
		updateCurrentMenu();
	}

	@Override
	public Pane getView() {
		return finalPane;
	}

	/**
	 * Puts together the Navigation bar with the pane below, making a complete PopUpMenu
	 * @param current the current PopupMenuItem
	 */
	public void updateCurrentMenu() {
		VBox MenuBox = new VBox();
		MenuBox.getChildren().addAll(
				updateNavHeader(new Label(myMenu.getName())),
				myMenu.getView());	
		setMenuBoxPreferences(MenuBox);
		finalPane.getChildren().clear();
		finalPane.getChildren().add(MenuBox);
	}

	/**
	 * Access the resource file with information about menubox sizing and set preferences
	 * @param MenuBox
	 */
	private void setMenuBoxPreferences(VBox MenuBox){
		MenuBox.setPrefWidth(Integer.parseInt(ResourceLoader.PLAYER_GRAPHICS.getString("PopUpMenuWidth")));
		MenuBox.setPrefHeight(Integer.parseInt(ResourceLoader.PLAYER_GRAPHICS.getString("PopUpMenuHeight")));
		MenuBox.setLayoutX(Integer.parseInt(ResourceLoader.PLAYER_GRAPHICS.getString("PopUpMenuX")));
		MenuBox.setLayoutY(Integer.parseInt(ResourceLoader.PLAYER_GRAPHICS.getString("PopUpMenuY")));
		MenuBox.setId("menu");
	}
	
	/**
	 * Creates an HBox with represents the top portion of a popup menu (Title and navigation arrows)
	 * @param title
	 * @return HBox
	 */
	private VBox updateNavHeader(Label title){
		VBox header = new VBox();
		header.getChildren().addAll(title, createNavigationButtonBox());
		header.setAlignment(Pos.CENTER);
		return header;
	}

	private HBox createNavigationButtonBox(){
		HBox navBox = new HBox();
		navBox.getChildren().addAll(leftNavButton(),rightNavButton());
		navBox.setId("nav");
		navBox.setPrefHeight(Integer.parseInt(ResourceLoader.PLAYER_GRAPHICS.getString("NavBoxHeight")));
		return navBox;
	}

	/**
	 * Creates the left navigation button. Set on Action is currently just filler.
	 * @return button
	 */
	private Button leftNavButton(){
		Button leftArrow = new Sprite(arrowImage, 50,  0, 0, arrowColumn, arrowImage.getHeight()).getButton();
		leftArrow.setOnAction(e-> ChangeLeft());
		expandButton(leftArrow);
		return leftArrow;
	}

	/**
	 * Creates the right navigation button. Set on Action is currently just filler.
	 * @return button
	 */
	private Button rightNavButton(){
		Button rightArrow = new Sprite(arrowImage, 50, arrowColumn, 0, arrowColumn, arrowImage.getHeight()).getButton();
		rightArrow.setOnAction(e->ChangeRight());
		expandButton(rightArrow);
		return rightArrow;
	}
	
	private void expandButton(Button button){
		HBox.setHgrow(button, Priority.ALWAYS);
		button.setMaxWidth(Double.MAX_VALUE);
	}

	/**
	 * Changes the menu item to the next menupane to the left
	 */
	private void ChangeLeft() {
		currentPosition= (currentPosition-1) % popUpMenus.size();
		if (currentPosition<0) {
			currentPosition += 2;
		}
		myMenu= popUpMenus.get(currentPosition);
		updateCurrentMenu();
	}

	/**
	 * Changes the menu item to the next menupane to the right
	 */
	private void ChangeRight() {
		currentPosition= (currentPosition+1)% popUpMenus.size();
		myMenu= popUpMenus.get(currentPosition);		
		updateCurrentMenu();
	}

}
