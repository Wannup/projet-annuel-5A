package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Logiciel;
import tools.ManipInterface;
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
	private Label msgAjoutOk;
	
	private FXMLLoader loader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionLogiciel.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	private boolean validationFormulaire(){
		//Todo
		return true;
	}
	
	@FXML
	private void enregistrerLogiciel(ActionEvent event){
		if(validationFormulaire()){
			Logiciel newLogiciel = new Logiciel(libelle.getText(), Double.parseDouble(prix.getText()), licenceNumber.getText());
			LogicielDao logicielDao = new LogicielDao();
			logicielDao.save(newLogiciel);
			informerValidation();
		}	
	}
	
	private void informerValidation(){
		viderTousLesChamps();
		msgAjoutOk.setVisible(true);
	}
	
	private void viderTousLesChamps(){
		libelle.clear();
		licenceNumber.clear();
		prix.clear();
	}
}
