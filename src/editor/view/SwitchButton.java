package editor.view;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import editor.controllers.MenuController;

/**
 * Creates the switch button which switches between Goals Editor and World Editor scenes
 * @author Harry Liu
 * @version 04-25-17
 */
public class SwitchButton {
	
	private Button button = new Button();
	private SimpleBooleanProperty status = new SimpleBooleanProperty(false);
	private MenuController control;
	
	public void initializeSwitch(){
		button.setText("Switch to Goals Editor");
		
		button.setOnMouseClicked(e->{
			status.set(!status.get());		
		});
	}

	public SwitchButton(MenuController mainController) {
		control = mainController;
		initializeSwitch();
		status.addListener((d,e,f) -> {
			if (f) {
                		button.setText("Switch to World Editor");
                		control.editorMenuClicked("Goals");
            		}
            		else {
            			button.setText("Switch to Goals Editor");
            			control.editorMenuClicked("World");
            		}
		});
	}
	
	public Button getButton(){
		return button;
	}

}