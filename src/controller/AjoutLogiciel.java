package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Logiciel;
import tools.ManipInterface;
import tools.TransformationDonnees;
import dao.LogicielDao;

public class AjoutLogiciel implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private TextField libelle;
	
	@FXML
	private TextField licenceNumber;
	
	@FXML
	private TextField prix;
	
	@FXML
	private DatePicker dateFinValidite;
	
	@FXML
	private Button btnAdd;
	
	public ListView<Logiciel> champLogicielFormEquipement;
	
	private FXMLLoader loader;
	private String errorMessage = "";
	private LogicielDao logicielDao;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 logicielDao = new LogicielDao();
	}

	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionLogiciel.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void enregistrerLogiciel(ActionEvent event){
		
		if(validationFormulaire()){
			
			Logiciel newLogiciel = new Logiciel(libelle.getText(), Double.parseDouble(prix.getText()), licenceNumber.getText(), TransformationDonnees.formatDate(dateFinValidite));
			logicielDao.save(newLogiciel);
			
			//popup
			if(champLogicielFormEquipement != null){
				champLogicielFormEquipement.getItems().add(newLogiciel);
				Stage fenetre =(Stage)btnAdd.getScene().getWindow();
           	 	fenetre.close();
			}
			//fenetre principal
			else
				informerValidation();	
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur enregistrement logiciel");
			alert.setHeaderText("Les champs ci-dessous sont incorrectes ou non renseign�s.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	
	private boolean validationFormulaire(){
		//Todo
		return true;
	}
	
	private void informerValidation(){
		viderTousLesChamps();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout logiciel");
		alert.setHeaderText(null);
		alert.setContentText("Logiciel ajout� avec succ�s !");
		alert.showAndWait();
	}
	
	private void viderTousLesChamps(){
		libelle.clear();
		licenceNumber.clear();
		prix.clear();
		dateFinValidite.getEditor().clear();
	}
}
