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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Agent;
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
	
	private List<Agent> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.list = new ArrayList<>();
		nomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("nom"));        
	    prenomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("prenom"));
	    dateDeNaissanceCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("dateDeNaissance"));
	    numCPCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numCP"));
	    numPosteCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numPoste"));
	}
	
	@FXML
	private void searchAgent(ActionEvent event) {
		if (!searchBar.getText().isEmpty()) {
		    AgentDao agentDao = new AgentDao();
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
        	pdfGenerator.generate(file, new PDFAgentListExport(list));
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
        	excelGenerator.generate(file, new ExcelAgentListExport(list));
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
        	refreshTable ();
        }
	}
	
	private void refreshTable () {
		ObservableList<Agent> items = FXCollections.observableArrayList(list);
		searchTab.setItems(items);
	}

}
