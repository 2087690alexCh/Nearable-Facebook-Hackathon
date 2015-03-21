package View.Button;

import javafx.scene.control.Button;

public class FacebookButton extends Button {
	
	public FacebookButton(String s){
		super(s);
		this.getStylesheets().add(getClass().getResource("facebook.css").toExternalForm());
	}

}
