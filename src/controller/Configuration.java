package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.TypeEquipement;
import tools.Config;
import tools.TransformationDonnees;
import dao.EquipementDao;
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
	private TextField nomType;
	
	@FXML
	private TextField nbYearRenew;
	
	@FXML
	private Button btnAddOrUpdate;
	
	@FXML
	private ListView<TypeEquipement> listType;
	
	private TypeEquipementDao typeEquipementDao;
	private EquipementDao equipementDao;
	
	private TypeEquipement typeEquipement;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		typeEquipementDao = new TypeEquipementDao();
		equipementDao = new EquipementDao();
		
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
		
		listType.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		            	typeEquipement = listType.getSelectionModel().getSelectedItem();
		            	nomType.setText(typeEquipement.getNom());
		            	nbYearRenew.setText(String.valueOf(typeEquipement.getNbYearRenewal()));
		            	btnAddOrUpdate.setText("Modifier");
		            }
		        }
		    }
		});
	}
	
	@FXML
	private void changeTableauLimite(){		
		if (checkBoxTableauNbLigne.isSelected()) {
			textFieldTableauNbLigne.setDisable(false);
		} else {
			textFieldTableauNbLigne.setDisable(true);
		}
	}
	
	@FXML
	private void saveConfig(){
		String tableau_limite = checkBoxTableauNbLigne.isSelected() ? "yes" : "no";
		Config.modifyProperties("tableau_limite", tableau_limite);
		Config.modifyProperties("tableau_nb_ligne", textFieldTableauNbLigne.getText());
		Config.modifyProperties("db_driver", textFieldDatabaseDriver.getText());
		Config.modifyProperties("db_location", textFieldDatabaseLocation.getText());
		Config.modifyProperties("db_user", textFieldDatabaseUser.getText());
		Config.modifyProperties("db_password", textFieldDatabasePassword.getText());
	}
	
	@FXML
	private void deleteType(){
		
		TypeEquipement typeEquip = listType.getSelectionModel().getSelectedItem();
		if(typeEquip != null){
			if(!equipementDao.getEquipementByType(typeEquip).isEmpty()){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur suppression du type");
				alert.setContentText("Il y a des équipements enregistrés avec ce type.");
				alert.showAndWait();
			}
			else{
				typeEquipementDao.remove(typeEquip);
				refreshListType();
			}
		}
		else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Erreur action suppression");
			alert.setHeaderText(null);
			alert.setContentText("Veuillez sélectionner un élément dans la liste pour la suppression !");
			alert.showAndWait();
		}
	}
	
	private void refreshListType(){
		listType.getItems().clear();
		listType.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
	}
	
	@FXML
	private void saveOrUpdateType(){
		
		// modifier
		if(typeEquipement != null){
			if(formIsValid() && updateControlIsOk()){
				typeEquipement.setNom(nomType.getText().trim());
				typeEquipement.setNbYearRenewal(TransformationDonnees.getIntValue(nbYearRenew));
				typeEquipementDao.update(typeEquipement);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Modification type equipement");
				alert.setHeaderText(null);
				alert.setContentText("Type equipement modifié avec succès.");
				alert.showAndWait();
			}
			btnAddOrUpdate.setText("Ajouter");
		}
		//ajouter
		else{
		   if(formIsValid() && typeEquipementNotExist()){
			   typeEquipement = new TypeEquipement(nomType.getText().trim(), TransformationDonnees.getIntValue(nbYearRenew));
			   typeEquipementDao.save(typeEquipement);
		   }
		   else{
			   Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur ajout type");
				alert.setContentText("Type existant ou saisie incorrecte.");
				alert.showAndWait();
		   }
		}
		
		typeEquipement = null;
		nomType.clear();
		nbYearRenew.clear();
		
		refreshListType();
	}
	
	private boolean updateControlIsOk(){
		return (!typeEquipement.getNom().trim().equals(nomType.getText().trim()) || TransformationDonnees.getIntValue(nbYearRenew) != typeEquipement.getNbYearRenewal());
	}
	
	private boolean formIsValid(){
		return (!nomType.getText().trim().equals("") && TransformationDonnees.getIntValue(nbYearRenew) != -1);
	}
	
	private boolean typeEquipementNotExist(){
		Map<String, String> attribut = new HashMap<String, String>();
		attribut.put("nom", nomType.getText().trim());
		return(typeEquipementDao.findByAttributesEquals(attribut).isEmpty());
	}
}
