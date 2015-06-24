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
		this.poleDao = new PoleDao();
		this.pole.getItems().addAll(FXCollections.observableArrayList(poleDao.findByAttributesLike(null)));
	}

	public void setValues(Agent agentParam) {
		
		this.agent = agentParam;
		this.nom.setText(agent.getNom());
		this.prenom.setText(agent.getPrenom());
		this.numCP.setText(agent.getNumCP());
		this.tel.setText(agent.getTel());
		this.pole.getSelectionModel().select(agent.getPole());
	}
	
	@FXML
	public void saveEditAgent(){
		this.agent.setNom(nom.getText());
		this.agent.setPrenom(prenom.getText());
		this.agent.setNumCP(numCP.getText());
		this.agent.setTel(tel.getText());
		this.agent.setPole(pole.getValue());
		
		Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
	    stage.close();
	}

}
