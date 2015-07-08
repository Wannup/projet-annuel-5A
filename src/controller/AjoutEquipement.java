package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Agent;
import model.Equipement;
import tools.ManipInterface;
import tools.TransformationDonnees;

/**
 * class controller for the interface AjoutEquipement.fxml
 * @author: Erwan LE GUYADER
 * @version 1.0
 * */
public class AjoutEquipement extends EditEquipement implements Initializable{
	
	@FXML
	private AnchorPane bodyPanel;
	private FXMLLoader loader;
	
	/**
	 * function associate with the button "Modifier/Supprimer des equipements"
	 * */
	@FXML
	private void displayEditDelete() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	/**
	 * function associate with the button "Enregistrer"
	 * */
	@FXML
	private void enregistrerEquipement(){
		
		errorMessage = "";
		Agent agent = null;
		
		// récupération de l'agent si renseigné
		if(!numCPAgent.getText().trim().equals("")){
			Map<String, String> attribut = new HashMap<String, String>();
			attribut.put("numCP", numCPAgent.getText().trim());
			agent = agentDao.findByAttributesEquals(attribut).get(0);
		}
		
		if(validationFormulaire(agent)){
			
			// calcul de la date prévisionnelle de renouvellement
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			if(!TransformationDonnees.formatDate(dateLivraison).equals("")){
				try {
					Date dateLivr = dateFormat.parse(TransformationDonnees.formatDate(dateLivraison));
					calendar.setTime(dateLivr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			calendar.add(Calendar.YEAR, typeEquipement.getSelectionModel().getSelectedItem().getNbYearRenewal());
			String renewalDate = dateFormat.format(calendar.getTime());
			
			// Création de l'équipement
			Equipement newEquipement;
			newEquipement = new Equipement(typeEquipement.getSelectionModel().getSelectedItem(), agent, poles.getSelectionModel().getSelectedItem(), TransformationDonnees.getDoubleValue(prix), TransformationDonnees.formatDate(dateGarantie), TransformationDonnees.formatDate(dateLivraison), renewalDate, marque.getText(), modele.getText().trim(), calife.getText().trim(), info.getText().trim());
			equipementDao.save(newEquipement);
			
			if(!lstLogiciel.getItems().isEmpty()){
				for(int i=0; i<lstLogiciel.getItems().size(); i++){
					lstLogiciel.getItems().get(i).addEquipement(newEquipement);
					logicielDao.update(lstLogiciel.getItems().get(i));
				}
				
				newEquipement.setLogiciels(lstLogiciel.getItems());
				equipementDao.update(newEquipement);
			}

			// ajout dans la liste des équipements de l'agent si renseigné
			if(!numCPAgent.getText().trim().equals("")){
				agent.addEquipement(newEquipement);
				agentDao.update(agent);
			}
			
			
			informerValidation();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur enregistrement equipement");
			alert.setHeaderText("Les erreurs sont les suivantes: ");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	
	/**
	 * override function verificationCalife
	 * @return true if "calife" is ok
	 * */
	protected boolean verificationCalife(){
		
		if(calife.getText().trim().equals("")){
			errorMessage += "Calife non renseigné.\n";
			return false;
		}
		
		// vérification s'il n'existe pas déjà un équipement avec même calife
		if(!calife.getText().trim().equals("")){
			Map<String, String> attribut = new HashMap<String, String>();
			attribut.put("nomCalife", calife.getText().trim());
								
			if(!equipementDao.findByAttributesEquals(attribut).isEmpty()){
				errorMessage += "Il y a déjà un équipement enregistré avec ce nom de calife.\n";
				return false;
			}
		}
		return true;
	}
	
	/**
	 * function call is the save of the new "Equipement" is ok.
	 * Inform the user of the save.
	 * */
	private void informerValidation(){
		viderTousLesChamps();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout equipement");
		alert.setHeaderText(null);
		alert.setContentText("Equipement ajouté avec succès !");
		alert.showAndWait();
	}
	
	/**
	 * clear the form
	 * */
	private void viderTousLesChamps(){	
		marque.clear();	
		modele.clear();	
		calife.clear();	
		prix.clear();
		info.clear();
		numCPAgent.clear();
		typeEquipement.getSelectionModel().clearSelection();
		dateGarantie.getEditor().clear();
		dateLivraison.getEditor().clear();
		lstLogiciel.getItems().clear();
		poles.getSelectionModel().clearSelection();
		
	}
	
}