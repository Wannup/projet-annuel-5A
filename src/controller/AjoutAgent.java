package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
	
	@FXML
	private Label msgAjoutOk;
	
	private FXMLLoader loader;

	private EventHandler<MouseEvent> enleverMessageAjout = new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent me) {
		       if(msgAjoutOk.isVisible())
		    	  msgAjoutOk.setVisible(false);
		}
	};
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		dateNaiss.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		       if(dateNaiss.getValue() == null)
		    	   dateNaiss.setValue(LocalDate.of(1970, 6, 15));
		       if(msgAjoutOk.isVisible())
			    	  msgAjoutOk.setVisible(false);
		    }
		});
		
		nom.setOnMouseClicked(enleverMessageAjout);
		prenom.setOnMouseClicked(enleverMessageAjout);
		numCP.setOnMouseClicked(enleverMessageAjout);
		numPoste.setOnMouseClicked(enleverMessageAjout);
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
		//ajouter un controle sur le numeroCP pour voir si l'agent n'est pas d�j� enregistr�
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
			informerValidation();
		}	
	}
	
	private void informerValidation(){
		viderTousLesChamps();
		msgAjoutOk.setVisible(true);
	}
	
	private void viderTousLesChamps(){
		nom.clear();
		prenom.clear();
		dateNaiss.getEditor().clear();
		numCP.clear();
		numPoste.clear();
	}
	
	@FXML
	private void selectionPoste(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Selection des logiciels", FXMLLoader.load(getClass().getResource("/view/RecherchePopup.fxml")));		
	}
}
