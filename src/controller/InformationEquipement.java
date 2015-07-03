package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Equipement;
import model.Logiciel;
import application.pdf.export.PDFEquipementExport;
import application.pdf.export.PDFGenerator;

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
	private Label dateRenewal;
	
	@FXML
	private Label service;
	
	@FXML
	private ListView<Logiciel> logiciels;
	
	@FXML
	private Label dateEndWarranty;
	
	@FXML
	private Label price;
	
	private Equipement equipement;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	private void validate(){
		Stage stage = (Stage) ok.getScene().getWindow();
	    stage.close();
	}
	
	public void setValues(Equipement equipementSelect){

		equipement = equipementSelect;
		
		type.setText(equipement.getTypeEquipement().getNom());
		marque.setText(equipement.getMarque());
		modele.setText(equipement.getModele());
		calife.setText(equipement.getNomCalife());
		info.setText(equipement.getInfo());
		dateLivraison.setText(equipement.getDateLivraison());
		logiciels.setItems(FXCollections.observableArrayList(equipement.getLogiciels()));
		dateRenewal.setText(equipement.getRenewalDate());
		service.setText(equipement.getPole().getNom());
		dateEndWarranty.setText(equipement.getDateGarantie());
		price.setText(String.valueOf(equipement.getPrix()));
		
		if(equipement.getAgent() != null){
			nom.setText(equipement.getAgent().getNom());
			prenom.setText(equipement.getAgent().getPrenom());
			cp.setText(equipement.getAgent().getNumCP());
		}
	}

	@FXML
	private void exportEquipement(){
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("Equipement - " + equipement.getNomCalife());
		fileChooser.setTitle("Save PDF");
		File file;
		file = fileChooser.showSaveDialog(root.getScene().getWindow());
		if (file != null) {
			pdfGenerator.generate(file, new PDFEquipementExport(equipement));
		}
	}
}
