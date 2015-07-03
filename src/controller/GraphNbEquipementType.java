package controller;

import java.net.URL;
import java.util.ArrayList;
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
import model.TypeEquipement;
import dao.EquipementDao;
import dao.TypeEquipementDao;

public class GraphNbEquipementType implements Initializable{

	@FXML
	private StackedBarChart SBchart;
	
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc = new StackedBarChart<String, Number>(xAxis, yAxis);
    
    private TypeEquipementDao typeEquipementDao;
    private EquipementDao equipementDao;
    private List<TypeEquipement> lstTypeEquipement;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.typeEquipementDao = new TypeEquipementDao();
		this.equipementDao = new EquipementDao();
		this.lstTypeEquipement = typeEquipementDao.findByAttributesLike(null);
		ArrayList<String> lstEquipName = new ArrayList<String>();
		
		xAxis.setLabel("Type d'équipment");
		ObservableList<XYChart.Series> s = FXCollections.observableArrayList();
		
		XYChart.Series<String, Number> nbEquip = new XYChart.Series<String, Number>();
		for(int i = 0; i< this.lstTypeEquipement.size(); i++){
			
			lstEquipName.add(this.lstTypeEquipement.get(i).getNom());
						
			// int count = equipementDao.getEquipementByType(this.lstTypeEquipement.get(i)).size();
			
			/*List<Equipement> equipementByYear = equipementDao.getEquipementByRenewalDateAndType(gap.get(j), this.lstTypeEquipement.get(i).getNom());
			for(int k=0; k<equipementByYear.size(); k++){
				type.getData().add(new XYChart.Data<String, Number>(this.lstTypeEquipement.get(i).getNom(), equipementByYear.get(k).getPrix()));
			}
			
			
			s.add(nb);	*/		
		}
			
		xAxis.setCategories(FXCollections.<String>observableArrayList(FXCollections.observableArrayList(lstEquipName)));
        yAxis.setLabel("Quantité");
        
        this.SBchart.setData(s);	
		
	}
	
	/*
	xAxis.setLabel("Années");
    xAxis.setCategories(FXCollections.<String>observableArrayList(
            Arrays.asList("2016", "2017", "2018", "2019", "2020")));
    yAxis.setLabel("Prix");
    
    XYChart.Series<String, Number> series1 =
            new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> series2 =
            new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> series3 =
            new XYChart.Series<String, Number>();
    series1.setName("PC Portable");
    series1.getData().add(new XYChart.Data<String, Number>("2016", 5));
    series1.getData().add(new XYChart.Data<String, Number>("2017", 20));
    series1.getData().add(new XYChart.Data<String, Number>("2018", 18));
    series1.getData().add(new XYChart.Data<String, Number>("2019", 4));
    series1.getData().add(new XYChart.Data<String, Number>("2020", 6));
    series2.setName("PC Fixe");
    series2.getData().add(new XYChart.Data<String, Number>("2016", 5));
    series2.getData().add(new XYChart.Data<String, Number>("2017", 4));
    series2.getData().add(new XYChart.Data<String, Number>("2018", 4));
    series2.getData().add(new XYChart.Data<String, Number>("2019", 1));
    series2.getData().add(new XYChart.Data<String, Number>("2020", 14));
    series3.setName("Imprimante");
    series3.getData().add(new XYChart.Data<String, Number>("2016", 4));
    series3.getData().add(new XYChart.Data<String, Number>("2017", 4));
    series3.getData().add(new XYChart.Data<String, Number>("2018", 18));
    series3.getData().add(new XYChart.Data<String, Number>("2019", 17));
    series3.getData().add(new XYChart.Data<String, Number>("2020", 9));
	
    ObservableList<XYChart.Series> s = FXCollections.observableArrayList();
    s.add(series1);
    s.add(series2);
    s.add(series3);
	
	return s;*/

}
