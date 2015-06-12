package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Agent;
import model.Pole;
import tools.ManipInterface;
import tools.TransformationDonnees;
import application.database.DatabaseConnection;
import dao.AgentDao;
import dao.PoleDao;
import dao.TypeEquipementDao;

public class AjoutAgent implements Initializable{
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML 
	private TextField nom;
	
	@FXML 
	private TextField prenom;
	
	@FXML 
	private TextField numCP;
	
	@FXML 
	private TextField tel;
	
	@FXML 
	private TextField numPoste;
	
	@FXML
	private Label msgAjoutOk;
	
	@FXML
	private ComboBox<Pole> pole;
	
	private FXMLLoader loader;
	
	private PoleDao poleDao;
	
	private EventHandler<MouseEvent> enleverMessageAjout = new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent me) {
		       if(msgAjoutOk.isVisible())
		    	  msgAjoutOk.setVisible(false);
		}
	};
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		nom.setOnMouseClicked(enleverMessageAjout);
		prenom.setOnMouseClicked(enleverMessageAjout);
		numCP.setOnMouseClicked(enleverMessageAjout);
		numPoste.setOnMouseClicked(enleverMessageAjout);
		
		poleDao = new PoleDao();
		DatabaseConnection.startConnection();
		pole.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
		DatabaseConnection.closeConnection();
	}
	
	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionAgent.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	private boolean validationFormulaire(){
		//Todo
		//ajouter un controle sur le numeroCP pour voir si l'agent n'est pas deja enregistré
		return true;
	}
	
	@FXML
	private void enregistrerAgent(ActionEvent event){
		if(validationFormulaire()){
			Agent newAgent = new Agent(nom.getText(), prenom.getText(), /*todo pole */ "", tel.getText(), numCP.getText(), numPoste.getText());
			AgentDao agentDao = new AgentDao();
			DatabaseConnection.startConnection();
			agentDao.save(newAgent);
			DatabaseConnection.closeConnection();
			informerValidation();
		}	
		
	}
	
	public void informerValidation(){
		viderTousLesChamps();
		msgAjoutOk.setVisible(true);
	}
	
	private void viderTousLesChamps(){
		nom.clear();
		prenom.clear();
		numCP.clear();
		numPoste.clear();
		pole.getEditor().clear();
	}
	
	@FXML
	private void selectionPoste(ActionEvent event) throws IOException{
		// � changer
		ManipInterface.newWindow("Selection d'un poste", FXMLLoader.load(getClass().getResource("/view/RecherchePopup.fxml")));
		
	}
}
