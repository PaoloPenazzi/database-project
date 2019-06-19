package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import model.Magazzino;
import model.MagazzinoMapper;

public class WarehouseView extends QueryViewTemplate {
	String warehouseName;

	public WarehouseView(Stage primaryStage) {
		super(primaryStage);
		this.selectWarehouse();
	}
	
	protected ObservableList<String> getOperations() {
		ObservableList<String> list = super.getOperations();
		list.add("Visualizza giacenze");
		list.add("Visualizza giacenze sotto la soglia minima");
		list.add("Registra ordine");
		list.add("Registra consegna");
		list.add("Aggiorna giacenza");
		list.add("Crea giacenza");
		return list;
	}
	
	protected String getWarehouseName() {
		return this.warehouseName;
	}
	
	protected void selectWarehouse() {
		List<String> choices = new ArrayList<>();
		for(Magazzino m : this.getDatabase().selectFrom("Magazzini", new MagazzinoMapper())) {
			choices.add(m.getNome());
		}

		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("");
		dialog.setHeaderText("A quale magazzino appartieni?");
		dialog.setContentText("Seleziona un magazzino:");
		dialog.initOwner(this.getStage());
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    this.warehouseName = result.get();
		}else {
			this.selectWarehouse();
		}
	}

}
