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
		System.out.println("test");
		
        this.typePchart.setData(getChartData());
	}
	
	private ObservableList<Data> getChartData() {
		TypeEquipementDao teDao = new TypeEquipementDao();
		EquipementDao eDao = new EquipementDao();
		List<TypeEquipement> typeList = teDao.findByAttributesLike(null);
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for(int i=0; i<typeList.size(); i++){
			List<Equipement> nbType = eDao.searchWithAttributes(typeList.get(i).getNom());
			pieChartData.add(new PieChart.Data(typeList.get(i).getNom(), nbType.size()));
		}

        return pieChartData;
    }

}
