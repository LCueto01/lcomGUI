package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Contoller2 {

	@FXML
	TextField textBox;
	@FXML
	private Label randLabel;
	@FXML
	private Label textBoxLabel;
	
	
	public void displayStuff(String words) {
		randLabel.setText(words);
	}
	
	public void add2Label(ActionEvent event) {
		try {
			String words = textBox.getText();
			textBoxLabel.setText(textBoxLabel.getText() + words + "\n");
		}catch(Exception e) {
			
		}
		
	}
}
