package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Agent;
import model.Equipement;
import model.Logiciel;
import model.Pole;
import model.TypeEquipement;
import tools.TransformationDonnees;
import dao.AgentDao;
import dao.EquipementDao;
import dao.PoleDao;
import dao.TypeEquipementDao;

/**
 * class controller for the interface EditEquipement.fxml
 * @author: Erwan LE GUYADER
 * @version 1.0
 * */
public class EditEquipement implements Initializable{
	
	@FXML 
	protected TextField prix;

	@FXML
	protected TextField modele;
	
	@FXML
	protected TextField marque;
	
	@FXML
	protected TextArea info;
	
	@FXML
	protected TextField calife;
	
	@FXML
	protected ComboBox<TypeEquipement> typeEquipement;
	
	@FXML
	protected DatePicker dateGarantie;
	
	@FXML
	protected DatePicker dateLivraison;
	
	@FXML
	protected ListView<Logiciel> lstLogiciel;
	
	@FXML
	protected ComboBox<Pole> poles;
	
	@FXML
	protected TextField numCPAgent;
	
	@FXML
	private Button editButton;
	
	private TypeEquipementDao typeEquipementDao;
	protected EquipementDao equipementDao;
	
	protected String errorMessage = "";	
	protected AgentDao agentDao;
	private PoleDao poleDao;
	private Equipement equipement;
	
	public Button fieldOnMainWindows;
	
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
	 * function associate with the button "Modifier"
	 * */
	@FXML
	private void modifierEquipement(){
		
		errorMessage = "";
		Agent agent = null;
		
		if(!numCPAgent.getText().trim().equals("")){
			Map<String, String> attribut = new HashMap<String, String>();
			attribut.put("numCP", numCPAgent.getText().trim());
			agent = agentDao.findByAttributesEquals(attribut).get(0);
		}
		
		if(validationFormulaire(agent)){
			
			if(!TransformationDonnees.formatDate(dateLivraison).equals(equipement.getDateLivraison())){
				// calcul de la date prévisionnelle de renouvellement
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				if(!TransformationDonnees.formatDate(dateLivraison).equals("")){
					try {
						Date dateLivr = dateFormat.parse(TransformationDonnees.formatDate(dateLivraison));
						calendar.setTime(dateLivr);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					calendar.add(Calendar.YEAR, typeEquipement.getSelectionModel().getSelectedItem().getNbYearRenewal());
					String renewalDate = dateFormat.format(calendar.getTime());
				
					equipement.setRenewalDate(renewalDate);
				}
				equipement.setDateLivraison(TransformationDonnees.formatDate(dateLivraison));
			}
			
			// changement de l'agent
			if(equipement.getAgent() != null && agent != null && !equipement.getAgent().getNumCP().equals(numCPAgent.getText().trim())){
				Agent oldAgent = equipement.getAgent();
				oldAgent.getEquipements().remove(equipement);
				agentDao.update(oldAgent);
				agent.addEquipement(equipement);
				agentDao.update(agent);
			}
			// ajout agent, pas d'agent ultérieurement 
			else if(equipement.getAgent() == null && agent != null){
				agent.addEquipement(equipement);
				agentDao.update(agent);		
			}
			else if(numCPAgent.getText().trim().equals("") && equipement.getAgent() != null){
				Agent oldAgent = equipement.getAgent();
				oldAgent.getEquipements().remove(equipement);
				agentDao.update(oldAgent);	
			}
			
			equipement.setAgent(agent);
			equipement.setTypeEquipement(typeEquipement.getSelectionModel().getSelectedItem());
			equipement.setDateGarantie(TransformationDonnees.formatDate(dateGarantie));
			equipement.setMarque(marque.getText().trim());
			equipement.setModele(modele.getText().trim());
			equipement.setNomCalife(calife.getText().trim());
			equipement.setInfo(info.getText());
			equipement.setPole(poles.getSelectionModel().getSelectedItem());
			equipement.setPrix(Double.parseDouble(prix.getText().trim()));
			equipement.setLogiciels(lstLogiciel.getItems());
			
			equipementDao.update(equipement);
			fieldOnMainWindows.setVisible(true);
			informerValidation();
			
			Stage stage = (Stage) editButton.getScene().getWindow();
		    stage.close();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur modification equipement");
			alert.setHeaderText("Les erreurs sont les suivantes: ");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	
	/**
	 * @return true if "calife" is ok
	 * */
	protected boolean verificationCalife(){
		
		if(calife.getText().trim().equals("")){
			errorMessage += "Calife non renseigné.\n";
			return false;
		}
		
		// vérification s'il n'existe pas déjà un équipement avec le même calife si le calife a changé.
		if(!equipement.getNomCalife().equals(calife.getText().trim())){
			Map<String, String> attribut = new HashMap<String, String>();
			attribut.put("nomCalife", calife.getText().trim());
								
			if(!equipementDao.findByAttributesEquals(attribut).isEmpty()){
				errorMessage += "Il y a déjà un équipement enregistré avec ce nom de calife.\n";
				return false;
			}
		}
		return true;
	}
	
	/**
	 * function use for the form validation
	 * @return boolean 
	 * 	true if the entries are correct
	 * */
	protected boolean validationFormulaire(Agent agent){
		
		boolean formValid = true;
		
		formValid = verificationCalife();
		
		if(typeEquipement.getSelectionModel().getSelectedItem() == null){
			errorMessage += "Type d'équipement non renseigné.\n";
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
	
	/**
	 * function call by the controller InformationEquipement to populate the form
	 * */
	public void setValues(Equipement equipementToModify){
		
		equipement = equipementToModify;
		if(equipement.getDateGarantie().trim().length() > 0){
			LocalDate myDate = LocalDate.parse(equipement.getDateGarantie().split("/")[2]+"-"+equipement.getDateGarantie().split("/")[1]+"-"+equipement.getDateGarantie().split("/")[0]);
			this.dateGarantie.setValue(myDate);
		}
		
		if(equipement.getDateLivraison().trim().length() > 0){
			LocalDate myDate = LocalDate.parse(equipement.getDateLivraison().split("/")[2]+"-"+equipement.getDateLivraison().split("/")[1]+"-"+equipement.getDateLivraison().split("/")[0]);
			dateLivraison.setValue(myDate);
		}
		
		if(equipement.getAgent() != null){
			numCPAgent.setText(equipement.getAgent().getNumCP());
		}
		typeEquipement.getSelectionModel().select(equipement.getTypeEquipement());
		poles.getSelectionModel().select(equipement.getPole());
		marque.setText(equipement.getMarque());
		modele.setText(equipement.getModele());
		calife.setText(equipement.getNomCalife());
		info.setText(equipement.getInfo());
		prix.setText(String.valueOf(equipement.getPrix()));
		lstLogiciel.setItems(FXCollections.observableArrayList(equipement.getLogiciels()));
	}
	
	/**
	 * function call to inform the modification validation
	 * */
	private void informerValidation(){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout equipement");
		alert.setContentText("Equipement modifié avec succès !");
		alert.showAndWait();
	}
	
}