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

/**
 * class controller for the interface AjoutEquipement.fxml
 * */
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
	
	/**
	 * function call automatically when interface AjoutEquipement.fxml is load.
	 * */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		typeEquipementDao = new TypeEquipementDao();
		equipementDao = new EquipementDao();
		agentDao = new AgentDao();
		poleDao = new PoleDao();
		
		poles.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
		typeEquipement.getItems().addAll(FXCollections.observableArrayList(typeEquipementDao.findByAttributesLike(null)));
	}
	
	/**
	 * function associate with the button "Modifier/Supprimer des equipements"
	 * */
	@FXML
	private void displayEditDelete() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GestionEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	/**
	 * function associate with the button "+" for the "pole"
	 * */
	@FXML
	private void addPole() throws IOException{
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
	
	/**
	 * function associate with the button "Nouveau" for the "Agent"
	 * */
	@FXML
	private void ajoutAgent() throws IOException{
		
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
        controllerAgentPopup.champPolesEquipement = poles;
	}
	
	/**
	 * function associate with the button "Nouveau" for the "logiciel"
	 * */
	@FXML
	private void ajoutLogiciel() throws IOException{
		
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
	
	/**
	 * function associate with the button "Selection" for the "Agent"
	 * */
	@FXML
	private void selectAgent() throws IOException{
	
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
        controllerSelectAgentPopup.champPolesEquipement = poles;
	}
	
	/**
	 * function associate with the button "Selection" for the "Logiciels"
	 * */
	@FXML
	private void selectLogiciels() throws IOException{
		
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
	
	/**
	 * function associate with the button "+" for the "TypeEquipement"
	 * */
	@FXML
	private void addTypeEquipement() throws IOException{
		
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
	
	/**
	 * function associate with the button "Enregistrer"
	 * */
	@FXML
	private void enregistrerEquipement(){
		
		errorMessage = "";
		Agent agent = null;
		
		// récupération de l'agent si renseigné
		if(!numCPAgent.getText().trim().equals("")){
			Map<String, String> attribut = new HashMap<String, String>();
			attribut.put("numCP", numCPAgent.getText().trim());
			agent = agentDao.findByAttributesEquals(attribut).get(0);
		}
		
		if(validationFormulaire(agent)){
			
			// calcul de la date pr�visionnelle de renouvellement
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
			
			// Création de l'équipement
			Equipement newEquipement;
			if(lstLogiciel.getItems().isEmpty())
				newEquipement = new Equipement(typeEquipement.getSelectionModel().getSelectedItem(), agent, poles.getSelectionModel().getSelectedItem(), TransformationDonnees.getDoubleValue(prix), TransformationDonnees.formatDate(dateGarantie), TransformationDonnees.formatDate(dateLivraison), renewalDate, marque.getText(), modele.getText().trim(), calife.getText().trim(), info.getText().trim());
			else
				newEquipement = new Equipement(typeEquipement.getSelectionModel().getSelectedItem(),lstLogiciel.getItems(), agent, poles.getSelectionModel().getSelectedItem(), TransformationDonnees.getDoubleValue(prix), TransformationDonnees.formatDate(dateGarantie), TransformationDonnees.formatDate(dateLivraison), renewalDate, marque.getText().trim(), modele.getText().trim(), calife.getText().trim(), info.getText().trim());
			
			equipementDao.save(newEquipement);
			
			// ajout dans la liste des équipements de l'agent si renseigné
			if(!numCPAgent.getText().trim().equals("")){
				agent.addEquipement(newEquipement);
				agentDao.update(agent);
			}
			
			informerValidation();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur enregistrement equipement");
			alert.setHeaderText("Les erreurs sont les suivantes: ");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	
	/**
	 * function use for the form validation
	 * @return boolean 
	 * 	true if the entries are correct
	 * */
	private boolean validationFormulaire(Agent agent){
		
		boolean formValid = true;
		
		// vérification s'il n'existe pas déjà un équipement avec même calife
		if(!calife.getText().trim().equals("")){
				Map<String, String> attribut = new HashMap<String, String>();
				attribut.put("nomCalife", calife.getText().trim());
						
				if(!equipementDao.findByAttributesEquals(attribut).isEmpty()){
					errorMessage += "Il y a déjà un équipement enregistré avec ce nom de calife.\n";
					return false;
				}
		}
		
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
		
		if(numCPAgent.getText().trim().equals("") && poles.getSelectionModel().getSelectedItem() == null){
			errorMessage += "Vous devez renseigner l'agent ou le pole.\n";
			formValid = false;
		}
		
		if(!numCPAgent.getText().trim().equals("") && poles.getSelectionModel().getSelectedItem() != null){
			if(agent.getPole() != poles.getSelectionModel().getSelectedItem()){
				errorMessage += "L'agent sélectionné n'est pas lié au pole choisi.\n";
				formValid = false;
			}
		}
		
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
		lstLogiciel.getItems().clear();
		poles.getSelectionModel().clearSelection();
		
	}
	
}