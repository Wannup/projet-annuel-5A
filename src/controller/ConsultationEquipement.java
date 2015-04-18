package controller;

import java.net.URL;
import java.util.ResourceBundle;

import model.Equipement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ConsultationEquipement implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	private ListView<Equipement> lstEquips;
}
