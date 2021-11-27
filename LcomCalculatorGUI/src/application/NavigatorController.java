package application;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigatorController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void goToScanner(ActionEvent event) {
		try {
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ClassScanner.fxml"));
			root = loader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void goToBuilder(ActionEvent event) {
		try {
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Builder.fxml"));
			root = loader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void returnToMenu(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			root = loader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();// one css for multiple
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


