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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Equipement;
import model.Logiciel;
import model.TypeEquipement;
import tools.ManipInterface;
import tools.TransformationDonnees;
import application.database.DatabaseConnection;
import dao.AgentDao;
import dao.EquipementDao;
import dao.TypeEquipementDao;

public class AjoutEquipement implements Initializable{
	
	@FXML 
	private TextField prix;
	
	@FXML 
	private TextField numCPAgent;

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
	private DatePicker dateLivraison;
	
	@FXML
	private CheckBox logicielsOuiNon;
	
	@FXML 
	private ListView<Logiciel> lstLogiciel;
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private AnchorPane sectionLogiciel;
	
	private TypeEquipementDao typeEquipementDao;
	
	private AgentDao agentDao;
	
	private FXMLLoader loader;
	
	private String errorMessage = "";
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		sectionLogiciel.setVisible(false);
		
		typeEquipementDao = new TypeEquipementDao();
		agentDao = new AgentDao();
		
		DatabaseConnection.startConnection();
		typeEquipement.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
		DatabaseConnection.closeConnection();
				
		
		logicielsOuiNon.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent me) {
			       if(logicielsOuiNon.isSelected())
			    	   sectionLogiciel.setVisible(true);
			       else
			    	   sectionLogiciel.setVisible(false);
			    }
			});
			
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
		
		boolean formValid = true;
		
		if(typeEquipement.getSelectionModel().getSelectedItem() == null){
			errorMessage += "Type d'�quipement non renseign�.\n";
			formValid = false;
		}
		if(calife.getText().trim().equals("")){
			errorMessage += "Calife non renseign�.\n";
			formValid = false;
		}
		if(prix.getText().trim().equals("")){
			errorMessage += "Valeur non renseign�e.\n";
			formValid = false;
		}
		else{
			if(TransformationDonnees.getDoubleValue(prix) == -1){
				errorMessage += "Valeur saisie incorrecte (mauvais format).\n";
				formValid = false;
			}
		}
		
		if(numCPAgent.getText().trim().equals("")){
			errorMessage += "Agent non renseign�.\n";
			formValid = false;
		}
		
		if(logicielsOuiNon.isSelected()){
			if(lstLogiciel.getItems().isEmpty()){
				errorMessage += "Aucun logiciel associ� � l'�quipement, d�cochez la case.\n";
				formValid = false;
			}
		}
		
		return formValid;
	}
	
	@FXML
	private void enregistrerEquipement(ActionEvent event){
		
		if(validationFormulaire()){
			// todo recupération de l'agent
			System.out.println(typeEquipement.getValue().getNom());
			Equipement newEquipement = new Equipement(typeEquipement.getValue().getNom(), null, TransformationDonnees.getDoubleValue(prix), TransformationDonnees.formatDate(dateGarantie), TransformationDonnees.formatDate(dateLivraison), marque.getText(), modele.getText(), calife.getText(), info.getText());
			EquipementDao equipementDao = new EquipementDao();
			DatabaseConnection.startConnection();
			equipementDao.save(newEquipement);
			DatabaseConnection.closeConnection();
			informerValidation();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur enregistrement equipement");
			alert.setHeaderText("Les champs ci-dessous sont incorrectes ou non renseign�s.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	
	private void informerValidation(){
		viderTousLesChamps();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout equipement");
		alert.setHeaderText(null);
		alert.setContentText("Equipement ajout� avec succ�s !");
		alert.showAndWait();
	}
	
	private void viderTousLesChamps(){	
		marque.clear();	
		modele.clear();	
		calife.clear();	
		prix.clear();
		info.clear();
		numCPAgent.clear();
		typeEquipement.getEditor().clear();
		dateGarantie.getEditor().clear();
		logicielsOuiNon.setSelected(false);
		lstLogiciel.getItems().clear();
	}
}
