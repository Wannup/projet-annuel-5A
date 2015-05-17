package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Agent;
import tools.Config;
import tools.ManipInterface;
import application.database.DatabaseConnection;
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
	private TableColumn<Agent, String> dateDeNaissanceCol;
	
	@FXML
	private TableColumn<Agent, String> numCPCol;
	
	@FXML
	private TableColumn<Agent, String> numPosteCol;
	
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
	    dateDeNaissanceCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("dateDeNaissance"));
	    numCPCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numCP"));
	    numPosteCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numPoste"));
	    agentDao = new AgentDao();
	    DatabaseConnection.startConnection();
	    boolean isLimit = Config.getPropertie("tableau_limite").equals("yes");
	    if (isLimit) {
		    maxResult = agentDao.getMaxResult(null);
		    limit = Integer.parseInt(Config.getPropertie("tableau_nb_ligne"));
		    if (maxResult < limit) {
		    	this.list = agentDao.findByAttributes(null);
		    } else {
		    	this.list = agentDao.findByAttributesWithLimit(null, 0, limit);
		    }
	    } else {
	    	this.list = agentDao.findByAttributes(null);
	    	maxResult = this.list.size();
	    }
		DatabaseConnection.closeConnection();
		refreshTable ();
	}
	
	@FXML
	private void searchAgent(ActionEvent event) {
		if (!searchBar.getText().isEmpty()) {
			DatabaseConnection.startConnection();
			this.list = agentDao.searchWithAttributes(searchBar.getText());
			DatabaseConnection.closeConnection();
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
    			DatabaseConnection.startConnection();
    			List<Agent> results = agentDao.findByAttributes(null);
    			DatabaseConnection.closeConnection();
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
    			DatabaseConnection.startConnection();
    			List<Agent> results = agentDao.findByAttributes(null);
    			DatabaseConnection.closeConnection();
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
        	DatabaseConnection.startConnection();
			for (Agent agent : this.list) {
				if (agentDao.find(agent.getId()) == null) {
					agentDao.save(agent);
				}
			}
			DatabaseConnection.closeConnection();
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
		DatabaseConnection.startConnection();
		List<Agent> results = agentDao.findByAttributesWithLimit(null, this.list.size(), limit);
		for (Agent agent : results) {
			this.list.add(agent);
		}
		DatabaseConnection.closeConnection();
		refreshTable ();
	}

}
