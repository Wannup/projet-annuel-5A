package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Logiciel;

/**
 * class controller for the interface EditLogiciel.fxml
 * */
public class EditLogiciel implements Initializable{
	
	private Logiciel logiciel;
	
	@FXML
	private Button btnEnregistrer;
	
	@FXML
	private TextField libelle;
	
	@FXML
	private TextField licenceNumber;
	
	@FXML
	private TextField prix;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void setValues(Logiciel logicielSelect) {
		
		logiciel = logicielSelect;
		libelle.setText(logiciel.getNom());
		licenceNumber.setText(logiciel.getLicenceNumber());
		prix.setText(String.valueOf(logiciel.getPrix()));
	}
	
	@FXML
	public void saveLogiciel(){
		this.logiciel.setNom(libelle.getText());
		this.logiciel.setLicenceNumber(licenceNumber.getText());
		this.logiciel.setPrix(Double.parseDouble(prix.getText()));
		
		Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
	    stage.close();
	}
}
