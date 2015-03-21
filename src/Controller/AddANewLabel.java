package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AddANewLabel implements EventHandler<ActionEvent> {
	
	private VBox parent;
	private Label news;
	
	//May not be Label, need to combine with the backend
	public AddANewLabel(Label newsBox, VBox paren){
		this.parent = paren;
		news = newsBox;
	}
	

	@Override
	public void handle(ActionEvent event) {
		parent.getChildren().addAll(news);
	}

}
