package view;
import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public abstract class QueryViewTemplate {
	protected Stage primaryStage;
	protected Scene scene;
	protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected int elementsHeight = (int) screenSize.getHeight() / 30;
	protected int windowSize = (int) screenSize.getHeight() / 2;
	

	public QueryViewTemplate(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.selectOperation();
		
		
		
	}
	
	private void selectOperation() {		
	    ComboBox<String> comboBox = new ComboBox<>(this.getOperations());
		
		FlowPane flow = new FlowPane(Orientation.VERTICAL);
	    flow.setColumnHalignment(HPos.CENTER); // align labels on left
	    flow.setRowValignment(VPos.CENTER);
	    flow.setAlignment(Pos.CENTER);
	    flow.setPrefWrapLength(windowSize); // preferred height = 200
        flow.setVgap(10);
   
        
        comboBox.getSelectionModel().select("-Seleziona il tipo di operazione-");
        Button select = new Button("Ok");
        select.setPrefHeight(elementsHeight);
             
        flow.getChildren().add(comboBox);
        flow.getChildren().add(select);
        
        select.setOnMouseClicked(sel ->{
        	if (comboBox.getValue() != "-Seleziona il tipo di operazione-") {
        		this.operationView(comboBox.getValue());
        	}
        });
        
        
        flow.setPrefSize(windowSize, windowSize);
        flow.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
 
        Scene scene = new Scene(flow,windowSize, windowSize/4);
        
        primaryStage.setTitle("Welcome to DrinkingTeam DB");
        primaryStage.setScene(scene);
		primaryStage.setResizable(false);
        primaryStage.show();
	}
	
	protected ObservableList<String> getOperations(){
		return FXCollections.observableArrayList(
    	        "Ricerca prodotto",
    	        "Ricerca cliente"    
		);
	}
	protected void operationView(String op) {
		switch (op) {
		case "Ricerca prodotto":
			this.productSearch();
			break;
		case "Ricerca cliente":
			this.customerSearch();
		}
	}
	
	protected void productSearch() {
		TableView table = new TableView();
		
	}
	
	protected void customerSearch() {
		
	}
	
}
