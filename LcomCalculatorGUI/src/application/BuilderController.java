package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lcomCalculator.Category;
import lcomCalculator.Scanner;
import application.NavigatorController;

public class BuilderController {

	@FXML
	TextField paramBox;
	@FXML
	private VBox classHolder;
	@FXML
	private VBox classDeleteBox;
	@FXML
	private Label classLabel;
	@FXML
	private VBox methodHolder;
	@FXML
	private VBox methodDeleteBox;
	@FXML
	private Label lcomLabel;
	
	private Category cat = null;
	
	public void addToClassHolder(ActionEvent event) {
		Text words = new Text(paramBox.getText());
		words.setFont(Font.font(18));
		classHolder.getChildren().add(words);
		classDeleteBox.setSpacing(5);
		Button deleteBtn = new Button("Delete");
		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				classHolder.getChildren().remove(classDeleteBox.getChildren().indexOf(deleteBtn));
				classDeleteBox.getChildren().remove(classDeleteBox.getChildren().indexOf(deleteBtn));
			}
			
		});
		classDeleteBox.getChildren().add(deleteBtn);
		cat.addToVariables(paramBox.getText());
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
		cat.addToMethods(paramBox.getText());
	}
	
	public void createClass(ActionEvent event) {
		classLabel.setText(paramBox.getText());
		 cat = new Category(classLabel.getText());
		 System.out.println(cat.getName());
	}
	
	public void deleteClass(ActionEvent event) {
		classLabel.setText("No Class currently created");
	}
	
	public void calculateLcom(ActionEvent event) {
		/*
		Scanner scan = new Scanner();
		scan.calculateLcom(cat, 0);
		*/
		cat.printMethods();
		cat.printVars();
	}
	
	public void returnToMenu(ActionEvent event) {
		NavigatorController nav = new NavigatorController();
		nav.returnToMenu(event);
	}
}
