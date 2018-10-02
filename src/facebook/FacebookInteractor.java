package facebook;

import com.restfb.exception.FacebookException;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public interface FacebookInteractor{
	public String getUsername(ActionEvent event, Label message);
	public ImageView getPicture() throws FacebookException;
	
}
