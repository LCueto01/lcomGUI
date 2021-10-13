package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lcomCalculator.Category;
import lcomCalculator.Scanner;

public class BuilderController {

	@FXML
	TextField paramBox;
	@FXML
	private GridPane classHolder;
	@FXML
	private VBox methodHolder;
	@FXML
	private VBox methodDeleteBox;
	
	private int classColumn = 0;
	private int classRow = 0;
	
	public void addToClassHolder(ActionEvent event) {
		
		Text words = new Text(paramBox.getText());
		words.setFont(Font.font(18));
		Button deleteBtn = new Button("Delete");
	
		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent arg0) {
				classHolder.getChildren().remove(classHolder.getChildren().indexOf(deleteBtn));
				classHolder.getChildren().remove(classHolder.getChildren().indexOf(words));
			}
		});
		classHolder.add(words, classRow, classColumn);
		classRow++;
		classHolder.add(deleteBtn, classRow, classColumn);
		classRow--;
		classColumn++;
	}
	public void addToMethodHolder(ActionEvent event) {
		Text words = new Text(paramBox.getText());
		words.setFont(Font.font(18));
		methodHolder.getChildren().add(words);
		methodDeleteBox.setSpacing(5);
		Button deleteBtn = new Button("Delete");
		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				methodHolder.getChildren().remove(methodDeleteBox.getChildren().indexOf(deleteBtn));
				methodDeleteBox.getChildren().remove(methodDeleteBox.getChildren().indexOf(deleteBtn));
			}
			
		});
		methodDeleteBox.getChildren().add(deleteBtn);
		
	}
	
}
