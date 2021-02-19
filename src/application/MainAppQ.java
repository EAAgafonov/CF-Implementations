package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainAppQ extends Application {
	private Stage primaryStage;
	Button button;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("First one");

        button  =new Button();
        button.setText("Click me");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	
	
	
	
	
	public static void main(String[] args) { launch(args); }
}
