package View.Button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class FacebookButton extends Button {
	
	public FacebookButton(String s, ImageView iv){
		super(s,iv);
		this.getStylesheets().add(getClass().getResource("facebook.css").toExternalForm());
	}

}
