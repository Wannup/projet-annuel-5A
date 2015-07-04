package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import model.Equipement;
import model.Pole;
import dao.EquipementDao;
import dao.PoleDao;

/**
 * class controller for the interface GestionRenewalPole.fxml
 * @author Erwan LE GUYADER
 * @version 1.0
 */

public class GraphRenewalPole implements Initializable{

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
    
    private PoleDao poleDao;
    private EquipementDao equipementDao;
    private List<Pole> lstPole;
    private String tGraph;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.poleDao = new PoleDao();
		this.equipementDao = new EquipementDao();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		this.lstPole = poleDao.findByAttributesLike(null);
		
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

		for(int i = 0; i< this.lstPole.size(); i++){
			XYChart.Series<String, Number> type = new XYChart.Series<String, Number>();
			type.setName(this.lstPole.get(i).getNom());
			
			for(int j=0; j<gap.size(); j++){
				List<Equipement> equipementByYear = equipementDao.getEquipementByRenewalDateAndPole(gap.get(j), this.lstPole.get(i));
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
		
		for(int i = 0; i< this.lstPole.size(); i++){
			XYChart.Series<String, Number> type = new XYChart.Series<String, Number>();
			type.setName(this.lstPole.get(i).getNom());
			for(int j=0; j<gap.size(); j++){			
				List<Equipement> equipementByYear = equipementDao.getEquipementByRenewalDateAndPole(gap.get(j), this.lstPole.get(i));
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
}
