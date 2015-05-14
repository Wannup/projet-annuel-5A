package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import dao.LogicielDao;
import model.Logiciel;
import application.database.DatabaseConnection;
import application.excel.export.ExcelGenerator;
import application.excel.export.ExcelLogicielListExport;
import application.excel.importer.ExcelImport;
import application.excel.importer.ExcelLogicielImport;
import application.pdf.export.PDFGenerator;
import application.pdf.export.PDFLogicielListExport;
import tools.ManipInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class GestionLogiciel implements Initializable {

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private TableView<Logiciel> tableLogiciel = new TableView<Logiciel>();
	
	@FXML
	private TableColumn<Logiciel, String> columnLibelle;
	
	@FXML
	private TableColumn<Logiciel, Double> columnPrix;
	
	@FXML
	private TableColumn<Logiciel, Integer> columnDuree;
	
	private FXMLLoader loader;
	
	private List<Logiciel> list;
	private LogicielDao logicielDao;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.list = new ArrayList<>();
		columnLibelle.setCellValueFactory(new PropertyValueFactory<Logiciel,String>("nom"));        
		columnPrix.setCellValueFactory(new PropertyValueFactory<Logiciel,Double>("prix"));
		columnDuree.setCellValueFactory(new PropertyValueFactory<Logiciel,Integer>("nbJourLicence"));
		logicielDao = new LogicielDao();
	    DatabaseConnection.startConnection();
		this.list = logicielDao.findByAttributes(new HashMap<String, String>());
		DatabaseConnection.closeConnection();
		refreshTable ();
	}

	@FXML
	private void displayAddLogiciel(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/AjoutLogiciel.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void exportTablePDF(ActionEvent event) throws IOException {
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("logiciels");
        fileChooser.setTitle("Save PDF");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	pdfGenerator.generate(file, new PDFLogicielListExport(list));
        }
	}
	
	@FXML
	private void exportTableExcel(ActionEvent event) throws IOException {
		ExcelGenerator excelGenerator = new ExcelGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setInitialFileName("logiciels");
        fileChooser.setTitle("Save Excel");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	excelGenerator.generate(file, new ExcelLogicielListExport(list));
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
        	excelImport.importFile(file, new ExcelLogicielImport(list));
        	DatabaseConnection.startConnection();
			for (Logiciel logiciel : this.list) {
				if (logicielDao.find(logiciel.getId()) == null) {
					logicielDao.save(logiciel);
				}
			}
			DatabaseConnection.closeConnection();
        	refreshTable ();
        }
	}
	
	private void refreshTable () {
		ObservableList<Logiciel> items = FXCollections.observableArrayList(list);
		tableLogiciel.setItems(items);
	}
}
