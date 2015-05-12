package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Agent;
import tools.ManipInterface;
import application.database.DatabaseConnection;
import dao.AgentDao;

public class GestionAgent implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private TableView<Agent> searchTab = new TableView<Agent>();
	
	@FXML
	private TextField searchBar = new TextField();
	
	@FXML
	private TableColumn<Agent, String> nomCol;
	
	@FXML
	private TableColumn<Agent, String> prenomCol;
	
	@FXML
	private TableColumn<Agent, String> dateDeNaissanceCol;
	
	@FXML
	private TableColumn<Agent, String> numCPCol;
	
	@FXML
	private TableColumn<Agent, String> numPosteCol;
	
	
	private FXMLLoader loader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void searchAgent(ActionEvent event) {
		if(!searchBar.getText().isEmpty()){     
		    
		    nomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("nom"));        
		    prenomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("prenom"));
		    dateDeNaissanceCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("dateDeNaissance"));
		    numCPCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numCP"));
		    numPosteCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numPoste"));
		    
		    
		    AgentDao agentDao = new AgentDao();
			DatabaseConnection.startConnection();
			ObservableList<Agent> agents = FXCollections.observableArrayList(agentDao.searchWithAttributes(searchBar.getText()));
			DatabaseConnection.closeConnection();
		    
		    searchTab.setItems(agents);
		}
	}
	
	@FXML
	private void displayAddAgent(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/AjoutAgent.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}

}
