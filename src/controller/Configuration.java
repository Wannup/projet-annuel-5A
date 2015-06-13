package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.TypeEquipement;
import tools.Config;
import tools.TransformationDonnees;
import application.database.DatabaseConnection;
import dao.TypeEquipementDao;

public class Configuration implements Initializable {
	
	@FXML
	private CheckBox checkBoxTableauNbLigne;
	
	@FXML
	private TextField textFieldTableauNbLigne;

	@FXML
	private TextField textFieldDatabaseDriver;

	@FXML
	private TextField textFieldDatabaseLocation;

	@FXML
	private TextField textFieldDatabaseUser;

	@FXML
	private TextField textFieldDatabasePassword;
	
	@FXML 
	private TextField textFieldType;
	
	@FXML
	private TextField textFieldNbMonth;
	
	@FXML
	private ListView<TypeEquipement> listType;
	
	private TypeEquipementDao typeEquipementDao;
	
	private String errorMessage = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		typeEquipementDao = new TypeEquipementDao();
		
		listType.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
		
		String limiteTableau = Config.getPropertie("tableau_limite");
		if ("yes".equals(limiteTableau)) {
			checkBoxTableauNbLigne.setSelected(true);
		} else {
			textFieldTableauNbLigne.setDisable(true);
		}
		textFieldTableauNbLigne.setText(Config.getPropertie("tableau_nb_ligne"));
		textFieldDatabaseDriver.setText(Config.getPropertie("db_driver"));
		textFieldDatabaseLocation.setText(Config.getPropertie("db_location"));
		textFieldDatabaseUser.setText(Config.getPropertie("db_user"));
		textFieldDatabasePassword.setText(Config.getPropertie("db_password"));
	}
	
	@FXML
	private void changeTableauLimite(ActionEvent event) throws IOException{		
		if (checkBoxTableauNbLigne.isSelected()) {
			textFieldTableauNbLigne.setDisable(false);
		} else {
			textFieldTableauNbLigne.setDisable(true);
		}
	}
	
	@FXML
	private void saveConfig(ActionEvent event) throws IOException {
		String tableau_limite = checkBoxTableauNbLigne.isSelected() ? "yes" : "no";
		Config.modifyProperties("tableau_limite", tableau_limite);
		Config.modifyProperties("tableau_nb_ligne", textFieldTableauNbLigne.getText());
		Config.modifyProperties("db_driver", textFieldDatabaseDriver.getText());
		Config.modifyProperties("db_location", textFieldDatabaseLocation.getText());
		Config.modifyProperties("db_user", textFieldDatabaseUser.getText());
		Config.modifyProperties("db_password", textFieldDatabasePassword.getText());
	}
	
	@FXML
	private void deleteType(ActionEvent event) throws IOException {
		System.out.println("to do");
	}
	
	@FXML
	private void saveType(ActionEvent event) throws IOException {
		
		
		if(validationFormulaire())	{
			
			TypeEquipement newType = new TypeEquipement(textFieldType.getText().trim(), TransformationDonnees.getIntValue(textFieldNbMonth));
			typeEquipementDao.save(newType);
			listType.getItems().clear();
			listType.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
			
			textFieldType.clear();
			textFieldNbMonth.clear();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ajout type équipement");
			alert.setHeaderText(null);
			alert.setContentText("Nouveau type d'équipement ajouté !");
			alert.showAndWait();
		}
		else{
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur enregistrement type équipement");
			//alert.setHeaderText("Champs mal renseign�s.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
			
	}
	
	private boolean validationFormulaire(){
		
		boolean formValid = true;
		
		if(textFieldType.getText().trim().equals("")){
			errorMessage += "Nom du type non renseign�e.\n";
			formValid = false;
		}	
		
		if(textFieldNbMonth.getText().trim().equals("")){
			errorMessage += "Nombre de mois de renouvellement non renseign�e.\n";
			formValid = false;
		}
		else{
			if(TransformationDonnees.getIntValue(textFieldNbMonth) == -1){
				errorMessage += "Nombre de mois incorrect (mauvais format).\n";
			    formValid = false;
			}
		}
		
		// v�rification non existence du type
		/*
		typeEquipementDao.save(newType);
		*/
		
		return formValid;
	}

}
