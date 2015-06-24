package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import tools.ManipInterface;
import application.database.DatabaseConnection;
import application.database.export.DatabaseExport;
import application.database.importer.DatabaseImport;

public class Accueil implements Initializable{

	@FXML
	private AnchorPane bodyPanel;

	private FXMLLoader loader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			loader = new FXMLLoader(getClass().getResource("/view/bodyPanelAccueil.fxml"));
			ManipInterface.chargementBodyPanel(bodyPanel, loader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@FXML
	private void agentGest() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionAgent.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void equipementGest() throws IOException{		
		loader = new FXMLLoader(getClass().getResource("/view/GestionEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void consultEquip() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/ConsultationEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void logicielGest() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionLogiciel.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void statPrev() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/StatEtPrevision.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void configuration() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/Configuration.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void exportDatabase() throws IOException {
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
	private void importDatabase() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Zip", "*.zip"));
        fileChooser.setTitle("Import database");
        File file;
        file = fileChooser.showOpenDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	DatabaseImport databaseImport = new DatabaseImport();
        	databaseImport.importDatabase(file);
        	DatabaseConnection.refresh();
        }
	}
	
	
}
