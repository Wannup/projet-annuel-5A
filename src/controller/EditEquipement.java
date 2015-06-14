package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import tools.TransformationDonnees;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Agent;
import model.Equipement;
import model.Logiciel;
import model.TypeEquipement;
import dao.AgentDao;
import dao.EquipementDao;
import dao.LogicielDao;
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
	private ComboBox<TypeEquipement> type;
	
	@FXML
	private DatePicker dateGarantie;
	
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
	
	@FXML
	private Button btnEnregistrer;
	
	private Equipement e;
	
	private int idEquipement;
	private TypeEquipementDao teDao;
	private AgentDao aDao;
	private EquipementDao eDao;
	private LogicielDao lDao;
	private String errorMessage = "";


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.eDao = new EquipementDao();
		this.aDao = new AgentDao();
		this.teDao = new TypeEquipementDao();
		this.lDao = new LogicielDao();
		
		type.getItems().addAll(FXCollections.observableArrayList(teDao.findByAttributesLike(null)));
		numCPAgent.getItems().addAll(FXCollections.observableArrayList(aDao.findByAttributesLike(null)));	
		lstLogiciel.getItems().addAll(FXCollections.observableArrayList(lDao.findByAttributesLike(null)));
		lstLogiciel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	public void setValues(int id){
		this.idEquipement = id;

		this.e = eDao.find(idEquipement);

		this.marque.setText(e.getMarque());
		this.modele.setText(e.getModele());
		this.calife.setText(e.getCalife());
		this.info.setText(e.getInfo());
		this.prix.setText("" + e.getPrix());
		this.dateGarantie.getEditor().setText(e.getDateGarantie());
		
	}
	
	private boolean validationFormulaire(){
		
		boolean formValid = true;
		
		if(type.getSelectionModel().getSelectedItem() == null){
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
				errorMessage += "Aucun logiciel associé à l'équipement, décochez la case.\n";
				formValid = false;
			}
		}
		return formValid;
	}

	@FXML
	public void validate(){
		
		if(validationFormulaire()){
			// todo recupération de l'agent
			
			/*ObservableList<Logiciel> selectedLog =  lstLogiciel.getSelectionModel().getSelectedItems();
            for(Logiciel l : selectedLog){
                
            }*/
			
			e.setTypeEquipement(type.getSelectionModel().getSelectedItem().toString());
			e.setCalife(calife.getText());
			e.setDateGarantie(TransformationDonnees.formatDate(dateGarantie));
			e.setMarque(marque.getText());
			e.setPrix(Double.parseDouble(prix.getText()));
			e.setModele(modele.getText());
			e.setAgent(numCPAgent.getSelectionModel().getSelectedItem());
			e.setInfo(info.getText());
			informerValidation();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur enregistrement equipement");
			alert.setHeaderText("Les champs ci-dessous sont incorrectes ou non renseignés.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
		
		Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
	    stage.close();
	}
	
	private void informerValidation(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout equipement");
		alert.setHeaderText(null);
		alert.setContentText("Equipement modifié avec succès !\n Pensez à rafraichir la liste d'équipement !");
		alert.showAndWait();
	}
}
