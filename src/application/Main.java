package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import View.Button.FacebookButton;
import View.Button.InstagramButton;
import View.Button.TwitterButton;


public class Main extends Application {
	
	private BorderPane root;
	private Scene scene;
	private VBox mainPane;
//	Font bb;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//billabong font for the instagram button
			root = new BorderPane();
			scene = new Scene(root,400,400);
			mainPane = new VBox();
			mainPane.setSpacing(150);
			generateNorth();
			generateCenter();
			generateSouth();
			root.setCenter(mainPane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void generateNorth(){
		BorderPane top = new BorderPane();
		Label title = new Label("Nearable");
		top.setCenter(title);
		mainPane.getChildren().add(top);
	}
	
	private void generateCenter(){
		BorderPane center = new BorderPane();
		TextField tf = new TextField("Enter text ");
		tf.setMaxSize(200, 10);
		center.setCenter(tf);
		mainPane.getChildren().add(center);
	}
	
	private void generateSouth(){
		StackPane south = new StackPane();
		HBox buttons = new HBox();
		buttons.setSpacing(15);
		//the bird is the word (twitter)
		Image im = new Image(getClass().getResource("..\\View\\Button\\Images\\bird.png").toExternalForm());
		ImageView iv = new ImageView(im);
		//fb log
		Image fbIM = new Image(getClass().getResource("..\\View\\Button\\Images\\fb.png").toExternalForm());
		ImageView fbIV = new ImageView(fbIM);
		//instagram logos
		Image igIM = new Image(getClass().getResource("..\\View\\Button\\Images\\ig.png").toExternalForm());
		ImageView igIV = new ImageView(igIM);
		//exit button
		Button exitButton = new Button("Exit");
		exitButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				System.exit(0);				
			}		
		});
		//random button for experiments
		FacebookButton facebookButton = new FacebookButton("Facebook",fbIV);
		TwitterButton twitterButton = new TwitterButton("Twitter", iv);
		InstagramButton instagramButton = new InstagramButton("Instagram", igIV);

		Font bb = Font.loadFont(getClass().getResourceAsStream("..\\View\\Button\\Fonts\\billabong.ttf"), 12);
		instagramButton.setFont(bb);
		facebookButton.setOnAction(new EventHandler<ActionEvent>() {
			VBox root = new VBox(2);
		    @Override public void handle(ActionEvent e) {
		        Stage stage = new Stage();
		        stage.show();
		        stage.setTitle("Events");
		        root.setAlignment(Pos.BOTTOM_RIGHT);
		    	Button b = new Button("Press me");
		    	//lambda expression
//		    	b.setOnAction(ev -> root.getChildren().add(new Label(""+i)));
		    	b.setOnAction(new EventHandler<ActionEvent>() {
		    		int i = 0;
		    		public void handle(ActionEvent e){
		    			Label l = new Label(""+i++);
		    			root.getChildren().add(l);
		    		}
		    	});
		    	ToolBar toolbar = new ToolBar(b);
		    	root.getChildren().add(toolbar);
				ScrollPane sp = new ScrollPane(root);
		        Scene eventsScene = new Scene(sp,350,350);
		        stage.setScene(eventsScene);
		    }
		});
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(exitButton, facebookButton, twitterButton, instagramButton);
		south.getChildren().addAll(buttons);
		mainPane.getChildren().add(south);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
