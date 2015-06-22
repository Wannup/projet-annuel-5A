package controller;

import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class GraphRenewal implements Initializable{

	@FXML
	private StackedBarChart renewalSBchart;
	
	@FXML
	private ObservableList<String> sYear;
	
	@FXML
	private ObservableList<String> eYear;
	
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc =
            new StackedBarChart<String, Number>(xAxis, yAxis);
    final XYChart.Series<String, Number> series1 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series2 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series3 =
            new XYChart.Series<String, Number>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		for(int i=year; i<year+20; i++){
			sYear.add("" + (year + (year-i)));
			eYear.add("" + (year - (year-i)));
		}		
		this.renewalSBchart.setData(getChartDataPrice());	
	}
	
	private ObservableList<XYChart.Series> getChartDataPrice() {
		
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
        series1.getData().add(new XYChart.Data<String, Number>("2016", 25601.34));
        series1.getData().add(new XYChart.Data<String, Number>("2017", 20148.82));
        series1.getData().add(new XYChart.Data<String, Number>("2018", 10000));
        series1.getData().add(new XYChart.Data<String, Number>("2019", 35407.15));
        series1.getData().add(new XYChart.Data<String, Number>("2020", 12000));
        series2.setName("PC Fixe");
        series2.getData().add(new XYChart.Data<String, Number>("2016", 57401.85));
        series2.getData().add(new XYChart.Data<String, Number>("2017", 41941.19));
        series2.getData().add(new XYChart.Data<String, Number>("2018", 45263.37));
        series2.getData().add(new XYChart.Data<String, Number>("2019", 117320.16));
        series2.getData().add(new XYChart.Data<String, Number>("2020", 14845.27));
        series3.setName("Imprimante");
        series3.getData().add(new XYChart.Data<String, Number>("2016", 45000.65));
        series3.getData().add(new XYChart.Data<String, Number>("2017", 44835.76));
        series3.getData().add(new XYChart.Data<String, Number>("2018", 18722.18));
        series3.getData().add(new XYChart.Data<String, Number>("2019", 17557.31));
        series3.getData().add(new XYChart.Data<String, Number>("2020", 92633.68));
		
        ObservableList<XYChart.Series> s = FXCollections.observableArrayList();
        s.add(series1);
        s.add(series2);
        s.add(series3);
		
		return s;
	}
	
	private ObservableList<XYChart.Series> getChartDataQuantity() {
		
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
		
		return s;
	}
	
	public void displayByPrice(){
		this.renewalSBchart.setData(getChartDataPrice());
	}
	
	public void displayByQuantity(){
		this.renewalSBchart.setData(getChartDataQuantity());
	}

}
