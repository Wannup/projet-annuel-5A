package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Equipement;
import dao.EquipementDao;

public class InformationEquipement implements Initializable{

	@FXML
	Parent root;
	
	@FXML
	private Label type;
	
	@FXML
	private Label marque;
	
	@FXML
	private Label modele;
	
	@FXML
	private Label calife;
	
	@FXML
	private Label nom;
	
	@FXML
	private Label prenom;
	
	@FXML
	private Label cp;
	
	@FXML
	private TextArea info;
	
	@FXML
	private Button ok;
	
	private int idEquipement;
	
	private EquipementDao eDao;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.eDao = new EquipementDao();
	}
	
	public void setValues(int id){
		this.idEquipement = id;

		Equipement e = eDao.find(idEquipement);
		
		this.type.setText(e.getTypeEquipement());
		this.marque.setText(e.getMarque());
		this.modele.setText(e.getModele());
		this.calife.setText(e.getCalife());
		//this.nom.setText(e.getAgent().getNom());
		//this.prenom.setText(e.getAgent().getPrenom());
		this.info.setText(e.getInfo());
		//this.cp.setText(e.getAgent().getNumCP());
	}

	@FXML
	private void validate(){
		Stage stage = (Stage) ok.getScene().getWindow();
	    stage.close();
	}
}
