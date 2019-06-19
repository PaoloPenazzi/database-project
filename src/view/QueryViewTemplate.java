package view;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.Accordo;
import model.AccordoMapper;
import model.Agente;
import model.AgenteMapper;
import model.Cliente;
import model.ClienteMapper;
import model.Database;
import model.Dipendente;
import model.DipendenteMapper;
import model.Giacenza;
import model.GiacenzaMapper;
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
		case "Registra prodotto":
			this.registerProduct();
			break;
		case "Registra fornitore":
			this.registerSupplier();
			break;
		case "Registra accordo":
			this.registerAgreement();
			break;
		case "Registra dipendente":
			this.registerEmployee();
			break;
		case "Registra agente":
			this.registerAgent();
			break;
		case "Aggiorna accordo":
			this.updateAgreement();
			break;
		case "Ricerca accordo":
			this.agreementSearch();
			break;
		case "Visualizza dipendenti":
			this.employeeSearch();
			break;
		case "Visualizza agenti":
			this.agentSearch();
			break;
		case "Visualizza giacenze":
			this.stockSearch("select * from giacenze where Magazzino='"+this.getWarehouseName()+"'");
			break;
		case "Visualizza giacenze sotto la soglia minima":
			this.stockSearch("select * from giacenze where Magazzino='"+this.getWarehouseName()+"' AND Quantita < Giacenza_minima");
			break;
		case "Crea giacenza":
			this.registerStock();
			break;
		case "Aggiorna giacenza":
			this.updateStock();
			break;
		case "Registra ordine":
			this.registerOrder();
			break;
		case "Registra consegna":
			this.registerDelivery();
			break;
		case "Registra vendita":
			this.registerSell();
			break;
		case "Registra cliente":
			this.registerCustomer();
			break;
		case "Calcola provvigione":
			this.computeRemuneration();
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
		table.setItems(filteredProdotti);
		table.setPrefWidth(windowSize*2);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TextField search = new TextField("Cerca...");
		search.setOnKeyReleased(keyEvent ->
        {
        	filteredProdotti.setPredicate(p -> p.getNome().toLowerCase().contains(search.getText().toLowerCase().trim()));
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
		
		FilteredList<Cliente> filteredList = new FilteredList<>(FXCollections.observableList(this.database.selectFrom("clienti", new ClienteMapper())), p -> true);
		table.setItems(filteredList);
		table.setPrefWidth(windowSize*2);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TextField search = new TextField("Cerca per nome...");
		search.setOnKeyReleased(keyEvent ->
        {
        	filteredList.setPredicate(p -> p.getNome().toLowerCase().contains(search.getText().toLowerCase().trim()));
        });
		
		FlowPane pane = this.getFlowPane();
		pane.getChildren().add(search);
		pane.getChildren().add(table);
		pane.getChildren().add(this.getBackButton());
		
		Scene scene = new Scene(pane,windowSize*3, windowSize*2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Ricerca Prodotto");
	}
	
	protected void stockSearch(String SQL) {
		TableView<Giacenza> table = new TableView<>();
		List<TableColumn<Giacenza, String>> columns = new ArrayList<>();
		
		for (String name : this.database.getColumnNamesOf("giacenze")) {
			TableColumn<Giacenza, String> column = new TableColumn<>(name);
			column.setCellValueFactory(new PropertyValueFactory<>(name));
			columns.add(column);
		}
		
		table.getColumns().addAll(columns);
		
		FilteredList<Giacenza> filteredList = new FilteredList<>(FXCollections.observableList(this.database.getJdbc().query(SQL, new GiacenzaMapper())), p -> true);
		table.setItems(filteredList);
		table.setPrefWidth(windowSize*2);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TextField search = new TextField("Cerca per codice prodotto...");
		search.setOnKeyReleased(keyEvent ->
        {
        	filteredList.setPredicate(p -> Integer.toString(p.getCodice_prodotto()).contains(search.getText().toLowerCase().trim()));
        });
		
		FlowPane pane = this.getFlowPane();
		pane.getChildren().add(search);
		pane.getChildren().add(table);
		pane.getChildren().add(this.getBackButton());
		
		Scene scene = new Scene(pane,windowSize*3, windowSize*2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Giacenze");
	}
	
	protected void agreementSearch() {
		TableView<Accordo> table = new TableView<>();
		List<TableColumn<Accordo, String>> columns = new ArrayList<>();
		
		for (String name : this.database.getColumnNamesOf("accordi")) {
			TableColumn<Accordo, String> column = new TableColumn<>(name);
			column.setCellValueFactory(new PropertyValueFactory<>(name));
			columns.add(column);
		}
		
		table.getColumns().addAll(columns);
		
		FilteredList<Accordo> filteredList = new FilteredList<>(FXCollections.observableList(this.database.selectFrom("accordi", new AccordoMapper())), p -> true);
		table.setItems(filteredList);
		table.setPrefWidth(windowSize*2);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TextField search = new TextField("Cerca per codice prodotto...");
		search.setOnKeyReleased(keyEvent ->
        {
        	filteredList.setPredicate(p -> Integer.toString(p.getCodice_prodotto()).contains(search.getText().toLowerCase().trim()));
        });
		
		FlowPane pane = this.getFlowPane();
		pane.getChildren().add(search);
		pane.getChildren().add(table);
		pane.getChildren().add(this.getBackButton());
		
		Scene scene = new Scene(pane,windowSize*3, windowSize*2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Ricerca accordo");
	}
	
	
	protected void employeeSearch() {
		TableView<Dipendente> table = new TableView<>();
		List<TableColumn<Dipendente, String>> columns = new ArrayList<>();
		
		for (String name : this.database.getColumnNamesOf("dipendenti")) {
			TableColumn<Dipendente, String> column = new TableColumn<>(name);
			column.setCellValueFactory(new PropertyValueFactory<>(name));
			columns.add(column);
		}
		
		table.getColumns().addAll(columns);
		
		FilteredList<Dipendente> filteredList = new FilteredList<>(FXCollections.observableList(this.database.selectFrom("dipendenti", new DipendenteMapper())), p -> true);
		table.setItems(filteredList);
		table.setPrefWidth(windowSize*2);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TextField search = new TextField("Cerca per cognome...");
		search.setOnKeyReleased(keyEvent ->
        {
        	filteredList.setPredicate(p -> p.getCognome().contains(search.getText().toLowerCase().trim()));
        });
		
		FlowPane pane = this.getFlowPane();
		pane.getChildren().add(search);
		pane.getChildren().add(table);
		pane.getChildren().add(this.getBackButton());
		
		Scene scene = new Scene(pane,windowSize*3, windowSize*2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Visualizza dipendenti");
	}
	
	
	protected void agentSearch() {
		TableView<Agente> table = new TableView<>();
		List<TableColumn<Agente, String>> columns = new ArrayList<>();
		
		for (String name : this.database.getColumnNamesOf("agenti")) {
			TableColumn<Agente, String> column = new TableColumn<>(name);
			column.setCellValueFactory(new PropertyValueFactory<>(name));
			columns.add(column);
		}
		
		table.getColumns().addAll(columns);
		
		FilteredList<Agente> filteredList = new FilteredList<>(FXCollections.observableList(this.database.selectFrom("agenti", new AgenteMapper())), p -> true);
		table.setItems(filteredList);
		table.setPrefWidth(windowSize*2);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TextField search = new TextField("Cerca per cognome...");
		search.setOnKeyReleased(keyEvent ->
        {
        	filteredList.setPredicate(p -> p.getCognome().contains(search.getText().toLowerCase().trim()));
        });
		
		FlowPane pane = this.getFlowPane();
		pane.getChildren().add(search);
		pane.getChildren().add(table);
		pane.getChildren().add(this.getBackButton());
		
		Scene scene = new Scene(pane,windowSize*3, windowSize*2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Visualizza dipendenti");
	}
	
	
	protected void registerCustomer() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		
		List<HBox> fields = this.getInputForm("Clienti", "---","---","---");
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
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione cliente");
		
	}
	
	protected void registerSell() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		List<HBox> fields = this.getInputForm("Vendite","Numero","---","---");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Vendite(Quantita,Data,Sconto,Codice_prodotto,Nome_cliente,Partita_IVA_agente) values(?,?,?,?,?,?)",
						Integer.parseInt(((TextField)fields.get(0).getChildren().get(1)).getText()),
						new SimpleDateFormat("dd/MM/yyyy").parse(((TextField)fields.get(1).getChildren().get(1)).getText()),
						Double.parseDouble(((TextField)fields.get(2).getChildren().get(1)).getText()),
						Integer.parseInt(((TextField)fields.get(3).getChildren().get(1)).getText()),
				        ((TextField)fields.get(4).getChildren().get(1)).getText(),
				        ((TextField)fields.get(5).getChildren().get(1)).getText()
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			} catch (ParseException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione vendita");
		
	}
	
	protected void registerProduct() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		
		List<HBox> fields = this.getInputForm("Prodotti", "Codice_prodotto","---","---");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Prodotti(Nome,Capacita,Marca,Prezzo_unitario,Tipologia,Confezione) values(?,?,?,?,?,?)",
				        ((TextField)fields.get(0).getChildren().get(1)).getText(),
				        Integer.parseInt(((TextField)fields.get(1).getChildren().get(1)).getText()),
				        ((TextField)fields.get(2).getChildren().get(1)).getText(),
				        Double.parseDouble(((TextField)fields.get(3).getChildren().get(1)).getText()),
				        ((TextField)fields.get(4).getChildren().get(1)).getText(),
				        ((TextField)fields.get(5).getChildren().get(1)).getText()
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione prodotto");
		
	}
	
	protected void registerSupplier() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		
		List<HBox> fields = this.getInputForm("Fornitori", "---","---","---");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Fornitori(Nome,Indirizzo,telefono,partita_IVA) values(?,?,?,?)",
				        ((TextField)fields.get(0).getChildren().get(1)).getText(),
				        ((TextField)fields.get(1).getChildren().get(1)).getText(),
				        ((TextField)fields.get(2).getChildren().get(1)).getText(),
				        ((TextField)fields.get(3).getChildren().get(1)).getText()
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/2);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione fornitore");
		
	}
	
	protected void registerAgreement() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		List<HBox> fields = this.getInputForm("Accordi","---","---","---");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Accordi(Nome_fornitore,Codice_prodotto,Prezzo_di_acquisto,Data_scadenza,Codice_Dipendente) values(?,?,?,?,?)",
						((TextField)fields.get(0).getChildren().get(1)).getText(),
						Integer.parseInt(((TextField)fields.get(1).getChildren().get(1)).getText()),
						Double.parseDouble(((TextField)fields.get(2).getChildren().get(1)).getText()),
						new SimpleDateFormat("dd/MM/yyyy").parse(((TextField)fields.get(3).getChildren().get(1)).getText()),						
						Integer.parseInt(((TextField)fields.get(4).getChildren().get(1)).getText())				       
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			} catch (ParseException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione accordo");
		
	}
	
	
	protected void registerEmployee() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		
		List<HBox> fields = this.getInputForm("Dipendenti", "Codice_Dipendente","---","---");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Dipendenti(Nome,Cognome,Stipendio,Indirizzo,telefono,Ruolo,Ufficio) values(?,?,?,?,?,?,?)",
				        ((TextField)fields.get(0).getChildren().get(1)).getText(),
				        ((TextField)fields.get(1).getChildren().get(1)).getText(),
				        Integer.parseInt(((TextField)fields.get(2).getChildren().get(1)).getText()),
				        ((TextField)fields.get(3).getChildren().get(1)).getText(),
				        ((TextField)fields.get(4).getChildren().get(1)).getText(),
				        ((TextField)fields.get(5).getChildren().get(1)).getText(),
				        ((TextField)fields.get(6).getChildren().get(1)).getText()
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione dipendente");
		
	}
	
	protected void registerAgent() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		
		List<HBox> fields = this.getInputForm("Agenti", "---","---","---");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Agenti(Nome,Cognome,partita_IVA,Provvigione,telefono,Codice_Zona) values(?,?,?,?,?,?)",
				        ((TextField)fields.get(0).getChildren().get(1)).getText(),
				        ((TextField)fields.get(1).getChildren().get(1)).getText(),
				        ((TextField)fields.get(2).getChildren().get(1)).getText(),
				        Double.parseDouble(((TextField)fields.get(3).getChildren().get(1)).getText()),
				        ((TextField)fields.get(4).getChildren().get(1)).getText(),
				        Integer.parseInt(((TextField)fields.get(5).getChildren().get(1)).getText())
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione agente");
		
	}
	
	
	
	
	protected void registerStock() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		List<HBox> fields = this.getInputForm("Giacenze","Magazzino","---","---");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Giacenze(Codice_prodotto,Magazzino,Settore,Quantita,Giacenza_minima) values(?,?,?,?,?)",
						Integer.parseInt(((TextField)fields.get(0).getChildren().get(1)).getText()),
						this.getWarehouseName(),
						Integer.parseInt(((TextField)fields.get(1).getChildren().get(1)).getText()),
						Integer.parseInt(((TextField)fields.get(2).getChildren().get(1)).getText()),
						Integer.parseInt(((TextField)fields.get(3).getChildren().get(1)).getText())
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Creazione giacenza");
		
	}
	
	protected void updateStock() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		List<HBox> fields = this.getInputForm("Giacenze","Magazzino","Settore","Giacenza_minima");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Aggiorna");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("UPDATE Giacenze SET quantita=? WHERE Codice_prodotto=? AND Magazzino=?",
						Integer.parseInt(((TextField)fields.get(1).getChildren().get(1)).getText()),
						Integer.parseInt(((TextField)fields.get(0).getChildren().get(1)).getText()),
						this.getWarehouseName()
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Aggiornamento giacenza");
	}
	
	protected void updateAgreement() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		List<HBox> fields = this.getInputForm("Accordi","---","---","---");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Aggiorna");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("UPDATE Accordi SET Data_Scadenza=?, Prezzo_di_acquisto=?, Codice_Dipendente=?  WHERE Codice_prodotto=? AND Nome_fornitore=?",
						new SimpleDateFormat("dd/MM/yyyy").parse(((TextField)fields.get(3).getChildren().get(1)).getText()),				
						Double.parseDouble(((TextField)fields.get(2).getChildren().get(1)).getText()),
						Integer.parseInt(((TextField)fields.get(4).getChildren().get(1)).getText()),
						Integer.parseInt(((TextField)fields.get(1).getChildren().get(1)).getText()),			
						((TextField)fields.get(0).getChildren().get(1)).getText()
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			} catch (ParseException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Aggiornamento accordo");
		
	}
	
	protected void registerOrder() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		List<HBox> fields = this.getInputForm("Ordini","Numero_Fattura","Magazzino", "---");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Ordini(Quantita,Data_ordine,Data_consegna,Magazzino,Nome_fornitore,Codice_prodotto) values(?,?,?,?,?,?)",
						Integer.parseInt(((TextField)fields.get(0).getChildren().get(1)).getText()),
						new SimpleDateFormat("dd/MM/yyyy").parse(((TextField)fields.get(1).getChildren().get(1)).getText()),
						new SimpleDateFormat("dd/MM/yyyy").parse(((TextField)fields.get(2).getChildren().get(1)).getText()),
						this.getWarehouseName(),
						((TextField)fields.get(3).getChildren().get(1)).getText(),
						Integer.parseInt(((TextField)fields.get(4).getChildren().get(1)).getText())
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			} catch (ParseException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione ordine");
		
	}
	
	protected void registerDelivery() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		List<HBox> fields = this.getInputForm("Consegne","---","---", "Magazzino");
		pane.getChildren().addAll(fields);
		
		Button register = new Button("Registra");
		register.setOnMouseClicked(p -> {
			try {
				this.database.getJdbc().update("INSERT INTO Consegne(Numero_Fattura,Nome_magazzino,Nome_cliente,Data_Consegna) values(?,?,?,?)",
						Integer.parseInt(((TextField)fields.get(0).getChildren().get(1)).getText()),
						this.getWarehouseName(),
						((TextField)fields.get(1).getChildren().get(1)).getText(),
						new SimpleDateFormat("dd/MM/yyyy").parse(((TextField)fields.get(2).getChildren().get(1)).getText())
						);
				this.successPopup();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			} catch (ParseException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Registrazione consegna");
		
	}
	
	protected void computeRemuneration() {
		FlowPane pane = this.getFlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		
		HBox box = new HBox();
		Label text = new Label("Inserire partita IVA dell'agente:");
		TextField field = new TextField("Partita IVA");
		box.getChildren().addAll(text,field);
		
		pane.getChildren().add(box);
		
		Button register = new Button("Calcola");
		register.setOnMouseClicked(p -> {
			try {
									
						
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Risultato dell'operazione");
				alert.setHeaderText(null);
				alert.setContentText("Importo dovuto all'agente: "+this.database.getJdbc().queryForObject("select p.prezzo_unitario*v.quantita*a.Provvigione as totale" + 
						" from prodotti p, vendite v, agenti a" + 
						" where v.partita_IVA_agente = a.partita_IVA" + 
						" and p.Codice_prodotto = v.Codice_prodotto" + 
						" and a.partita_IVA = '"+field.getText()+"'", String.class)+ " €"	);
				alert.initOwner(this.primaryStage);
				alert.showAndWait();
			} catch (DataAccessException e) {
				this.exceptionPopup(e);
			} catch (NumberFormatException e) {
				this.exceptionPopup(e);
			}
		});
		
		pane.getChildren().add(register);
		pane.getChildren().add(this.getBackButton());
		Scene scene = new Scene(pane,windowSize, windowSize/1.5);
		this.getStage().setScene(scene);
		this.getStage().setTitle("Calcolo provvigione");
		
	}
	
	
	
	
	protected Database getDatabase() {
		return this.database;
	}
	
	protected Stage getStage() {
		return this.primaryStage;
	}
	
	protected String getWarehouseName() {
		return null;
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
	
	protected List<HBox> getInputForm(String tableName, String forbiddenName, String forbiddenName2, String forbiddenName3){
		List<HBox> fields = new ArrayList<>();
		
		for(String columnName : this.database.getColumnNamesOf(tableName)) {
			if(!columnName.contains(forbiddenName) && !columnName.contains(forbiddenName2) && !columnName.contains(forbiddenName3)) {
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
		alert.setContentText("Di seguito informazioni dettagliate su cosa è andato storto.");

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
