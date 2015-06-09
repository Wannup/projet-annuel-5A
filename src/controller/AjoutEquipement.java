package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Equipement;
import model.Logiciel;
import model.TypeEquipement;
import tools.ManipInterface;
import tools.TransformationDonnees;
import application.database.DatabaseConnection;
import dao.EquipementDao;
import dao.TypeEquipementDao;

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
	private TextField modele;
	
	@FXML
	private TextField marque;
	
	@FXML
	private TextArea info;
	
	@FXML
	private TextField calife;
	
	@FXML
	private ComboBox<TypeEquipement> typeEquipement;
	
	@FXML
	private DatePicker dateGarantie;
	
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
	
	private TypeEquipementDao typeEquipementDao;
	
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
		
		typeEquipementDao = new TypeEquipementDao();
		DatabaseConnection.startConnection();
		typeEquipement.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributes(null)));
		DatabaseConnection.closeConnection();
				
		
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
		//ManipInterface.newWindow("Ajouter un agent", FXMLLoader.load(getClass().getResource("/view/AjoutAgentPopup.fxml")));	
		Stage stage = new Stage();
        stage.setTitle("Ajouter un agent");
        stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AjoutAgentPopup.fxml"))));
        stage.show();
	}
	
	private boolean validationFormulaire(){
		//Todo
		return true;
	}
	
	@FXML
	private void enregistrerEquipement(ActionEvent event){
		
		if(validationFormulaire()){
			// todo recupération de l'agent
			System.out.println(typeEquipement.getValue().getNom());
			Equipement newEquipement = new Equipement(typeEquipement.getValue().getNom(), TransformationDonnees.getIntValue(numPoste), null, TransformationDonnees.getDoubleValue(prix), TransformationDonnees.getIntValue(nbJoursPrev), TransformationDonnees.formatDate(dateGarantie), marque.getText(), modele.getText(), calife.getText(), info.getText());
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
		marque.clear();	
		modele.clear();	
		calife.clear();	
		prix.clear();
		info.clear();
		numCPAgent.clear();
		nbJoursPrev.clear();
		typeEquipement.getEditor().clear();
		dateGarantie.getEditor().clear();
		logicielsOuiNon.setSelected(false);
		lstLogiciel.getItems().clear();
	}
}
