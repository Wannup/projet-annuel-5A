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

public class GraphNbEquipementPole implements Initializable{
	
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
		/*this.typeEquipementDao = new TypeEquipementDao();
		this.equipementDao = new EquipementDao();
		this.lstTypeEquipement = typeEquipementDao.findByAttributesLike(null);
		ArrayList<String> lstEquipName = new ArrayList<String>();
		
		xAxis.setLabel("Type d'équipment");
		ObservableList<XYChart.Series> s = FXCollections.observableArrayList();
		
		XYChart.Series<String, Number> nbEquip = new XYChart.Series<String, Number>();
		nbEquip.setName("Equipement(s)");
		for(int i = 0; i< this.lstTypeEquipement.size(); i++){
			
			lstEquipName.add(this.lstTypeEquipement.get(i).getNom());
						
			int count = equipementDao.getEquipementByType(this.lstTypeEquipement.get(i)).size();
			if(count > 0){
				nbEquip.getData().add(new XYChart.Data<String, Number>(this.lstTypeEquipement.get(i).getNom(), count));			
				
			}
		}
		s.add(nbEquip);	
		xAxis.setCategories(FXCollections.<String>observableArrayList(FXCollections.observableArrayList(lstEquipName)));
        yAxis.setLabel("Quantité");
        
        this.SBchart.setData(s);	*/
		
	}
}
