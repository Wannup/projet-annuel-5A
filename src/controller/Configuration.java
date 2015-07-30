package controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import application.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Pole;
import model.TypeEquipement;
import tools.Config;
import tools.TransformationDonnees;
import dao.EquipementDao;
import dao.PoleDao;
import dao.TypeEquipementDao;
/**
 * class controller for the interfaces Configuration.fxml
 * @author: Mike FIARI
 * @version 1.0
 * */
public class Configuration implements Initializable {

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
	private Button btnAddOrUpdatePole;
	
	@FXML
	private TextField nomPole;
	
	@FXML
	private ListView<TypeEquipement> listType;
	
	@FXML
	private ListView<Pole> listPole;
	
	private TypeEquipementDao typeEquipementDao;
	private EquipementDao equipementDao;
	private PoleDao poleDao;
	private Pole pole;
	
	private TypeEquipement typeEquipement;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		typeEquipementDao = new TypeEquipementDao();
		equipementDao = new EquipementDao();
		poleDao = new PoleDao();
		
		listType.setItems(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
		listPole.setItems(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
		
		getConfigurationBdd();
		
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
		
		listPole.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		            	pole = listPole.getSelectionModel().getSelectedItem();
		            	nomPole.setText(pole.getNom());
		            	btnAddOrUpdatePole.setText("Modifier");
		            }
		        }
		    }
		});
	}
	
	/**
	 * function associate with the button "Enregistrement"
	 * */
	@FXML
	private void saveConfig(){
		
		if(!textFieldDatabaseDriver.getText().trim().equals("") && !textFieldDatabaseLocation.getText().trim().equals("") && !textFieldDatabaseUser.getText().trim().equals("")){
			Config.modifyProperties("javax.persistence.jdbc.driver", textFieldDatabaseDriver.getText().trim());
			Config.modifyProperties("javax.persistence.jdbc.url", textFieldDatabaseLocation.getText().trim());
			Config.modifyProperties("javax.persistence.jdbc.user", textFieldDatabaseUser.getText().trim());
			Config.modifyProperties("javax.persistence.jdbc.password", textFieldDatabasePassword.getText().trim());
			
			DatabaseConnection.closeConnection();
			
			try{		
				DatabaseConnection.startConnection();
			}
	    	catch(Exception e){
	    		Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Mauvaise configuration de la base de données");
				alert.setContentText("Vérifier le paramétrage de la base de données. \n (Retour au paramétrage par défaut)");
				alert.showAndWait();
				
				Config.goToDefaultConfig();
				getConfigurationBdd();
				DatabaseConnection.startConnection();
	    	}
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur changement configuration");
			alert.setContentText("Seul le mot de passe peut ne pas être renseigné.");
			alert.showAndWait();
		}
		
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
	
	@FXML
	private void deletePole(){
		
		Pole pole = listPole.getSelectionModel().getSelectedItem();
		if(pole != null){
			if(!equipementDao.getEquipementByPole(pole).isEmpty()){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur suppression du pole");
				alert.setContentText("Il y a des équipements enregistrés avec ce pole.");
				alert.showAndWait();
			}
			else{
				poleDao.remove(pole);
				refreshListPole();
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
	
	@FXML
	private void saveOrUpdatePole(){
		
		// modifier
		if(pole != null){
			if(formPoleIsValid() && updatePoleControlIsOk()){
				pole.setNom(nomPole.getText().trim());
				poleDao.update(pole);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Modification du pole");
				alert.setHeaderText(null);
				alert.setContentText("Pole modifié avec succès.");
				alert.showAndWait();
			}
			btnAddOrUpdate.setText("Ajouter");
		}
		//ajouter
		else{
		   if(formPoleIsValid() && poleNotExist()){
			   pole = new Pole(nomPole.getText().trim());
			   poleDao.save(pole);
		   }
		   else{
			   Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur ajout type");
				alert.setContentText("Pole existant ou saisie incorrecte.");
				alert.showAndWait();
		   }
		}
		
		pole = null;
		nomPole.clear();
		
		refreshListPole();
	}
	
	private void getConfigurationBdd() {
		textFieldDatabaseDriver.setText(Config.getProperty("javax.persistence.jdbc.driver"));
		textFieldDatabaseLocation.setText(Config.getProperty("javax.persistence.jdbc.url"));
		textFieldDatabaseUser.setText(Config.getProperty("javax.persistence.jdbc.user"));
		textFieldDatabasePassword.setText(Config.getProperty("javax.persistence.jdbc.password"));
	}
	
	private void refreshListPole(){
		listPole.getItems().clear();
		listPole.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
	}
	
	private boolean updatePoleControlIsOk(){
		return (!pole.getNom().trim().equals(nomPole.getText().trim()));
	}
	
	private boolean formPoleIsValid(){
		return (!nomPole.getText().trim().equals(""));
	}
	
	private boolean poleNotExist(){
		Map<String, String> attribut = new HashMap<String, String>();
		attribut.put("nom", nomPole.getText().trim());
		return(poleDao.findByAttributesEquals(attribut).isEmpty());
	}
}
