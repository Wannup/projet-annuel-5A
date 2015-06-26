package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.pdf.export.PDFEquipementExport;
import application.pdf.export.PDFGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Equipement;
import model.Logiciel;

/**
 * class controller for the interface InformationEquipementPopup.fxml
 * */
public class InformationEquipement implements Initializable{

	@FXML
	Parent root;
	
	@FXML
	private Label type;
	
	@FXML
	private Label marque;
	
	@FXML
	private Label modele;
	
	@FXML
	private Label calife;
	
	@FXML
	private Label nom;
	
	@FXML
	private Label prenom;
	
	@FXML
	private Label cp;
	
	@FXML
	private TextArea info;
	
	@FXML
	private Button ok;
	
	@FXML
	private Label dateLivraison;
	
	@FXML
	private ListView<Logiciel> logiciels;
	
	private Equipement equipement;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}

	@FXML
	private void validate(){
		Stage stage = (Stage) ok.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void editEquipement() throws IOException{		
		Stage stage = new Stage();
		FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/EditEquipement.fxml"));
		Parent root;
		root = (Parent)fxmlLoader.load();
		EditEquipement controller = fxmlLoader.<EditEquipement>getController();
		controller.setValues(equipement);
		Scene scene = new Scene(root); 
	    stage.setScene(scene);    
	    stage.show(); 
	    validate();	
	}
	
	public void setValues(Equipement equipementSelect){

		this.equipement = equipementSelect;
		
		this.type.setText(equipement.getTypeEquipement().getNom());
		this.marque.setText(equipement.getMarque());
		this.modele.setText(equipement.getModele());
		this.calife.setText(equipement.getNomCalife());
		this.info.setText(equipement.getInfo());
		this.dateLivraison.setText(equipement.getDateLivraison());
		this.logiciels.getItems().addAll(equipement.getLogiciels());
		
		if(equipement.getAgent() != null){
			this.nom.setText(equipement.getAgent().getNom());
			this.prenom.setText(equipement.getAgent().getPrenom());
			this.cp.setText(equipement.getAgent().getNumCP());
		}
	}

	@FXML
	private void exportEquipement(ActionEvent event) throws IOException {
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("Equipement - " + equipement.getNomCalife());
		fileChooser.setTitle("Save PDF");
		File file;
		file = fileChooser.showSaveDialog(root.getScene().getWindow());
		if (file != null) {
			pdfGenerator.generate(file, new PDFEquipementExport(this.equipement));
		}
	}
}
