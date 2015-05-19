package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Equipement;
import model.Logiciel;
import tools.ManipInterface;
import tools.TransformationDonnees;
import application.database.DatabaseConnection;
import dao.EquipementDao;

public class AjoutEquipement implements Initializable{
	
	@FXML
	private TextField numPoste;
	
	@FXML 
	private TextField prix;
	
	@FXML 
	private TextField numCPAgent;
	
	@FXML
	private TextField nbJoursPrev;

	@FXML
	private ComboBox<String> typeEquipement;
	
	@FXML
	private DatePicker dateAchat;
	
	@FXML
	private CheckBox logicielsOuiNon;
	
	@FXML 
	private ListView<Logiciel> lstLogiciel;
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private AnchorPane sectionLogiciel;
	
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
		
		sectionLogiciel.setVisible(false);
		
		logicielsOuiNon.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent me) {
			       if(logicielsOuiNon.isSelected())
			    	   sectionLogiciel.setVisible(true);
			       else
			    	   sectionLogiciel.setVisible(false);
			       
			       if(msgAjoutOk.isVisible())
				    	  msgAjoutOk.setVisible(false);
			    }
			});
		
		numPoste.setOnMouseClicked(enleverMessageAjout);	
		prix.setOnMouseClicked(enleverMessageAjout);
		numCPAgent.setOnMouseClicked(enleverMessageAjout);
		nbJoursPrev.setOnMouseClicked(enleverMessageAjout);
		typeEquipement.setOnMouseClicked(enleverMessageAjout);
		dateAchat.setOnMouseClicked(enleverMessageAjout);
		lstLogiciel.setOnMouseClicked(enleverMessageAjout);
			
	}
	
	@FXML
	private void selectionAgent(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Selection de l'agent", FXMLLoader.load(getClass().getResource("/view/RechercheAgentPopup.fxml")));
	}
	
	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}

	@FXML
	private void selectionLogiciels(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Selection des logiciels", FXMLLoader.load(getClass().getResource("/view/RecherchePopup.fxml")));		
	}
	
	@FXML
	private void ajoutLogiciel(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Ajouter un logiciel", FXMLLoader.load(getClass().getResource("/view/AjoutLogicielPopup.fxml")));	
	}

	@FXML
	private void ajoutAgent(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Ajouter un agent", FXMLLoader.load(getClass().getResource("/view/AjoutAgentPopup.fxml")));	
	}
	
	private boolean validationFormulaire(){
		//Todo
		return true;
	}
	
	@FXML
	private void enregistrerEquipement(ActionEvent event){
		
		if(validationFormulaire()){
			// à revoir la récupération de l'agent
			Equipement newEquipement = new Equipement(typeEquipement.getValue(), TransformationDonnees.getIntValue(numPoste), null, TransformationDonnees.getDoubleValue(prix), TransformationDonnees.getIntValue(nbJoursPrev), TransformationDonnees.formatDate(dateAchat));
			EquipementDao equipementDao = new EquipementDao();
			DatabaseConnection.startConnection();
			equipementDao.save(newEquipement);
			DatabaseConnection.closeConnection();
			informerValidation();
		}
	}
	
	private void informerValidation(){
		viderTousLesChamps();
		msgAjoutOk.setVisible(true);
	}
	
	private void viderTousLesChamps(){
		
		numPoste.clear();	
		prix.clear();
		numCPAgent.clear();
		nbJoursPrev.clear();
		typeEquipement.getEditor().clear();
		dateAchat.getEditor().clear();
		logicielsOuiNon.setSelected(false);
		lstLogiciel.getItems().clear();
	}
}
