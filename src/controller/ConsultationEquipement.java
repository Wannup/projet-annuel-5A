package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.Equipement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ConsultationEquipement implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Equipement> list = new ArrayList<>();
		ObservableList<String> items = FXCollections.observableArrayList();
		for (Equipement equip : list) {
			items.add(equip.toString());
		}
		lstEquips.setItems(items);
	}

	@FXML
	private ListView<String> lstEquips;
}
