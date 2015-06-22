package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import model.TypeEquipement;
import dao.EquipementDao;
import dao.TypeEquipementDao;

public class GraphType implements Initializable{
	
	@FXML
	private PieChart typePchart;
	
	private TypeEquipementDao typeEquipementDao;
	private EquipementDao equipementDao;
	
	private List<TypeEquipement> lstTypeEquipement;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.typeEquipementDao = new TypeEquipementDao();
		this.equipementDao = new EquipementDao();
		this.lstTypeEquipement = typeEquipementDao.findByAttributesLike(null);
        this.typePchart.setData(getChartData());
	}
	
	private ObservableList<Data> getChartData() {
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		
		for(int i = 0; i< this.lstTypeEquipement.size(); i++){
			String nomType = this.lstTypeEquipement.get(i).getNom();
			int count = equipementDao.getEquipementByType(this.lstTypeEquipement.get(i)).size();
			pieChartData.add(new PieChart.Data(nomType, count));
		}

        return pieChartData;
    }

}
