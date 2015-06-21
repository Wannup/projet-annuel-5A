package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Agent;
import model.Equipement;
import model.Logiciel;
import dao.AgentDao;
import dao.EquipementDao;

public class InformationEquipement implements Initializable{

	@FXML
	Parent root;
	
	@FXML
	private Label type;
	
	@FXML
	private Label marque;
	
	@FXML
	private Label modele;
	
	@FXML
	private Label calife;
	
	@FXML
	private Label nom;
	
	@FXML
	private Label prenom;
	
	@FXML
	private Label cp;
	
	@FXML
	private TextArea info;
	
	@FXML
	private Button ok;
	
	@FXML
	private Label dateLivraison;
	
	@FXML
	private ListView<Logiciel> logiciels;
	
	private int idEquipement;
	
	private EquipementDao eDao;
	private AgentDao aDao;
	private Equipement e;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.eDao = new EquipementDao();
		this.aDao = new AgentDao();
	}

	@FXML
	private void validate(){
		Stage stage = (Stage) ok.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void editEquipement() throws IOException{		
		Stage stage = new Stage();
		FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/EditEquipement.fxml"));
		Parent root;
		root = (Parent)fxmlLoader.load();
		EditEquipement controller = fxmlLoader.<EditEquipement>getController();
		controller.setValues(e.getId());
		Scene scene = new Scene(root); 
	    stage.setScene(scene);    
	    stage.show(); 
	    validate();	
	}
	
	public void setValues(int id){
		this.idEquipement = id;

		this.e = eDao.find(idEquipement);
		
		this.type.setText(e.getTypeEquipement().getNom());
		this.marque.setText(e.getMarque());
		this.modele.setText(e.getModele());
		this.calife.setText(e.getCalife());
		this.info.setText(e.getInfo());
		this.dateLivraison.setText(e.getDateLivraison());
		this.logiciels.getItems().addAll(e.getLogiciels());
		
		//if(e.getAgent() != null)
			/*this.nom.setText(a.getNom());
			this.prenom.setText(a.getPrenom());
			this.cp.setText(a.getNumCP());*/
	}
}
