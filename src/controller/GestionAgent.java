package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Agent;
import tools.Config;
import tools.ManipInterface;
import application.excel.export.ExcelAgentListExport;
import application.excel.export.ExcelGenerator;
import application.excel.importer.ExcelAgentImport;
import application.excel.importer.ExcelImport;
import application.pdf.export.PDFAgentListExport;
import application.pdf.export.PDFGenerator;
import dao.AgentDao;

public class GestionAgent implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private CheckBox checkBoxExportTable;
	
	@FXML
	private TableView<Agent> searchTab = new TableView<Agent>();
	
	@FXML
	private TextField searchBar = new TextField();
	
	@FXML
	private TableColumn<Agent, String> nomCol;
	
	@FXML
	private TableColumn<Agent, String> prenomCol;
	
	@FXML
	private TableColumn<Agent, String> poleCol;
	
	@FXML
	private TableColumn<Agent, String> numCPCol;
	
	@FXML
	private TableColumn<Agent, String> telCol;
	
	@FXML
	private TableColumn<Agent, String> numPosteCol;
	
	@FXML
	private TableColumn<Agent, Agent> columnModifier;
	
	@FXML
	private TableColumn<Agent, Agent> columnSupprimer;
	
	@FXML
	private Button buttonNext;
	
	
	private FXMLLoader loader;

	private List<Agent> list;
	private AgentDao agentDao;
	private int maxResult;
	private int limit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.list = new ArrayList<>();
		nomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("nom"));        
	    prenomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("prenom"));
	    poleCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("pole"));
	    numCPCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numCP"));
	    telCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("tel"));
	    
	    columnModifier.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Agent, Agent>, ObservableValue<Agent>>() {
	      @Override public ObservableValue<Agent> call(TableColumn.CellDataFeatures<Agent, Agent> features) {
	    	  return new ReadOnlyObjectWrapper<Agent>(features.getValue());
	      }
	    });
	 
	    columnModifier.setCellFactory(new Callback<TableColumn<Agent, Agent>, TableCell<Agent, Agent>>() {
	      @Override public TableCell<Agent, Agent> call(TableColumn<Agent, Agent> personBooleanTableColumn) {
	    	  return new TableCell<Agent, Agent>() {
	              //final ImageView buttonGraphic = new ImageView();
	              final Button button = new Button(); {
	             //   button.setGraphic(buttonGraphic);
	                button.setMinWidth(70);
	              }
	              public void updateItem(Agent person, boolean empty) {
	                super.updateItem(person, empty);
	                if (person != null) {
	                	button.setText("Modifier");
	                	//buttonGraphic.setImage(Image);

	                  setGraphic(button);
	                  button.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override public void handle(ActionEvent event) {
	                     
	                    }
	                  });
	                } else {
	                  setGraphic(null);
	                }
	              }
	            };
	          }
	    });
	    
	    columnSupprimer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Agent, Agent>, ObservableValue<Agent>>() {
		      @Override public ObservableValue<Agent> call(TableColumn.CellDataFeatures<Agent, Agent> features) {
		    	  return new ReadOnlyObjectWrapper<Agent>(features.getValue());
		      }
		    });
		 
	    columnSupprimer.setCellFactory(new Callback<TableColumn<Agent, Agent>, TableCell<Agent, Agent>>() {
		      @Override public TableCell<Agent, Agent> call(TableColumn<Agent, Agent> personBooleanTableColumn) {
		    	  return new TableCell<Agent, Agent>() {
		              //final ImageView buttonGraphic = new ImageView();
		              final Button button = new Button(); {
		             //   button.setGraphic(buttonGraphic);
		                button.setMinWidth(70);
		              }
		              public void updateItem(Agent person, boolean empty) {
		                super.updateItem(person, empty);
		                if (person != null) {
		                	button.setText("X");
		                	//buttonGraphic.setImage(Image);

		                  setGraphic(button);
		                  button.setOnAction(new EventHandler<ActionEvent>() {
		                    @Override public void handle(ActionEvent event) {
		                     
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
	    boolean isLimit = Config.getPropertie("tableau_limite").equals("yes");
	    if (isLimit) {
		    maxResult = agentDao.getNbResultLike(null);
		    limit = Integer.parseInt(Config.getPropertie("tableau_nb_ligne"));
		    if (maxResult < limit) {
		    	this.list = agentDao.findByAttributesLike(null);
		    } else {
		    	this.list = agentDao.findByAttributesLikeWithLimits(null, 0, limit);
		    }
	    } else {
	    	this.list = agentDao.findByAttributesLike(null);
	    	maxResult = this.list.size();
	    }
		refreshTable ();
	}
	
	@FXML
	private void searchAgent(ActionEvent event) {
		if (!searchBar.getText().isEmpty()) {
			this.list = agentDao.searchWithAttributes(searchBar.getText());
			refreshTable ();
		}
	}
	
	@FXML
	private void displayAddAgent(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/AjoutAgent.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void exportTablePDF(ActionEvent event) throws IOException {
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("agents");
        fileChooser.setTitle("Save PDF");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	if (checkBoxExportTable.isSelected()) {
        		pdfGenerator.generate(file, new PDFAgentListExport(list));
    		} else if (maxResult == this.list.size()) {
    			pdfGenerator.generate(file, new PDFAgentListExport(list));
    		} else {	
    			List<Agent> results = agentDao.findByAttributesLike(null);
    			pdfGenerator.generate(file, new PDFAgentListExport(results));
    		}
        }
	}
	
	@FXML
	private void exportTableExcel(ActionEvent event) throws IOException {
		ExcelGenerator excelGenerator = new ExcelGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setInitialFileName("agents");
        fileChooser.setTitle("Save Excel");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	if (checkBoxExportTable.isSelected()) {
        		excelGenerator.generate(file, new ExcelAgentListExport(list));
    		} else if (maxResult == this.list.size()) {
    			excelGenerator.generate(file, new ExcelAgentListExport(list));
    		} else {
    			List<Agent> results = agentDao.findByAttributesLike(null);
    			excelGenerator.generate(file, new ExcelAgentListExport(results));
    		}
        }
	}
	
	@FXML
	private void importExcel(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
        fileChooser.setTitle("Load Excel");
        File file;
        file = fileChooser.showOpenDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	ExcelImport excelImport = new ExcelImport();
        	excelImport.importFile(file, new ExcelAgentImport(list));
			for (Agent agent : this.list) {
				if (agentDao.find(agent.getId()) == null) {
					agentDao.save(agent);
				}
			}
        	refreshTable ();
        }
	}
	
	private void refreshTable () {
		ObservableList<Agent> items = FXCollections.observableArrayList(list);
		searchTab.setItems(items);
		if (maxResult > this.list.size()) {
			buttonNext.setDisable(false);
		} else {
			buttonNext.setDisable(true);
		}
	}
	
	@FXML
	private void viewMore(ActionEvent event) throws IOException {

		List<Agent> results = agentDao.findByAttributesLikeWithLimits(null, this.list.size(), limit);
		for (Agent agent : results) {
			this.list.add(agent);
		}
		refreshTable ();
	}

}
