package application;
	
import java.io.IOException; 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Builder.fxml"));
			//((Controller) FXMLLoader.getController()).setStage(stage);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); for one scene
			
			String css = this.getClass().getResource("application.css").toExternalForm();// one css for multiple
			scene.getStylesheets().add(css);
			
			stage.setTitle(("lcom Calculator"));
			stage.setResizable(true);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
