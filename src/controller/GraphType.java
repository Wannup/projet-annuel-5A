package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import model.Equipement;
import model.TypeEquipement;
import dao.EquipementDao;
import dao.TypeEquipementDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

public class GraphType implements Initializable{
	
	@FXML
	private PieChart typePchart;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
        this.typePchart.setData(getChartData());
	}
	
	private ObservableList<Data> getChartData() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		pieChartData.add(new PieChart.Data("PC Fixe", 3));
		pieChartData.add(new PieChart.Data("PC Portable", 10));
		pieChartData.add(new PieChart.Data("Imprimante", 5));

        return pieChartData;
    }

}
