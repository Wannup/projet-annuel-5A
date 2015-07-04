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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Logiciel;
import dao.LogicielDao;

/**
 * class controller for the interface SelectionLogicielPopup.fxml
 * @author Charly FAROT
 * @version 1.0
 */
public class SelectionLogicielPopup implements Initializable{

	@FXML
	private TableView<Logiciel> tableViewLogiciel;
	
	@FXML
	private TableColumn<Logiciel, String> columnNom;
	
	@FXML
	private TableColumn<Logiciel, String> columnPrix;
	
	@FXML
	private TableColumn<Logiciel, String> columnNumLicence;
	
	@FXML 
	private TableColumn<Logiciel, String> columnDateFinLicence;
	
	@FXML
	private TableColumn<Logiciel, Logiciel> columnSelect;
	
	@FXML
	private Button btnValidSelect;
	
	private List<Logiciel> listLogiciel;
	
	public ListView<Logiciel> champLogicielFormEquipement;
	
	private LogicielDao logicielDao;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		columnNom.setCellValueFactory(new Callback<CellDataFeatures<Logiciel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Logiciel, String> logiciel) {
				return new SimpleStringProperty(logiciel.getValue().getNom());
			}
		});
		
		columnPrix.setCellValueFactory(new Callback<CellDataFeatures<Logiciel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Logiciel, String> logiciel) {
				return new SimpleStringProperty(String.valueOf(logiciel.getValue().getPrix()));
			}
		});
		
		columnNumLicence.setCellValueFactory(new Callback<CellDataFeatures<Logiciel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Logiciel, String> logiciel) {
				return new SimpleStringProperty(logiciel.getValue().getLicenceNumber());
			}
		});
		
		columnDateFinLicence.setCellValueFactory(new Callback<CellDataFeatures<Logiciel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Logiciel, String> logiciel) {
				return new SimpleStringProperty(logiciel.getValue().getDateEndValidityLicence());
			}
		});
		
		columnSelect.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Logiciel, Logiciel>, ObservableValue<Logiciel>>() {
		      @Override 
		      public ObservableValue<Logiciel> call(TableColumn.CellDataFeatures<Logiciel, Logiciel> logiciel) {
		    	  return new ReadOnlyObjectWrapper<Logiciel>(logiciel.getValue());
		      }
		});
		
		columnSelect.setCellFactory(new Callback<TableColumn<Logiciel,Logiciel>, TableCell<Logiciel,Logiciel>>() {
			 @Override 
		      public TableCell<Logiciel, Logiciel> call(TableColumn<Logiciel, Logiciel> logicielTableColumn) {
		    	  return new TableCell<Logiciel, Logiciel>() {
		             
		              final CheckBox checkBox = new CheckBox(); 
		                
		              @Override
					public void updateItem(Logiciel logiciel, boolean empty) {
		                super.updateItem(logiciel, empty);
		                if (logiciel != null) {
		                	 checkBox.setSelected(false);
		                	 setGraphic(checkBox);
		                	 checkBox.setOnAction(new EventHandler<ActionEvent>() {
			                    @Override 
			                    public void handle(ActionEvent event){
			                    	 if(checkBox.isSelected())
			                    		 champLogicielFormEquipement.getItems().add(logiciel);
			                    	 else
			                    		 champLogicielFormEquipement.getItems().remove(logiciel);         	
			                    }
		                  });
		                } else {
		                  setGraphic(null);
		                }
		              }
		           };
		      }
		 });
		
		logicielDao = new LogicielDao();
		listLogiciel = logicielDao.findByAttributesLike(null);
		
		ObservableList<Logiciel> itemsLogiciel = FXCollections.observableArrayList(listLogiciel);
		tableViewLogiciel.setItems(itemsLogiciel);
		
	}

	@FXML
	private void validateSelection(){
		Stage fenetre =(Stage)btnValidSelect.getScene().getWindow();
   	 	fenetre.close(); 
	}
}
