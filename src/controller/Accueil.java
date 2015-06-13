package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import tools.ManipInterface;
import application.database.export.DatabaseExport;
import application.database.importer.DatabaseImport;

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
	private AnchorPane bodyPanel;

	private FXMLLoader loader;
	
	@FXML
	private void agentGest(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionAgent.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void equipementGest(ActionEvent event) throws IOException{		
		loader = new FXMLLoader(getClass().getResource("/view/GestionEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void consultEquip(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/ConsultationEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void logicielGest(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionLogiciel.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void statPrev(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/StatEtPrevision.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void configuration(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/Configuration.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void exportDatabase(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Zip", "*.zip"));
		fileChooser.setInitialFileName("database");
        fileChooser.setTitle("Export database");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	DatabaseExport databaseExport = new DatabaseExport();
        	databaseExport.exportDatabase(file);
        }
	}
	
	@FXML
	private void importDatabase(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Zip", "*.zip"));
        fileChooser.setTitle("Import database");
        File file;
        file = fileChooser.showOpenDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	DatabaseImport databaseImport = new DatabaseImport();
        	databaseImport.importDatabase(file);
        	//DatabaseConnection.refresh();
        }
	}
	
	
}
