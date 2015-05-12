package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Agent;
import tools.ManipInterface;
import application.database.DatabaseConnection;
import dao.AgentDao;

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
	
	private String formatDateNaiss(){
		if(!dateNaiss.getValue().toString().isEmpty()){
			String[] date = dateNaiss.getValue().toString().split("-");
			return date[2]+"/"+date[1]+"/"+date[0];
		}
		return "";
	}
	
	private boolean validationFormulaire(){
		//Todo
		return true;
	}
	
	@FXML
	private void enregistrerAgent(ActionEvent event){
		if(validationFormulaire()){
			Agent newAgent = new Agent(nom.getText(), prenom.getText(), formatDateNaiss(), numCP.getText(), numPoste.getText());
			AgentDao agentDao = new AgentDao();
			DatabaseConnection.startConnection();
			agentDao.save(newAgent);
			DatabaseConnection.closeConnection();
		}	
	}
}
