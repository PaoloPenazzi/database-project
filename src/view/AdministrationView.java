package view;

import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class AdministrationView extends QueryViewTemplate {

	public AdministrationView(Stage primaryStage) {
		super(primaryStage);
		// TODO Auto-generated constructor stub
	}
	
	protected ObservableList<String> getOperations() {
		ObservableList<String> list = super.getOperations();
		list.add("Registra prodotto");
		list.add("Registra fornitore");
		list.add("Registra accordo");
		list.add("Aggiorna accordo");
		list.add("Ricerca accordo");
		list.add("Visualizza agenti");
		list.add("Visualizza dipendenti");
		return list;
	}

}
