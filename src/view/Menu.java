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
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Menu extends Application {
	private FlowPane flow;
	private Button select;
	private ObservableList<String> options = 
    	    FXCollections.observableArrayList(
    	        "Venditore",
    	        "Magazziniere",
    	        "Amministrativo"
    	    );
    private ComboBox<String> comboBox = new ComboBox<>(options);
	
	/**
     * This is the Main Method.
     * @param args - the standard args parameter.
     */
    public static void main(final String[] args) {
        Application.launch(Menu.class, args);
    }

	
	public void start(Stage primaryStage) throws Exception {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int elementsHeight = (int) screenSize.getHeight() / 30;
		final int windowSize = (int) screenSize.getHeight() / 2;
		
		flow = new FlowPane(Orientation.VERTICAL);
	    flow.setColumnHalignment(HPos.CENTER); // align labels on left
	    flow.setRowValignment(VPos.CENTER);
	    flow.setAlignment(Pos.CENTER);
	    flow.setPrefWrapLength(windowSize); // preferred height = 200
        flow.setVgap(10);
   
       
        
        comboBox.getSelectionModel().select("-Seleziona il tipo di utente-");
        select = new Button("Ok");
        select.setPrefHeight(elementsHeight);
             
        flow.getChildren().add(comboBox);
        flow.getChildren().add(select);
 
        
        select.setOnMouseClicked(sel ->{
        	if (this.comboBox.getValue().equals(this.options.get(0))) {
        		new SellingView(primaryStage);
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

}
