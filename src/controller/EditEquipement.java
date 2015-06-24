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
import javafx.event.ActionEvent;
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

public class EditEquipement implements Initializable{
	
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
	private TextField numCPAgent;
	
	@FXML
	private Button editButton;
	
	private TypeEquipementDao typeEquipementDao;
	private EquipementDao equipementDao;
	
	private String errorMessage = "";	
	private AgentDao agentDao;
	private PoleDao poleDao;
	private Equipement equipement;
	
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
        controllerAgentPopup.champPolesEquipement = poles;
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
        controllerSelectAgentPopup.champPolesEquipement = poles;
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
	private void modifierEquipement(ActionEvent event){
		
		errorMessage = "";
		Agent agent = null;
		
		// récupération de l'agent si renseigné
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
			
		//	if()
			
			// TODO à continuer
			
			
			
			// ajout dans la liste des équipements de l'agent si renseigné
			/*if(!numCPAgent.getText().trim().equals("")){
				agent.addEquipement(newEquipement);
				agentDao.update(agent);
			}*/
			//equipement.setTypeEquipement(type.getSelectionModel().getSelectedItem());
			equipement.setNomCalife(calife.getText());
			equipement.setDateGarantie(TransformationDonnees.formatDate(dateGarantie));
			equipement.setMarque(marque.getText());
			equipement.setPrix(Double.parseDouble(prix.getText()));
			equipement.setModele(modele.getText());
			//equipement.setAgent(numCPAgent.getSelectionModel().getSelectedItem());
			equipement.setInfo(info.getText());
			
			//equipementDao.save(newEquipement);
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
	
	private boolean validationFormulaire(Agent agent){
		
		boolean formValid = true;
		
		// vérification s'il n'existe pas déjà un équipement avec le même calife
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
	
	public void setValues(Equipement equipementToModify){
		
		equipement = equipementToModify;
		if(equipement.getDateGarantie().trim().length() > 0){
			LocalDate myDate = LocalDate.parse(equipement.getDateGarantie().split("/")[2]+"-"+equipement.getDateGarantie().split("/")[1]+"-"+equipement.getDateGarantie().split("/")[0]);
			this.dateGarantie.setValue(myDate);
		}
		
		if(equipement.getDateLivraison().trim().length() > 0){
			LocalDate myDate = LocalDate.parse(equipement.getDateLivraison().split("/")[2]+"-"+equipement.getDateLivraison().split("/")[1]+"-"+equipement.getDateLivraison().split("/")[0]);
			this.dateLivraison.setValue(myDate);
		}
		
		if(equipement.getAgent() != null){
			this.numCPAgent.setText(equipement.getAgent().getNumCP());
		}
		this.typeEquipement.getSelectionModel().select(equipement.getTypeEquipement());
		this.poles.getSelectionModel().select(equipement.getPole());
		this.marque.setText(equipement.getMarque());
		this.modele.setText(equipement.getModele());
		this.calife.setText(equipement.getNomCalife());
		this.info.setText(equipement.getInfo());
		this.prix.setText("" + equipement.getPrix());
		this.lstLogiciel.getItems().addAll(FXCollections.observableArrayList(FXCollections.observableArrayList(equipement.getLogiciels())));
	}
	
	private void informerValidation(){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout equipement");
		alert.setHeaderText(null);
		alert.setContentText("Equipement modifié avec succès !");
		alert.showAndWait();
	}
	
}