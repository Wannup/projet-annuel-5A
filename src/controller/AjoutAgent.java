package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import application.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AjoutAgent implements Initializable{
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML 
	private TextField nom;
	
	@FXML 
	private TextField prenom;
	
	@FXML 
	private ComboBox<String> jour;
	
	@FXML 
	private ComboBox<String> mois;
	
	@FXML 
	private ComboBox<String> annee;
	
	@FXML 
	private TextField numCP;
	
	@FXML 
	private TextField numPoste;
	
	private FXMLLoader loader;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int j=1; j<=31; j++){
			jour.getItems().add(""+j);
		}
		
		for (int m=1; m<=12; m++){
			mois.getItems().add(""+m);
		}
		
		for (int a=Calendar.getInstance().get(Calendar.YEAR) - 10; a >= Calendar.getInstance().get(Calendar.YEAR) - 90; a--){
			annee.getItems().add(""+a);
		}
	}
	
	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionAgent.fxml"));
		bodyPanel.getChildren().setAll(loader.load());
		AnchorPane.setTopAnchor(bodyPanel, (double) 0);
		AnchorPane.setTopAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setRightAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setLeftAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setBottomAnchor(bodyPanel.getChildren().get(0), (double) 0);
	}
	
	private String formatDate(){
		return annee.getValue() + "-" + mois.getValue() + "-" + jour.getValue();
	}
	
	@FXML
	private void enregistrerAgent(ActionEvent event){
		Database.doRequest("INSERT INTO agents (nom, prenom, dateDeNaissance, numCPAgent, numPoste) VALUES ('" + nom.getText() + "', '" + prenom.getText() + "', '" + formatDate() + "', '" + numCP.getText() + "', '" + numPoste.getText() + "')");
	}

}
