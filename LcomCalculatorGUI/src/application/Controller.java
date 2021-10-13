package application; 

import lcomCalculator.Scanner;



import java.io.File; 
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller {
	DirectoryChooser folderChooser = new DirectoryChooser();

	@FXML
	TextField textbox;
	@FXML
	private AnchorPane ap;
	@FXML
	private Label filePath;

	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private int nextCoordinate = 200;
	
	public void open(ActionEvent e) {
		Stage stage = (Stage) ap.getScene().getWindow();
		File selectedFile = folderChooser.showDialog(stage);
		filePath.setText(selectedFile.getAbsolutePath());
		
		Scanner scan = new Scanner();
		scan.scanFolder(filePath.getText());
		
	}
	
	public void goToScene2(ActionEvent event) {
		try {
		
			String words = textbox.getText();
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ClassScanner.fxml"));
			root = loader.load();
			Contoller2 cont2 = loader.getController();
			cont2.displayStuff(words);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void goToScene1(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Builder.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void calculateClasses() {
		try {
			Scanner scan = new Scanner();
			String[] scannedClasses = scan.scanFolder(filePath.getText());
			for(int i = 0; i < scannedClasses.length;i++) {
				Label lab = new Label(scannedClasses[i]);
				ap.getChildren().add(lab);
				lab.setTranslateX(30); lab.setTranslateY(nextCoordinate);
				nextCoordinate += 130;
			}
			
			
		}
		catch(Exception e) {
			
		}
		
	}
	
	public void returnToMenu(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
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

}
