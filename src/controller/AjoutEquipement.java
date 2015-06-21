package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Agent;
import model.Equipement;
import model.Logiciel;
import model.Pole;
import model.TypeEquipement;
import tools.ManipInterface;
import tools.TransformationDonnees;
import dao.AgentDao;
import dao.EquipementDao;
import dao.PoleDao;
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
	private ListView<Logiciel> lstLogiciel;
	
	@FXML
	private ComboBox<Pole> poles;
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private TextField numCPAgent;
	
	private TypeEquipementDao typeEquipementDao;
	private EquipementDao equipementDao;
	
	private FXMLLoader loader;
	private String errorMessage = "";	
	private AgentDao agentDao;
	private PoleDao poleDao;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		typeEquipementDao = new TypeEquipementDao();
		equipementDao = new EquipementDao();
		agentDao = new AgentDao();
		poleDao = new PoleDao();
		
		poles.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
		typeEquipement.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
	}
	
	@FXML
	private void displayEditDelete(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void addPole(ActionEvent event) throws IOException{
		//TODO
	}
	
	@FXML
	private void ajoutAgent(ActionEvent event) throws IOException{
		
		numCPAgent.clear();
		
		Stage stage = new Stage();
        stage.setTitle("Ajouter un agent");
        stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/AjoutAgentPopup.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
      
        AjoutAgent controllerAgentPopup = (AjoutAgent) fxmlLoader.getController();
        
        // liaison entre les deux fenetres
        controllerAgentPopup.champAgentFormEquipement = numCPAgent;
        
	}
	
	@FXML
	private void ajoutLogiciel(ActionEvent event) throws IOException{
		
		Stage stage = new Stage();
        stage.setTitle("Ajouter un agent");
        stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/AjoutLogicielPopup.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
      
        AjoutLogiciel controllerLogicielPopup = (AjoutLogiciel) fxmlLoader.getController();
        
        // liaison entre les deux fenetres
        controllerLogicielPopup.champLogicielFormEquipement = lstLogiciel;
        
	}
	
	@FXML
	private void selectAgent(ActionEvent event) throws IOException{
	
		numCPAgent.clear();
		
		Stage stage = new Stage();
        stage.setTitle("Selection d'un agent");
        stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/SelectionAgentPopup.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
      
        SelectionAgentPopup controllerSelectAgentPopup = (SelectionAgentPopup) fxmlLoader.getController();
        
        // liaison entre les deux fenetres
        controllerSelectAgentPopup.champAgentFormEquipement = numCPAgent;
      
	}
	
	@FXML
	private void selectLogiciels(ActionEvent event) throws IOException{
		
		lstLogiciel.getItems().clear();
		
		Stage stage = new Stage();
        stage.setTitle("Selection de logiciels");
        stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/SelectionLogicielPopup.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
      
        SelectionLogicielPopup controllerSelectLogicielPopup = (SelectionLogicielPopup) fxmlLoader.getController();
        
        // liaison entre les deux fenetres
        controllerSelectLogicielPopup.champLogicielFormEquipement = lstLogiciel;
	}
	
	@FXML
	private void addTypeEquipement(ActionEvent event) throws IOException{
		
		Stage stage = new Stage();
        stage.setTitle("Type d'équipement");
        stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/TypeEquipementPopup.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
      
        TypeEquipementPopup controllerSelectLogicielPopup = (TypeEquipementPopup) fxmlLoader.getController();
        
        // liaison entre les deux fenetres
        controllerSelectLogicielPopup.champTypeEquipFormEquipement = typeEquipement;
	}
	
	@FXML
	private void enregistrerEquipement(ActionEvent event){
		
		if(validationFormulaire()){
			
			// rï¿½cupï¿½ration de l'agent si renseignï¿½
			Map<String, String> attribut = new HashMap<String, String>();
			attribut.put("numCP", numCPAgent.getText().trim());
			Agent agent = agentDao.findByAttributesEquals(attribut).get(0);
			//Agent agent = null;
			
			// calcul de la date prï¿½visionnelle de renouvellement
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
			
			// Crï¿½ation de l'ï¿½quipement
			Equipement newEquipement;
			if(lstLogiciel.getItems().isEmpty())
				newEquipement = new Equipement(typeEquipement.getSelectionModel().getSelectedItem(), agent, TransformationDonnees.getDoubleValue(prix), TransformationDonnees.formatDate(dateGarantie), TransformationDonnees.formatDate(dateLivraison), renewalDate, marque.getText(), modele.getText(), calife.getText(), info.getText());
			else
				newEquipement = new Equipement(typeEquipement.getSelectionModel().getSelectedItem(),lstLogiciel.getItems(), agent, TransformationDonnees.getDoubleValue(prix), TransformationDonnees.formatDate(dateGarantie), TransformationDonnees.formatDate(dateLivraison), renewalDate, marque.getText(), modele.getText(), calife.getText(), info.getText());
			
			equipementDao.save(newEquipement);
			informerValidation();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur enregistrement equipement");
			alert.setHeaderText("Les champs ci-dessous sont incorrectes ou non renseignï¿½s.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
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
		if(numCPAgent.getText().trim().equals("")){
			errorMessage += "Agent non renseigné.\n";
			formValid = false;
		}
		
	/*	if(numCPAgent.getSelectionModel().getSelectedItem() == null){
			errorMessage += "Agent non renseigné.\n";
			formValid = false;
		}*/
		
		
		return formValid;
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
		numCPAgent.clear();
		typeEquipement.getSelectionModel().clearSelection();
		dateGarantie.getEditor().clear();
		dateLivraison.getEditor().clear();
		lstLogiciel.getSelectionModel().clearSelection();
		
	}
}