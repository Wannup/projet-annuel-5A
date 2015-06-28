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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Pole;
import dao.EquipementDao;
import dao.PoleDao;

/**
 * class controller for the interface PolePopup.fxml
 * */
public class PolePopup implements Initializable{

	@FXML
	private ListView<Pole> listPole;
	
	@FXML
	private TextField nomPole;
	
	@FXML
	private Button btnAddOrUpdate;
	
	public ComboBox<Pole> champPoleFormEquipement;
	
	private PoleDao poleDao;
	private Pole pole;
	private EquipementDao equipementDao;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		poleDao = new PoleDao();
		equipementDao = new EquipementDao();
		listPole.setItems(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
		
		listPole.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		            	pole = listPole.getSelectionModel().getSelectedItem();
		            	nomPole.setText(pole.getNom());
		            	btnAddOrUpdate.setText("Modifier");
		            }
		        }
		    }
		});
	}

	@FXML
	private void deletePole(ActionEvent event){
		
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
	private void saveOrUpdatePole(ActionEvent event){
		
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
	
	private void refreshListPole(){
		listPole.getItems().clear();
		listPole.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
		champPoleFormEquipement.getItems().clear();
		champPoleFormEquipement.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));	
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
