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
	private Label words;
	@FXML
	private AnchorPane ap;
	@FXML
	private Label filePath;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void b1(ActionEvent e) {
		words.setText("Bruh moment");
	}
	
	public void b2(ActionEvent e) {
		words.setText("Bruh moment2");
	}
	
	public void b3(ActionEvent e) {
		words.setText("Bruh moment3");
	}
	
	public void open(ActionEvent e) {
		Stage stage = (Stage) ap.getScene().getWindow();
		File selectedFile = folderChooser.showDialog(stage);
		filePath.setText(selectedFile.getAbsolutePath());
		
		Scanner scan = new Scanner();
		scan.scanFolder(selectedFile.getAbsolutePath());
		
	}
	
	public void goToScene2(ActionEvent event) {
		try {
		
			String words = textbox.getText();
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
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

}
