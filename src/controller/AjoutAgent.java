package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	private ComboBox<Pole> pole;
	
	@FXML
	private Button btnAdd;
	
	public TextField champAgentFormEquipement;
	
	private FXMLLoader loader;
	
	private PoleDao poleDao;
	private String errorMessage = "";
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		
		poleDao = new PoleDao();
		pole.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
	}
	
	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionAgent.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void enregistrerAgent(ActionEvent event){
		if(validationFormulaire()){
			Agent newAgent = new Agent(nom.getText(), prenom.getText(), pole.getValue(), tel.getText(), numCP.getText());
			AgentDao agentDao = new AgentDao();
			agentDao.save(newAgent);
			
			//popup
			if(champAgentFormEquipement != null){
				champAgentFormEquipement.setText(numCP.getText());
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
	
	private boolean validationFormulaire(){
		//Todo
		//ajouter un controle sur le numeroCP pour voir si l'agent n'est pas deja enregistrÃ©
		return true;
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
		pole.getEditor().clear();
	}
	
	@FXML
	private void selectionPoste(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Selection d'un poste", FXMLLoader.load(getClass().getResource("/view/RecherchePopup.fxml")));
	}
}
