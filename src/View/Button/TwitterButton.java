package View.Button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class TwitterButton extends Button {
	
	public TwitterButton(String s, ImageView iv){
		super(s, iv);
		this.getStylesheets().add(getClass().getResource("twitter.css").toExternalForm());
	}

}
