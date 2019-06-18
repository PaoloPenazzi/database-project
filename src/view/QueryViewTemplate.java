package view;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteMapper;
import model.Database;
import model.Prodotto;
import model.ProdottoMapper;

public abstract class QueryViewTemplate {
	private Stage primaryStage;
	private Database database;
	protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected int elementsHeight = (int) screenSize.getHeight() / 30;
	protected int windowSize = (int) screenSize.getHeight() / 2;
	

	public QueryViewTemplate(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.database = new Database();
		this.selectOperation();
	}
	
	private void selectOperation() {		
	    ComboBox<String> comboBox = new ComboBox<>(this.getOperations());
		
		FlowPane flow = this.getFlowPane();
		flow.setOrientation(Orientation.VERTICAL);
   
        
        comboBox.getSelectionModel().select("-Seleziona il tipo di operazione-");
        Button select = new Button("Ok");
        select.setPrefHeight(elementsHeight);
             
        flow.getChildren().add(comboBox);
        flow.getChildren().add(select);
        
        select.setOnMouseClicked(sel ->{
        	if (!comboBox.getValue().equals("-Seleziona il tipo di operazione-")) {
        		this.operationView(comboBox.getValue());
        	}
        });
        
        
        flow.setPrefSize(windowSize, windowSize);
        flow.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
 
        Scene scene = new Scene(flow,windowSize, windowSize/4);
        
        primaryStage.setScene(scene);
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
			break;
		case "Registra cliente":
			this.insertCustomer();
			
		}
		
	}
	
	
	protected void productSearch() {
		TableView<Prodotto> table = new TableView<>();
		List<TableColumn<Prodotto, String>> columns = new ArrayList<>();
		
		for (String name : this.database.getColumnNamesOf("prodotti")) {
			TableColumn<Prodotto, String> column = new TableColumn<>(name);
			column.setCellValueFactory(new PropertyValueFactory<>(name));
			if(name.equals("Prezzo_unitario")) {
				column.setText("Prezzo_unitario (€)");
			}
			if(name.equals("Capacita")) {
				column.setText("Capacità (ml)");
			}
			columns.add(column);
		}
		
		table.getColumns().addAll(columns);
		
		FilteredList<Prodotto> filteredProdotti = new FilteredList<>(FXCollections.observableList(this.database.selectFrom("prodotti", new ProdottoMapper())), p -> true);
		//table.setItems(FXCollections.observableList(jdbc.query....));
		table.setItems(filteredProdotti);
		table.setPrefWidth(windowSize*2);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TextField search = new TextField("Cerca...");
		search.setOnKeyReleased(keyEvent ->
        {
        	filteredProdotti.setPredicate(p -> p.getNome().toLowerCase().contains(search.getText().toLowerCase().trim()));//filter table by name
        });
		
		FlowPane pane = this.getFlowPane();
		pane.getChildren().add(search);
		pane.getChildren().add(table);
		pane.getChildren().add(this.getBackButton());
		
		Scene scene = new Scene(pane,windowSize*3, windowSize*2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Ricerca Prodotto");
	}

	
	
	protected void customerSearch() {
		TableView<Cliente> table = new TableView<>();
		List<TableColumn<Cliente, String>> columns = new ArrayList<>();
		
		for (String name : this.database.getColumnNamesOf("clienti")) {
			TableColumn<Cliente, String> column = new TableColumn<>(name);
			column.setCellValueFactory(new PropertyValueFactory<>(name));
			columns.add(column);
		}
		
		table.getColumns().addAll(columns);
		
		FilteredList<Cliente> filteredProdotti = new FilteredList<>(FXCollections.observableList(this.database.selectFrom("clienti", new ClienteMapper())), p -> true);
		//table.setItems(FXCollections.observableList(jdbc.query....));
		table.setItems(filteredProdotti);
		table.setPrefWidth(windowSize*2);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TextField search = new TextField("Cerca...");
		search.setOnKeyReleased(keyEvent ->
        {
        	filteredProdotti.setPredicate(p -> p.getNome().toLowerCase().contains(search.getText().toLowerCase().trim()));//filter table by name
        });
		
		FlowPane pane = this.getFlowPane();
		pane.getChildren().add(search);
		pane.getChildren().add(table);
		pane.getChildren().add(this.getBackButton());
		
		Scene scene = new Scene(pane,windowSize*3, windowSize*2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Ricerca Prodotto");
	}
	
	protected void insertCustomer() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		
		List<HBox> fields = this.getDefaultInputForm("Clienti");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Clienti(Nome,Tipologia,Indirizzo,telefono,Codice_Zona) values(?,?,?,?,?)",
				        ((TextField)fields.get(0).getChildren().get(1)).getText(),
				        ((TextField)fields.get(1).getChildren().get(1)).getText(),
				        ((TextField)fields.get(2).getChildren().get(1)).getText(),
				        ((TextField)fields.get(3).getChildren().get(1)).getText(),
				        Integer.parseInt(((TextField)fields.get(4).getChildren().get(1)).getText())
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		Scene scene = new Scene(pane,windowSize, windowSize/2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione cliente");
		
	}
	
	protected Database getDatabase() {
		return this.database;
	}
	
	protected Stage getStage() {
		return this.primaryStage;
	}
	
	//----------------------------------------------------------------------------------//
	//____________________________________VIEW UTILS____________________________________//
	
	protected FlowPane getFlowPane() {
		FlowPane flow = new FlowPane(Orientation.HORIZONTAL);
	    flow.setColumnHalignment(HPos.CENTER); // align labels on left
	    flow.setRowValignment(VPos.CENTER);
	    flow.setAlignment(Pos.CENTER);
	    flow.setPrefWrapLength(windowSize); 
        flow.setHgap(10);
        flow.setPrefSize(windowSize, windowSize);
        flow.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
        return flow;
	}
	
	protected List<HBox> getDefaultInputForm(String tableName){
		List<HBox> fields = new ArrayList<>();
		
		for(String columnName : this.database.getColumnNamesOf(tableName)) {
			Label text = new Label(String.format("%1$"+40+ "s", columnName));
			TextField field = new TextField(columnName);
			if(columnName.contains("Data")) {
				field.setText("dd/MM/yyyy");
			}
			HBox box = new HBox();
			box.getChildren().addAll(text, field);
			box.setSpacing(5);
			fields.add(box);
		}
		return fields;
	}
	
	protected Button getBackButton() {
		Button back = new Button("Back");
		back.setOnMouseClicked(b ->{
        	this.selectOperation();
        });
		return back;
	}
	
	
	protected void successPopup() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Risultato dell'operazione");
		alert.setHeaderText(null);
		alert.setContentText("La registrazione è avvenuta con successo!");
		alert.initOwner(this.primaryStage);
		alert.showAndWait();
	}
	
	
	protected void exceptionPopup(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Risultato dell'operazione");
		alert.setHeaderText("Si è verificato un errore");
		alert.setContentText("Could not find file blabla.txt!");

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);
		alert.initOwner(this.primaryStage);
		alert.showAndWait();
	}

	
}
