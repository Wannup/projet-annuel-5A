package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.EquipementDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Equipement;
import model.Logiciel;
import tools.Config;
import tools.ManipInterface;
import application.database.DatabaseConnection;
import application.excel.export.ExcelEquipementListExport;
import application.excel.export.ExcelGenerator;
import application.excel.importer.ExcelEquipementImport;
import application.excel.importer.ExcelImport;
import application.pdf.export.PDFEquipementListExport;
import application.pdf.export.PDFGenerator;

public class GestionEquipement implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private CheckBox checkBoxExportTable;
	
	@FXML
	private TableView<Equipement> tableViewEquipement;
	
	@FXML
	private TableColumn<Equipement, Integer> columnNumero;
	
	@FXML
	private TableColumn<Equipement, Double> columnPrix;
	
	@FXML
	private TableColumn<Equipement, String> columnAgent;
	
	@FXML
	private TableColumn<Equipement, String> columnLogiciels;
	
	@FXML
	private Button buttonNext;
	
	private FXMLLoader loader;
	
	private List<Equipement> list;
	private EquipementDao equipementDao;
	private int maxResult;
	private int limit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.list = new ArrayList<>();
		columnNumero.setCellValueFactory(new PropertyValueFactory<Equipement,Integer>("numeroEquipement"));        
		columnPrix.setCellValueFactory(new PropertyValueFactory<Equipement,Double>("prix"));
		columnAgent.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> p) {
				if (p.getValue().getAgent() == null) {
					return new SimpleStringProperty("");
				}
				return new SimpleStringProperty(p.getValue().getAgent().getNumCP());
			}
		});
		columnLogiciels.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> p) {
				String Logiciels  = "";
				for (Logiciel logiciel : p.getValue().getLogiciels()) {
					Logiciels += logiciel.getNom() + ",";
				}
				return new SimpleStringProperty(Logiciels);
			}
		});
		equipementDao = new EquipementDao();
	    DatabaseConnection.startConnection();
	    boolean isLimit = Config.getPropertie("tableau_limite").equals("yes");
	    if (isLimit) {
		    maxResult = equipementDao.getMaxResult(null);
		    limit = Integer.parseInt(Config.getPropertie("tableau_nb_ligne"));
		    if (maxResult < limit) {
		    	this.list = equipementDao.findByAttributes(null);
		    } else {
		    	this.list = equipementDao.findByAttributesWithLimit(null, 0, limit);
		    }
	    } else {
	    	this.list = equipementDao.findByAttributes(null);
	    	maxResult = this.list.size();
	    }
		DatabaseConnection.closeConnection();
		refreshTable ();
	}
	
	@FXML
	private void displayAddEquipment(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/AjoutEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void exportTablePDF(ActionEvent event) throws IOException {
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("equipements");
        fileChooser.setTitle("Save PDF");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	if (checkBoxExportTable.isSelected()) {
        		pdfGenerator.generate(file, new PDFEquipementListExport(list));
    		} else if (maxResult == this.list.size()) {
    			pdfGenerator.generate(file, new PDFEquipementListExport(list));
    		} else {
    			DatabaseConnection.startConnection();
    			List<Equipement> results = equipementDao.findByAttributes(null);
    			DatabaseConnection.closeConnection();
    			pdfGenerator.generate(file, new PDFEquipementListExport(results));
    		}
        }
	}
	
	@FXML
	private void exportTableExcel(ActionEvent event) throws IOException {
		ExcelGenerator excelGenerator = new ExcelGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setInitialFileName("equipements");
        fileChooser.setTitle("Save Excel");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	if (checkBoxExportTable.isSelected()) {
        		excelGenerator.generate(file, new ExcelEquipementListExport(list));
    		} else if (maxResult == this.list.size()) {
    			excelGenerator.generate(file, new ExcelEquipementListExport(list));
    		} else {
    			DatabaseConnection.startConnection();
    			List<Equipement> results = equipementDao.findByAttributes(null);
    			DatabaseConnection.closeConnection();
    			excelGenerator.generate(file, new ExcelEquipementListExport(results));
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
        	excelImport.importFile(file, new ExcelEquipementImport(list));
        	DatabaseConnection.startConnection();
			for (Equipement equipement : this.list) {
				if (equipementDao.find(equipement.getId()) == null) {
					equipementDao.save(equipement);
				}
			}
			DatabaseConnection.closeConnection();
        	refreshTable();
        }
	}
	
	private void refreshTable () {
		ObservableList<Equipement> items = FXCollections.observableArrayList(list);
		tableViewEquipement.setItems(items);
		if (maxResult > this.list.size()) {
			buttonNext.setDisable(false);
		} else {
			buttonNext.setDisable(true);
		}
	}
	
	@FXML
	private void viewMore(ActionEvent event) throws IOException {
		DatabaseConnection.startConnection();
		List<Equipement> results = equipementDao.findByAttributesWithLimit(null, this.list.size(), limit);
		for (Equipement equipement : results) {
			this.list.add(equipement);
		}
		DatabaseConnection.closeConnection();
		refreshTable ();
	}

}
