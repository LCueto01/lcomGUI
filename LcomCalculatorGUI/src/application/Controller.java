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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller {
	DirectoryChooser folderChooser = new DirectoryChooser();

	
	@FXML
	private AnchorPane ap;
	@FXML
	private Label filePath;
	@FXML 
	private VBox lcomHolder;
	@FXML
	private TextField cutoffText;

	
	private Stage stage;
	private Scene scene;
	private Parent root;

	
	public void open(ActionEvent e) {
		Stage stage = (Stage) ap.getScene().getWindow();
		File selectedFile = folderChooser.showDialog(stage);
		filePath.setText(selectedFile.getAbsolutePath());
		
		try {
			
		}catch(Exception e1) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("File Scan error");
			alert.setHeaderText("Files could not be scanned");
			alert.setResizable(false);
			alert.setContentText("Scanner could not scan files, recheck files in the directory to make sure they are java files");
			alert.show();
		}
		
		
	}
	
	
	
	public void goToScene1(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Builder.fxml"));
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
	
	public void calculateClasses() {
		try {
			Scanner scan = new Scanner();
			double cutoff = Double.parseDouble(cutoffText.getText());
			if(cutoff < 0 || cutoff > 1) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Invalid Cutoff");
				alert.setHeaderText("Input cutoff cannot be used");
				alert.setResizable(false);
				alert.setContentText("Cutoff must be a double, less than 1 and cannot be negative. Default cutoff of 0.7 will be used");
				alert.show();
				cutoff = 0.7;
			}
				
			String[] scannedClasses = scan.scanFolder(filePath.getText(),cutoff);
			for(int i = 0; i < scannedClasses.length;i++) {
				Text classes = new Text(scannedClasses[i]);
				lcomHolder.getChildren().add(classes);
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
			String css = this.getClass().getResource("application.css").toExternalForm();// one css for multiple
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void clearClasses(ActionEvent event) {
		lcomHolder.getChildren().clear();
		cutoffText.clear();
	}

}
