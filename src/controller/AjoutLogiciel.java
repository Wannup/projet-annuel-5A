package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import model.Agent;
import model.Logiciel;
import application.database.DatabaseConnection;
import dao.AgentDao;
import dao.LogicielDao;
import tools.ManipInterface;
import tools.TransformationDonnees;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AjoutLogiciel implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private TextField libelle;
	
	@FXML
	private TextField duree;
	
	@FXML
	private TextField prix;
	
	@FXML
	private Label msgAjoutOk;
	
	private FXMLLoader loader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
			Logiciel newLogiciel = new Logiciel(libelle.getText(), Double.parseDouble(duree.getText()), Integer.parseInt(prix.getText()));
			LogicielDao logicielDao = new LogicielDao();
			DatabaseConnection.startConnection();
			logicielDao.save(newLogiciel);
			DatabaseConnection.closeConnection();
			informerValidation();
		}	
	}
	
	private void informerValidation(){
		viderTousLesChamps();
		msgAjoutOk.setVisible(true);
	}
	
	private void viderTousLesChamps(){
		libelle.clear();
		duree.clear();
		prix.clear();
	}
}
