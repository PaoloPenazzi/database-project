package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class SellingView extends QueryViewTemplate {

	public SellingView(Stage primaryStage) {
		super(primaryStage);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ObservableList<String> getOperations() {
		ObservableList<String> list = super.getOperations();
		list.add("Registra vendita");
		list.add("Registra cliente");
		return list;
	}

	@Override
	protected void operationView(String op) {
		// TODO Auto-generated method stub
		
	}

}
