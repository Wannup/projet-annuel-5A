package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Agent;
import dao.AgentDao;

public class SelectionAgentPopup implements Initializable{

	@FXML
	private TableView<Agent> tableViewAgent;
	
	@FXML
	private TableColumn<Agent, String> columnNumCP;
	
	@FXML
	private TableColumn<Agent, String> columnNom;
	
	@FXML
	private TableColumn<Agent, String> columnPrenom;
	
	@FXML
	private TableColumn<Agent, String> columnTel;
	
	@FXML 
	private TableColumn<Agent, String> columnPole;
	
	@FXML
	private TableColumn<Agent, Agent> columnSelect;
	
	private List<Agent> listAgent;
	
	public TextField champAgentFormEquipement;
	
	private AgentDao agentDao;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		columnNumCP.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getNumCP());
			}
		});
		
		columnNom.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getNom());
			}
		});
		
		columnPrenom.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getPrenom());
			}
		});
		
		columnTel.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getTel());
			}
		});
		
		columnPole.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getPole().getNom());
			}
		});
		
		columnSelect.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Agent, Agent>, ObservableValue<Agent>>() {
		      @Override 
		      public ObservableValue<Agent> call(TableColumn.CellDataFeatures<Agent, Agent> agent) {
		    	  return new ReadOnlyObjectWrapper<Agent>(agent.getValue());
		      }
		});
		
		columnSelect.setCellFactory(new Callback<TableColumn<Agent,Agent>, TableCell<Agent,Agent>>() {
			 @Override 
		      public TableCell<Agent, Agent> call(TableColumn<Agent, Agent> agentTableColumn) {
		    	  return new TableCell<Agent, Agent>() {
		             
		              final Button button = new Button("Selectionner"); 
		                
		              public void updateItem(Agent agent, boolean empty) {
		                super.updateItem(agent, empty);
		                if (agent != null) {
		                	 button.setMinWidth(70);
		                	 setGraphic(button);
		                	 button.setOnAction(new EventHandler<ActionEvent>() {
			                    @Override 
			                    public void handle(ActionEvent event){
			                    	 champAgentFormEquipement.setText(agent.getNumCP());
			                    	 Stage fenetre =(Stage)button.getScene().getWindow();
			                    	 fenetre.close();             	
			                    }
		                  });
		                } else {
		                  setGraphic(null);
		                }
		              }
		           };
		      }
		 });
		
		agentDao = new AgentDao();
		listAgent = agentDao.findByAttributesLike(null);
		
		ObservableList<Agent> itemsAgent = FXCollections.observableArrayList(listAgent);
		tableViewAgent.setItems(itemsAgent);
		
	}

}
