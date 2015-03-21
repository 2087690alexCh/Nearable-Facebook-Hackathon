package View.Button;

import javafx.scene.control.Button;

public class CombinedButton extends Button {
	
	public CombinedButton(String s){
		super(s);
		this.getStylesheets().add(getClass().getResource("combined.css").toExternalForm());
	}
	
}