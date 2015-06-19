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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.TypeEquipement;
import tools.TransformationDonnees;
import dao.TypeEquipementDao;

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
	
	//private String errorMessage = "";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		typeEquipementDao = new TypeEquipementDao();
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
		//TODO
	}
	
	@FXML
	private void saveOrUpdateType(ActionEvent event){
		
		// modifier
		if(typeEquipement != null){
			if(formIsValid() && updateControlIsOk())
				typeEquipement.setNom(nomType.getText().trim());
				typeEquipement.setNbYearRenewal(TransformationDonnees.getIntValue(nbYearRenew));
				typeEquipementDao.update(typeEquipement);	
			
			btnAddOrUpdate.setText("Ajouter");
		}
		//ajouter
		else{
		   if(formIsValid() && typeEquipementNotExist()){
			   typeEquipement = new TypeEquipement(nomType.getText().trim(), TransformationDonnees.getIntValue(nbYearRenew));
			   typeEquipementDao.save(typeEquipement);
		   }
		}
		
		typeEquipement = null;
		nomType.clear();
		nbYearRenew.clear();
		
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
