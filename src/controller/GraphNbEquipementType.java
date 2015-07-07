package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.TypeEquipement;
import dao.EquipementDao;
import dao.TypeEquipementDao;

/**
 * class controller for the interface GraphNbEquipementType.fxml
 * @author Erwan LE GUYADER
 * @version 1.0
 */
public class GraphNbEquipementType implements Initializable{
	
	@FXML
	private AnchorPane bodyPanel;

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
        
        this.SBchart.setData(s);	
		
	}
	
	@FXML
	private void exportGraph() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
		fileChooser.setInitialFileName("Nombre d'équipement par type");
        fileChooser.setTitle("Enregister graphique");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	CategoryAxis xAxis = new CategoryAxis();
        	xAxis.setLabel(SBchart.getXAxis().getLabel());
        	xAxis.setCategories(FXCollections.<String>observableArrayList(((CategoryAxis)SBchart.getXAxis()).getCategories()));
        	xAxis.setSide(Side.BOTTOM);
        	NumberAxis yAxis = new NumberAxis();
        	yAxis.setLabel(SBchart.getYAxis().getLabel());
        	yAxis.setSide(Side.LEFT);
        	StackedBarChart<String, Number> chart = new StackedBarChart<>(xAxis, yAxis);
        	
        	ObservableList<XYChart.Series<String, Number>> observableList = FXCollections.observableArrayList(SBchart.getData());
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
