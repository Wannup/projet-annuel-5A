package controller;

import java.net.URL;
import java.util.ResourceBundle;

import model.Logiciel;
import dao.LogicielDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * class controller for the interface EditLogiciel.fxml
 * */
public class EditLogiciel implements Initializable{
	
	private LogicielDao lDao;
	private int idLogiciel;
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
		this.lDao = new LogicielDao();
	}

	public void setValues(int id) {
		this.idLogiciel =  id;		
		this.logiciel = lDao.find(idLogiciel);
		this.libelle.setText(logiciel.getNom());
		this.licenceNumber.setText(logiciel.getLicenceNumber());
		this.prix.setText(""+logiciel.getPrix());
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
