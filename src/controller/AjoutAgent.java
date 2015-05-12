package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.AgentDao;
import application.database.DatabaseConnection;
import model.Agent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tools.ManipInterface;

public class AjoutAgent implements Initializable{
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML 
	private TextField nom;
	
	@FXML 
	private TextField prenom;
	
	@FXML
	private DatePicker dateNaiss;
	
	@FXML 
	private TextField numCP;
	
	@FXML 
	private TextField numPoste;
	
	private FXMLLoader loader;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//dateNaiss.setValue(LocalDate.now());
	}
	
	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionAgent.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	/*private String formatDate(){
		return jour.getValue()+"/"+mois.getValue()+"/"+annee.getValue();
	}*/
	
	private boolean validationFormulaire(){
		//Todo
		return true;
	}
	
	@FXML
	private void enregistrerAgent(ActionEvent event){
		if(validationFormulaire()){
			Agent newAgent = new Agent(nom.getText(), prenom.getText(), dateNaiss.getValue().toString(), numCP.getText(), numPoste.getText());
			AgentDao agentDao = new AgentDao();
			DatabaseConnection.startConnection();
			agentDao.save(newAgent);
			DatabaseConnection.closeConnection();
		}
		
	}
	
	@FXML
	private void selectionPoste(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Selection des logiciels", FXMLLoader.load(getClass().getResource("/view/RecherchePopup.fxml")));		
	}

}
