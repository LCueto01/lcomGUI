package application;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
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
	@FXML
	private ChoiceBox<String> methodDrop;
	@FXML
	private Button incrementBtn;
	@FXML
	private VBox varBox;
	
	private String selectedChoice = null;
	private Category cat = null;
	private boolean attributeCreation = true;

	
	public void initialize() {

	methodDrop.setValue("Choose a method");;
	selectedChoice = "Choose a method";

	}
	
	public void addToClassHolder(ActionEvent event) {
		if(cat == null) {
			triggerAlert("class is null");
			return;
		}
		else if(!attributeCreation) {
			triggerAlert("class closed");
			return;
		}
		Text words = new Text(paramBox.getText());
		words.setFont(Font.font(18));
		classHolder.getChildren().add(words);
		classDeleteBox.setSpacing(5);
		Button deleteBtn = new Button("Delete");
		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				cat.removeFromVariables(classHolder.getChildren().indexOf(words));
				varBox.getChildren().remove(classHolder.getChildren().indexOf(words));
				classHolder.getChildren().remove(classDeleteBox.getChildren().indexOf(deleteBtn));
				classDeleteBox.getChildren().remove(classDeleteBox.getChildren().indexOf(deleteBtn));
			}
			
		});
		classDeleteBox.getChildren().add(deleteBtn);
		cat.addToVariables(paramBox.getText());
		varBox.getChildren().add(new CheckBox(words.getText()));
		
	}
	public void addToMethodHolder(ActionEvent event) {
		if(cat == null) {
			triggerAlert("class is null");
			return;
		}
		else if(!attributeCreation) {
			triggerAlert("class closed");
			return;
		}
		Text words = new Text(paramBox.getText());
		words.setFont(Font.font(18));
		methodHolder.getChildren().add(words);
		methodDeleteBox.setSpacing(5);
		Button deleteBtn = new Button("Delete");
		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				cat.removeFromMethods(methodHolder.getChildren().indexOf(words));
				methodHolder.getChildren().remove(methodDeleteBox.getChildren().indexOf(deleteBtn));
				methodDeleteBox.getChildren().remove(methodDeleteBox.getChildren().indexOf(deleteBtn));
			}
			
		});
		methodDeleteBox.getChildren().add(deleteBtn);
		cat.addToMethods(paramBox.getText());
		methodDrop.getItems().add(words.getText());
	}
	
	public void createClass(ActionEvent event) {
		if(cat != null) {
			triggerAlert("already created");
			return;
		}
		classLabel.setText(paramBox.getText());
		 cat = new Category(classLabel.getText());
	}
	
	public void deleteClass(ActionEvent event) {
		if(cat == null) {
			triggerAlert("no class delete");
			return;
		}
		classLabel.setText("No Class currently created");
		lcomLabel.setText("Lcom value goes here");
		paramBox.clear();
		classHolder.getChildren().clear(); classDeleteBox.getChildren().clear();
		methodHolder.getChildren().clear(); methodDeleteBox.getChildren().clear();
		varBox.getChildren().clear(); methodDrop.getItems().clear();
		cat = null;
	}
	
	public void calculateLcom(ActionEvent event) {
		if(cat == null) {
			triggerAlert("class is null");
			return;
		}
		
		Scanner scan = new Scanner();
		lcomLabel.setText(scan.calculateLcom(cat, 0));
	}
	
	public void returnToMenu(ActionEvent event) {
		NavigatorController nav = new NavigatorController();
		nav.returnToMenu(event);
	}
	
	public void triggerAlert(String errortype) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		switch(errortype) {
		case "class is null":
			alert.setTitle("Class is null error");
			alert.setHeaderText("Class not created.");
			alert.setResizable(false);
			alert.setContentText("You are trying to add to a class that doesn't exist, create a class first.");
			break;
		case "no class delete":
			alert.setTitle("Class Deletion Error");
			alert.setHeaderText("Class not created.");
			alert.setResizable(false);
			alert.setContentText("You are trying to delete a class that doesn't exist, create a class first.");
			break;
		case "already created":
			alert.setTitle("Class Creation Error");
			alert.setHeaderText("Class currently in use.");
			alert.setResizable(false);
			alert.setContentText("You are trying to create a class while still working on a class. Delete your "
			+" current class to make a new one.");
			break;
		case "lock class":
			alert.setTitle("Initiating Usage Calculation");
			alert.setHeaderText("Can no longer add attributes");
			alert.setResizable(false);
			alert.setContentText("Class is now closed for appending. If you wish to add more attributes, delete"
			+ " this class and start again, to continue incrementing usage, press ok");
			break;
		case "class locked":
			alert.setTitle("Class Locked Error");
			alert.setHeaderText("Class is currently locked");
			alert.setResizable(false);
			alert.setContentText("Class is currently closed for appending. If you wish to add more attributes, delete"
			+ " this class and start again");
			break;
		}
		
		
		alert.show();
	}
	
	public void checkMethodBox() {
		methodDrop.getSelectionModel().getSelectedItem();
		if(selectedChoice!= methodDrop.getSelectionModel().getSelectedItem()) {
			selectedChoice = methodDrop.getSelectionModel().getSelectedItem();
			
			if(varBox.getChildren() != null) {
				for(Node child : varBox.getChildren()) {
					if(child instanceof CheckBox) {
						((CheckBox) child).setSelected(false);
					}
				}
			}
		}
	}
	
	public void increaseVarUsage(ActionEvent event) {
		if(attributeCreation) {
			triggerAlert("lock class");
		}
		triggerAlert("lock class");
		attributeCreation = false;
		Node element;
		if(varBox.getChildren() != null) {
			for(int i = 0; i < varBox.getChildren().size();i++) {
				element = varBox.getChildren().get(i);
				if(element instanceof CheckBox && ((CheckBox) element).isSelected()) {
					cat.incrementAttribute(varBox.getChildren().indexOf(element));
				}
			}
			
		}
	}
}
