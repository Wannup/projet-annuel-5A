package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import tools.ManipInterface;
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

public class AjoutEquipement implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// initialisé choicebox avec les types d'équipements
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
	private void selectionAgent(ActionEvent event){
		System.out.println("Clic selection Agent: todo");
	}
	
	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}

	@FXML
	private void selectionLogiciels(ActionEvent event){
		System.out.println("Clic selection Logiciel: todo");
	}
	
	@FXML
	private void enregistrerEquipement(ActionEvent event){
		/*
		 * if(formulaireValide())
		 * 		appel d'une fonction dans le model "Equipement" pour faire l'insertion
		 */
		//Database.doRequest("INSERT INTO equipements (numPoste, prix, numCPAgent) VALUES ('" + numPoste.getText() + "', '" + prix.getText() + "', '" + numCPAgent.getText() + "')");
	}
	
	/*private boolean formulaireValide(){
		return false;
		// TODO
	}*/
}
