package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.excel.export.ExcelAgentListExport;
import application.excel.export.ExcelGenerator;
import application.pdf.export.PDFAgentListExport;
import application.pdf.export.PDFGenerator;
import tools.ManipInterface;
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
		// TODO Auto-generated method stub
		this.list = new ArrayList<>();
	}
	
	@FXML
	private void searchAgent(ActionEvent event) throws IOException, SQLException{
		if(!searchBar.getText().isEmpty()){
		    ObservableList<Agent> agents = FXCollections.observableArrayList();     
		    
		    nomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("nom"));        
		    prenomCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("prenom"));
		    dateDeNaissanceCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("dateDeNaissance"));
		    numCPCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numCP"));
		    numPosteCol.setCellValueFactory(new PropertyValueFactory<Agent,String>("numPoste"));
		    
		   /* ResultSet rs = Database.doRequest("select nom, prenom, dateDeNaissance, numCPAgent, numPoste from agents where numCPAgent='"+ searchBar.getText() +"'");
		    while (rs.next()) {
			    agents.add(new Agent(rs.getString("nom"), rs.getString("prenom"), rs.getString("dateDeNaissance"), rs.getString("numCPAgent"), rs.getString("numCPAgent")));
		    }*/
		    
		    searchTab.setItems(agents);
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
        	
        }
	}

}
