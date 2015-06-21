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

public class EditLogiciel implements Initializable{
	
	private LogicielDao lDao;
	private int idLogiciel;
	private Logiciel l;
	
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
		this.l = lDao.find(idLogiciel);
		this.libelle.setText(l.getNom());
		this.licenceNumber.setText(l.getLicenceNumber());
		this.prix.setText(""+l.getPrix());
	}
	
	@FXML
	public void saveLogiciel(){
		this.l.setNom(libelle.getText());
		this.l.setLicenceNumber(licenceNumber.getText());
		this.l.setPrix(Double.parseDouble(prix.getText()));
		
		Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
	    stage.close();
	}
}
