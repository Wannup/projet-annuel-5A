package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import model.Agent;
import model.Logiciel;
import model.Pole;
import dao.AgentDao;
import dao.LogicielDao;
import dao.PoleDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditAgent implements Initializable{
	
	private AgentDao aDao;
	private PoleDao pDao;
	private int idAgent;
	private Agent a;
	
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.aDao = new AgentDao();
		this.pDao = new PoleDao();
		this.pole.getItems().addAll(FXCollections.observableArrayList(pDao.findByAttributesLike(null)));
	}

	public void setValues(int id) {
		this.idAgent =  id;	
		
		this.a = aDao.find(idAgent);
		this.nom.setText(a.getNom());
		this.prenom.setText(a.getPrenom());
		this.numCP.setText(a.getNumCP());
		this.tel.setText(a.getTel());
	}
	
	@FXML
	public void saveEditAgent(){
		this.a.setNom(nom.getText());
		this.a.setPrenom(prenom.getText());
		this.a.setNumCP(numCP.getText());
		this.a.setTel(tel.getText());
		this.a.setPole(pole.getValue());
		
		Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
	    stage.close();
	}

}
