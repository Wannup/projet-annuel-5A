package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Database;
import model.Agent;
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
	private void searchAgent(ActionEvent event) throws IOException, SQLException{
		if(!searchBar.getText().isEmpty()){
		    ObservableList<Agent> agents = FXCollections.observableArrayList();     
		    
		    nomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("nom"));        
		    prenomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("prenom"));
		    dateDeNaissanceCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("dateDeNaissance"));
		    numCPCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numCP"));
		    numPosteCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numPoste"));
		    
		    ResultSet rs = Database.doRequest("select nom, prenom, dateDeNaissance, numCPAgent, numPoste from agents where numCPAgent='"+ searchBar.getText() +"'");
		    while (rs.next()) {
			    agents.add(new Agent(rs.getString("nom"), rs.getString("prenom"), rs.getString("dateDeNaissance"), rs.getString("numCPAgent"), rs.getString("numCPAgent")));
		    }
		    
		    searchTab.setItems(agents);
		}
	}
	
	@FXML
	private void displayAddAgent(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/AjoutAgent.fxml"));
		bodyPanel.getChildren().setAll(loader.load());
		AnchorPane.setTopAnchor(bodyPanel, (double) 0);
		AnchorPane.setTopAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setRightAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setLeftAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setBottomAnchor(bodyPanel.getChildren().get(0), (double) 0);
	}

}
