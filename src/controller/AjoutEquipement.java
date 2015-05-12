package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Logiciel;
import tools.ManipInterface;

public class AjoutEquipement implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private Button btnSelectAgent;
	
	@FXML
	private Button btnSelectLogiciels;
	
	@FXML
	private Button btnEnregistrer;
	
	@FXML
	private Button btnEditDelete;
	
	@FXML
	private TextField numPoste;
	
	@FXML 
	private TextField prix;
	
	@FXML 
	private TextField numCPAgent;
	
	@FXML
	private TextField nbJourPrev;
	
	@FXML 
	private ListView<Logiciel> lstLogiciel;
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private AnchorPane sectionOrdinateur;
	
	@FXML
	private ChoiceBox<String> typeEquipement;
	
	private FXMLLoader loader;
	
	@FXML
	private void selectionAgent(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Selection de l'agent", FXMLLoader.load(getClass().getResource("/view/RecherchePopup.fxml")));
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
		System.out.println("Ouvre une fenetre pour ajouter un nouveau logiciel");
		ManipInterface.newWindow("Ajouter un logiciel", FXMLLoader.load(getClass().getResource("/view/AjoutLogicielPopup.fxml")));	
	}

	@FXML
	private void ajoutAgent(ActionEvent event) throws IOException{
		System.out.println("Ouvre une fenetre pour ajouter un nouvel agent");
		ManipInterface.newWindow("Ajouter un agent", FXMLLoader.load(getClass().getResource("/view/AjoutAgentPopup.fxml")));	
	}
	
	private boolean validationFormulaire(){
		//Todo
		return true;
	}
	
	@FXML
	private void enregistrerEquipement(ActionEvent event){
		/*
		 * if(formulaireValide())
		  		appel d'une fonction dans le model "Equipement" pour faire l'insertion
		 */
	
		//Database.doRequest("INSERT INTO equipements (numPoste, prix, numCPAgent) VALUES ('" + numPoste.getText() + "', '" + prix.getText() + "', '" + numCPAgent.getText() + "')");
		if(validationFormulaire()){
			
		}
	}
	
}
