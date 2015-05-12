package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Equipement;
import tools.ManipInterface;
import application.excel.export.ExcelEquipementListExport;
import application.excel.export.ExcelGenerator;
import application.pdf.export.PDFEquipementListExport;
import application.pdf.export.PDFGenerator;
import application.test.DataTest;

public class GestionEquipement implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private TableView<Equipement> tableViewEquipement;
	
	private FXMLLoader loader;
	
	private List<Equipement> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		DataTest dataTest = new DataTest();
		list = dataTest.getEquipements();
		ObservableList<Equipement> items = FXCollections.observableArrayList();
		for (Equipement equip : list) {
			items.add(equip);
		}
		tableViewEquipement.setItems(items);
		@SuppressWarnings("unchecked")
		TableColumn<Equipement,String> firstCol = (TableColumn<Equipement, String>) tableViewEquipement.getColumns().get(0);
		firstCol.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Equipement, String> p) {
		         // p.getValue() returns the Person instance for a particular TableView row
		         return new SimpleStringProperty(p.getValue().getNom());
		     }
		  });
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
        	pdfGenerator.generate(file, new PDFEquipementListExport(list));
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
        	excelGenerator.generate(file, new ExcelEquipementListExport(list));
        }
	}
	
	@FXML
	private void importExcel(ActionEvent event) throws IOException {
		
	}

}
