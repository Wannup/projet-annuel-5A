package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Database;
import model.Logiciel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
	private TextField numPoste;
	
	@FXML 
	private TextField prix;
	
	@FXML 
	private TextField numCPAgent;
	
	@FXML 
	private ListView<Logiciel> lstLogiciel;
	
	@FXML
	private void selectionAgent(ActionEvent event){
		System.out.println("Clic selection Agent: todo");
	}

	@FXML
	private void selectionLogiciels(ActionEvent event){
		System.out.println("Clic selection Logiciel: todo");
	}
	
	@FXML
	private void enregistrerEquipement(ActionEvent event){
		//System.out.println("ajout d'équipement");
		Database.doRequest("INSERT INTO equipements (numPoste, prix, numCPAgent) VALUES ('" + numPoste.getText() + "', '" + prix.getText() + "', '" + numCPAgent.getText() + "')");
	}
}
