package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Equipement;
import model.TypeEquipement;
import dao.EquipementDao;
import dao.TypeEquipementDao;

/**
 * class controller for the interface GestionRenewal.fxml
 * @author Erwan LE GUYADER
 * @version 1.0
 */
public class GraphRenewal implements Initializable{
	
	@FXML
	private AnchorPane bodyPanel;

	@FXML
	private StackedBarChart renewalSBchart;
	
	@FXML
	private ObservableList<String> sYear;
	
	@FXML
	private ObservableList<String> eYear;
	
	@FXML
	private ComboBox cbSYear;
	
	@FXML
	private ComboBox cbEYear;
	
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc = new StackedBarChart<String, Number>(xAxis, yAxis);
    
    private TypeEquipementDao typeEquipementDao;
    private EquipementDao equipementDao;
    private List<TypeEquipement> lstTypeEquipement;
    private String tGraph;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.typeEquipementDao = new TypeEquipementDao();
		this.equipementDao = new EquipementDao();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		this.lstTypeEquipement = typeEquipementDao.findByAttributesLike(null);
		
		for(int i=year; i<year+20; i++){
			sYear.add("" + (year + (year-i)));
			eYear.add("" + (year - (year-i)));
		}		
		
		cbSYear.getSelectionModel().selectFirst();
		cbEYear.getSelectionModel().select(5);
		
		this.renewalSBchart.setData(getChartDataPrice());	
	}
	
	private ObservableList<XYChart.Series> getChartDataPrice() {
		tGraph = "price";
		
		ArrayList<String> gap = new ArrayList<String>();
		xAxis.setLabel("Années");
        yAxis.setLabel("Prix");
		ObservableList<XYChart.Series> s = FXCollections.observableArrayList();
		
		for(int i=0; i<= (Integer.parseInt((String) cbEYear.getSelectionModel().getSelectedItem()) - Integer.parseInt((String) cbSYear.getSelectionModel().getSelectedItem())) ; i++){
			gap.add("" + (Integer.parseInt((String) cbSYear.getSelectionModel().getSelectedItem()) + i));			
		}
		
		xAxis.setCategories(FXCollections.<String>observableArrayList(gap));

		for(int i = 0; i< this.lstTypeEquipement.size(); i++){
			XYChart.Series<String, Number> type = new XYChart.Series<String, Number>();
			type.setName(this.lstTypeEquipement.get(i).getNom());
			
			for(int j=0; j<gap.size(); j++){
				List<Equipement> equipementByYear = equipementDao.getEquipementByRenewalDateAndType(gap.get(j), this.lstTypeEquipement.get(i));
				for(int k=0; k<equipementByYear.size(); k++){
					type.getData().add(new XYChart.Data<String, Number>(gap.get(j), equipementByYear.get(k).getPrix()));
				}
			}
			
			s.add(type);			
		}				
		return s;
	}
	
	private ObservableList<XYChart.Series> getChartDataQuantity() {
		String tGraph = "quantity";	
		ArrayList<String> gap = new ArrayList<String>();
		yAxis.setLabel("Prix");
		xAxis.setLabel("Années");
		ObservableList<XYChart.Series> s = FXCollections.observableArrayList();
		
		for(int i=0; i<= (Integer.parseInt((String) cbEYear.getSelectionModel().getSelectedItem()) - Integer.parseInt((String) cbSYear.getSelectionModel().getSelectedItem())) ; i++){
			gap.add("" + (Integer.parseInt((String) cbSYear.getSelectionModel().getSelectedItem()) + i));
		}
		
		xAxis.setCategories(FXCollections.<String>observableArrayList(gap));
		
		for(int i = 0; i< this.lstTypeEquipement.size(); i++){
			XYChart.Series<String, Number> type = new XYChart.Series<String, Number>();
			type.setName(this.lstTypeEquipement.get(i).getNom());
			for(int j=0; j<gap.size(); j++){			
				List<Equipement> equipementByYear = equipementDao.getEquipementByRenewalDateAndType(gap.get(j), this.lstTypeEquipement.get(i));
				if(equipementByYear.size() != 0){
					type.getData().add(new XYChart.Data<String, Number>(gap.get(j), equipementByYear.size()));
				}
			}			
			s.add(type);			
		}				
		return s;
	}
	
	public void displayByPrice(){
		this.renewalSBchart.setData(getChartDataPrice());
	}
	
	public void displayByQuantity(){
		this.renewalSBchart.setData(getChartDataQuantity());
	}

	public void changeGap(){
		if((Integer.parseInt((String) cbEYear.getSelectionModel().getSelectedItem()) - Integer.parseInt((String) cbSYear.getSelectionModel().getSelectedItem())) >0){
			ArrayList<String> gap = new ArrayList<String>();
			for(int i=0; i<= (Integer.parseInt((String) cbEYear.getSelectionModel().getSelectedItem()) - Integer.parseInt((String) cbSYear.getSelectionModel().getSelectedItem())) ; i++){
				gap.add("" + (Integer.parseInt((String) cbSYear.getSelectionModel().getSelectedItem()) + i));
			}
			xAxis.setCategories(FXCollections.<String>observableArrayList(gap));
			
			if(tGraph.equals("price")){
				displayByPrice();
			}else{
				displayByQuantity();
			}
		}
	}
	
	@FXML
	private void exportGraph() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
		fileChooser.setInitialFileName("renouvellement (par type)");
        fileChooser.setTitle("Enregister graphique");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	CategoryAxis xAxis = new CategoryAxis();
        	xAxis.setLabel(renewalSBchart.getXAxis().getLabel());
        	xAxis.setCategories(FXCollections.<String>observableArrayList(((CategoryAxis)renewalSBchart.getXAxis()).getCategories()));
        	xAxis.setSide(Side.BOTTOM);
        	NumberAxis yAxis = new NumberAxis();
        	yAxis.setLabel(renewalSBchart.getYAxis().getLabel());
        	yAxis.setSide(Side.LEFT);
        	StackedBarChart<String, Number> chart = new StackedBarChart<>(xAxis, yAxis);
        	
        	ObservableList<XYChart.Series<String, Number>> observableList = FXCollections.observableArrayList(renewalSBchart.getData());
        	ObservableList<XYChart.Series<String, Number>> observableList2 = FXCollections.observableArrayList();
        	for (XYChart.Series<String, Number> series : observableList) {
        		XYChart.Series<String, Number> type = new XYChart.Series<String, Number>();
    			type.setName(series.getName());
    			ObservableList<XYChart.Data<String, Number>> datas = FXCollections.observableArrayList(series.getData());
    			for (XYChart.Data<String, Number> data : datas) {
    				type.getData().add(new XYChart.Data<String, Number>(data.getXValue(), data.getYValue()));
    			}			
    			observableList2.add(type);
        	}
        	chart.setData(observableList2);
            Scene scene = new Scene(chart);
            WritableImage snapShot = scene.snapshot(null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", file);
        }
	}
}
