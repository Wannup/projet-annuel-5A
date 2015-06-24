package controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.TypeEquipement;
import tools.TransformationDonnees;
import dao.EquipementDao;
import dao.TypeEquipementDao;

/**
 * class controller for the interface TypeEquipementPopup.fxml
 * */
public class TypeEquipementPopup implements Initializable{

	@FXML
	private ListView<TypeEquipement> listType;
	
	@FXML
	private TextField nomType;
	
	@FXML
	private TextField nbYearRenew;
	
	@FXML
	private Button btnAddOrUpdate;
	
	public ComboBox<TypeEquipement> champTypeEquipFormEquipement;
	
	private TypeEquipementDao typeEquipementDao;
	private TypeEquipement typeEquipement;
	private EquipementDao equipementDao;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		typeEquipementDao = new TypeEquipementDao();
		equipementDao = new EquipementDao();
		listType.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
		
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
	private void deleteType(ActionEvent event){
		
		TypeEquipement typeEquip = listType.getSelectionModel().getSelectedItem();
		if(typeEquip != null){
			if(!equipementDao.getEquipementByType(typeEquip).isEmpty()){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur suppression du type");
				alert.setContentText("Il y a des �quipements enregistr�s avec ce type.");
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
			alert.setContentText("Veuillez s�lectionner un �l�ment dans la liste pour la suppression !");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void saveOrUpdateType(ActionEvent event){
		
		// modifier
		if(typeEquipement != null){
			if(formIsValid() && updateControlIsOk()){
				typeEquipement.setNom(nomType.getText().trim());
				typeEquipement.setNbYearRenewal(TransformationDonnees.getIntValue(nbYearRenew));
				typeEquipementDao.update(typeEquipement);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Modification type equipement");
				alert.setHeaderText(null);
				alert.setContentText("Type equipement modifi� avec succ�s.");
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
	
	private void refreshListType(){
		listType.getItems().clear();
		listType.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
		champTypeEquipFormEquipement.getItems().clear();
		champTypeEquipFormEquipement.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));	
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
