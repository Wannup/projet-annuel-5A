package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.AgentDao;
import model.Agent;
import application.database.DatabaseConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RechercheAgentPopup implements Initializable{
	
	@FXML
	private TextField searchField;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private ListView<Agent> searchList;
	
	@FXML
	private Button searchValidation;
	
	private List<Agent> list;
	private AgentDao agentDao;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.list = new ArrayList<>();
		this.agentDao = new AgentDao();
		
		searchList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agent>() {
		    @Override
		    public void changed(ObservableValue<? extends Agent> observable, Agent oldValue, Agent newValue) {
		        System.out.println("ListView selection changed from oldValue = " 
		                + oldValue + " to newValue = " + newValue);
				
		    }
		});
	}
	
	@FXML
	private void searchAgent(ActionEvent event) {
		if (!searchField.getText().isEmpty()) {
			DatabaseConnection.startConnection();
			this.list = agentDao.searchWithAttributes(searchField.getText());
			DatabaseConnection.closeConnection();
			populateList();
		}
	}
	
	private void populateList(){
		ObservableList<Agent> oList = FXCollections.observableArrayList(this.list);
		searchList.setItems(oList);
	}
	
	@FXML
	private void validation(){
		Agent a = searchList.getSelectionModel().getSelectedItem();
		 
		Stage stage = (Stage) searchValidation.getScene().getWindow();
	    stage.close();
	}

}
