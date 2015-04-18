package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Accueil implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private Button btnAgentGest;
	
	@FXML
	private Button btnConsultEquip;
	
	@FXML
	private Button btnAjoutEquip;
	
	@FXML 
	private Button btnLogicielGest;
	
	@FXML
	private Button btnStatPrev;
	
	@FXML
	private void agentGest(ActionEvent event) throws IOException{
		Parent agent_gest_page = FXMLLoader.load(getClass().getResource("/view/GestionAgent.fxml"));
		Scene agent_gest_scene = new Scene(agent_gest_page);
		Stage app_stage = new Stage();
		app_stage.setScene(agent_gest_scene);
		persoStage(app_stage);
		app_stage.show();
	}
	
	@FXML
	private void ajoutEquip(ActionEvent event) throws IOException{
		Parent ajout_equip_page = FXMLLoader.load(getClass().getResource("/view/AjoutEquipement.fxml"));
		Scene ajout_equip_scene = new Scene(ajout_equip_page);
		Stage app_stage = new Stage();
		app_stage.setScene(ajout_equip_scene);
		persoStage(app_stage);
		app_stage.show();
	}
	
	@FXML
	private void consultEquip(ActionEvent event) throws IOException{
		Parent consult_equip_page = FXMLLoader.load(getClass().getResource("/view/ConsultationEquipement.fxml"));
		Scene consult_equip_scene = new Scene(consult_equip_page);
		Stage app_stage = new Stage();
		app_stage.setScene(consult_equip_scene);
		persoStage(app_stage);
		app_stage.show();
	}
	
	@FXML
	private void logicielGest(ActionEvent event) throws IOException{
		Parent lociciel_gest_page = FXMLLoader.load(getClass().getResource("/view/GestionLogiciel.fxml"));
		Scene logiciel_gest_scene = new Scene(lociciel_gest_page);
		Stage app_stage = new Stage();
		app_stage.setScene(logiciel_gest_scene);
		persoStage(app_stage);
		app_stage.show();
	}
	
	@FXML
	private void statPrev(ActionEvent event) throws IOException{
		Parent stat_prev_page = FXMLLoader.load(getClass().getResource("/view/StatEtPrevision.fxml"));
		Scene stat_prev_scene = new Scene(stat_prev_page);
		Stage app_stage = new Stage();
		app_stage.setScene(stat_prev_scene);
		persoStage(app_stage);
		app_stage.show();
	}
	
	private void persoStage(Stage app_stage){
		app_stage.setTitle("LGPI SNCF");
		app_stage.getIcons().add(new Image(getClass().getResourceAsStream("../res/icon-sncf.jpg")));
		app_stage.setResizable(false);
	}
}
