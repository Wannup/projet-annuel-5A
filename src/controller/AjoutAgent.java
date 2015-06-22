package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Agent;
import model.Pole;
import tools.ManipInterface;
import dao.AgentDao;
import dao.PoleDao;

public class AjoutAgent implements Initializable{
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML 
	private TextField nom;
	
	@FXML 
	private TextField prenom;
	
	@FXML 
	private TextField numCP;
	
	@FXML 
	private TextField tel;
	
	@FXML
	private ComboBox<Pole> poles;
	
	@FXML
	private Button btnAdd;
	
	public TextField champAgentFormEquipement;
	public ComboBox<Pole> champPolesEquipement;
	
	private FXMLLoader loader;
	
	private PoleDao poleDao;
	private AgentDao agentDao;
	private String errorMessage = "";
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		
		poleDao = new PoleDao();
		agentDao = new AgentDao();
		
		poles.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
	}
	
	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionAgent.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void enregistrerAgent(ActionEvent event){
		
		errorMessage = "";
		
		if(validationFormulaire()){
			Agent newAgent = new Agent(nom.getText(), prenom.getText(), poles.getValue(), tel.getText(), numCP.getText());
			agentDao.save(newAgent);
			
			//popup
			if(champAgentFormEquipement != null){
				champAgentFormEquipement.setText(numCP.getText());
				champPolesEquipement.setValue(newAgent.getPole());
				Stage fenetre =(Stage)btnAdd.getScene().getWindow();
           	 	fenetre.close();
			}
			//fenetre principal
			else
				informerValidation();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur enregistrement agent");
			alert.setHeaderText("Les champs ci-dessous sont incorrectes ou non renseignés.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
		
	}
	
	@FXML
	private void addPole(ActionEvent event) throws IOException{
		Stage stage = new Stage();
        stage.setTitle("Pole/Service");
        stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/PolePopup.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
      
        PolePopup controllerSelectLogicielPopup = (PolePopup) fxmlLoader.getController();
        
        // liaison entre les deux fenetres
        controllerSelectLogicielPopup.champPoleFormEquipement = poles;
	}
	
	private boolean validationFormulaire(){
		
		boolean formValid = true;
		
		if(!numCP.getText().trim().equals("")){
			Map<String, String> attribut = new HashMap<String, String>();
			attribut.put("numCP", numCP.getText().trim());
			if(!agentDao.findByAttributesEquals(attribut).isEmpty()){
				errorMessage += "Il y a déja un agent enregistré avec ce N° CP.\n";
				return false;
			}
		}
		
		if(numCP.getText().trim().equals("")){
			errorMessage += "N° CP non renseigné.\n";
			formValid = false;
		}
		

		if(nom.getText().trim().equals("")){
			errorMessage += "Nom non renseigné.\n";
			formValid = false;
		}
		
		if(prenom.getText().trim().equals("")){
			errorMessage += "Prénom non renseigné.\n";
			formValid = false;
		}
		
		if(poles.getSelectionModel().getSelectedItem() == null){
			errorMessage += "Pole/service non renseigné.\n";
			formValid = false;
		}
	
		
		return formValid;
	}
	
	public void informerValidation(){
		viderTousLesChamps();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout agent");
		alert.setHeaderText(null);
		alert.setContentText("Agent ajouté avec succès !");
		alert.showAndWait();
	}
	
	private void viderTousLesChamps(){
		nom.clear();
		prenom.clear();
		tel.clear();
		numCP.clear();
		poles.getSelectionModel().clearSelection();
	}
	
}
