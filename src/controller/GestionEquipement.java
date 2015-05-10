package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.util.Callback;
import model.Equipement;
import application.DataTest;
import application.ExcelGenerator;
import application.PDFGenerator;

public class GestionEquipement implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private TableView<Equipement> tableViewEquipement;
	
	private FXMLLoader loader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		DataTest dataTest = new DataTest();
		List<Equipement> list = dataTest.getEquipements();
		ObservableList<Equipement> items = FXCollections.observableArrayList();
		for (Equipement equip : list) {
			items.add(equip);
		}
		tableViewEquipement.setItems(items);
		TableColumn<Equipement,String> firstCol = (TableColumn<Equipement, String>) tableViewEquipement.getColumns().get(0);
		firstCol.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Equipement, String> p) {
		         // p.getValue() returns the Person instance for a particular TableView row
		         return p.getValue().getNom();
		     }
		  });
	}
	
	@FXML
	private void displayAddEquipment(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/AjoutEquipement.fxml"));
		bodyPanel.getChildren().setAll(loader.load());
		AnchorPane.setTopAnchor(bodyPanel, (double) 0);
		AnchorPane.setTopAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setRightAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setLeftAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setBottomAnchor(bodyPanel.getChildren().get(0), (double) 0);
	}
	
	@FXML
	private void exportTable(ActionEvent event) throws IOException {
		ExcelGenerator excelGenerator = new ExcelGenerator();
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	excelGenerator.generate(file);
        }
        fileChooser.setTitle("Save PDF");
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	pdfGenerator.generate(file);
        }
		
		
	}

}