package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Agent;
import model.Equipement;
import model.Logiciel;
import model.TypeEquipement;
import tools.ManipInterface;
import tools.TransformationDonnees;
import dao.AgentDao;
import dao.EquipementDao;
import dao.LogicielDao;
import dao.TypeEquipementDao;

public class AjoutEquipement implements Initializable{
	
	@FXML 
	private TextField prix;

	@FXML
	private TextField modele;
	
	@FXML
	private TextField marque;
	
	@FXML
	private TextArea info;
	
	@FXML
	private TextField calife;
	
	@FXML
	private ComboBox<TypeEquipement> typeEquipement;
	
	@FXML
	private DatePicker dateGarantie;
	
	@FXML
	private DatePicker dateLivraison;
	
	@FXML
	private CheckBox logicielsOuiNon;
	
	@FXML 
	private ListView<Logiciel> lstLogiciel;
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private AnchorPane sectionLogiciel;
	
	@FXML
	private ComboBox<Agent> numCPAgent;
	
	private TypeEquipementDao typeEquipementDao;
	private AgentDao agentDao;
	private FXMLLoader loader;
	private String errorMessage = "";	
	private LogicielDao logicielDao;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		sectionLogiciel.setVisible(false);
		
		typeEquipementDao = new TypeEquipementDao();
		agentDao = new AgentDao();
		logicielDao = new LogicielDao();
		
		typeEquipement.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
		numCPAgent.getItems().addAll(FXCollections.observableArrayList(agentDao.findByAttributesLike(null)));	
		lstLogiciel.getItems().addAll(FXCollections.observableArrayList(logicielDao.findByAttributesLike(null)));
		lstLogiciel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		logicielsOuiNon.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent me) {
			       if(logicielsOuiNon.isSelected())
			    	   sectionLogiciel.setVisible(true);
			       else
			    	   sectionLogiciel.setVisible(false);
			    }
			});
			
	}
	
	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}

	@FXML
	private void selectionLogiciels(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Selection des logiciels", FXMLLoader.load(getClass().getResource("/view/RecherchePopup.fxml")));		
	}
	
	@FXML
	private void ajoutLogiciel(ActionEvent event) throws IOException{
		ManipInterface.newWindow("Ajouter un logiciel", FXMLLoader.load(getClass().getResource("/view/AjoutLogicielPopup.fxml")));	
	}
	
	private boolean validationFormulaire(){
		
		boolean formValid = true;
		
		if(typeEquipement.getSelectionModel().getSelectedItem() == null){
			errorMessage += "Type d'équipement non renseigné.\n";
			formValid = false;
		}
		if(calife.getText().trim().equals("")){
			errorMessage += "Calife non renseigné.\n";
			formValid = false;
		}
		if(prix.getText().trim().equals("")){
			errorMessage += "Valeur non renseigné.\n";
			formValid = false;
		}
		else{
			if(TransformationDonnees.getDoubleValue(prix) == -1){
				errorMessage += "Valeur saisie incorrecte (mauvais format).\n";
				formValid = false;
			}
		}
		
		if(numCPAgent.getSelectionModel().getSelectedItem() == null){
			errorMessage += "Agent non renseigné.\n";
			formValid = false;
		}
		
		if(logicielsOuiNon.isSelected()){
			if(lstLogiciel.getItems().isEmpty()){
				errorMessage += "Aucun logiciel associé à  l'équipement, décochez la case.\n";
				formValid = false;
			}
		}
		return formValid;
	}
	
	@FXML
	private void enregistrerEquipement(ActionEvent event){
		
		if(validationFormulaire()){
			// todo recupÃ©ration de l'agent
			
			/*ObservableList<Logiciel> selectedLog =  lstLogiciel.getSelectionModel().getSelectedItems();
            for(Logiciel l : selectedLog){
                
            }*/
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, typeEquipement.getSelectionModel().getSelectedItem().getNbYearRenewal());
			String renewalDate = dateFormat.format(cal.getTime());
			
			Equipement newEquipement = new Equipement(typeEquipement.getSelectionModel().getSelectedItem().toString(), numCPAgent.getSelectionModel().getSelectedItem(), TransformationDonnees.getDoubleValue(prix), TransformationDonnees.formatDate(dateGarantie), TransformationDonnees.formatDate(dateLivraison), marque.getText(), modele.getText(), calife.getText(), info.getText(), renewalDate);
			EquipementDao equipementDao = new EquipementDao();
			equipementDao.save(newEquipement);
			informerValidation();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur enregistrement equipement");
			alert.setHeaderText("Les champs ci-dessous sont incorrectes ou non renseignés.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	
	private void informerValidation(){
		viderTousLesChamps();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout equipement");
		alert.setHeaderText(null);
		alert.setContentText("Equipement ajouté avec succès !");
		alert.showAndWait();
	}
	
	private void viderTousLesChamps(){	
		marque.clear();	
		modele.clear();	
		calife.clear();	
		prix.clear();
		info.clear();
		numCPAgent.getSelectionModel().clearSelection();
		typeEquipement.getSelectionModel().clearSelection();
		dateGarantie.getEditor().clear();
		dateLivraison.getEditor().clear();
		logicielsOuiNon.setSelected(false);
		lstLogiciel.getSelectionModel().clearSelection();
		numCPAgent.getEditor().clear();
	}
}