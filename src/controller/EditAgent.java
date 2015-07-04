package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Agent;
import model.Pole;
import dao.PoleDao;

/**
 * class controller for the interface EditAgent.fxml
 * @author: Erwan LE GUYADER
 * @version 1.0
 * */
public class EditAgent implements Initializable{
	
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
	private Button btnEnregistrer;
	
	private PoleDao poleDao;
	private Agent agent;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		poleDao = new PoleDao();
		pole.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
	}

	/**
	 * function call by the controller "GestionAgent" to populate the edit form
	 * */
	public void setValues(Agent agentParam) {
		
		agent = agentParam;
		nom.setText(agent.getNom());
		prenom.setText(agent.getPrenom());
		numCP.setText(agent.getNumCP());
		tel.setText(agent.getTel());
		pole.getSelectionModel().select(agent.getPole());
	}
	
	/**
	 * function associate with the button "Modifier"
	 * */
	@FXML
	public void saveEditAgent(){
		agent.setNom(nom.getText());
		agent.setPrenom(prenom.getText());
		agent.setNumCP(numCP.getText());
		agent.setTel(tel.getText());
		agent.setPole(pole.getValue());
		
		Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
	    stage.close();
	}

}
