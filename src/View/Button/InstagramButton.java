package View.Button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class InstagramButton extends Button {
	
	public InstagramButton(String s, ImageView iv){
		super(s, iv);
		this.getStylesheets().add(getClass().getResource("instagram.css").toExternalForm());
	}

}
