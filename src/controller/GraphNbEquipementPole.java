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
import model.Agent;
import model.Pole;
import model.TypeEquipement;
import dao.AgentDao;
import dao.EquipementDao;
import dao.PoleDao;
import dao.TypeEquipementDao;


/**
 * class controller for the interface GraphNbEquipementPole.fxml
 * @author Erwan LE GUYADER
 * @version 1.0
 */
public class GraphNbEquipementPole implements Initializable{
	
	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private StackedBarChart SBchart;
	
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc = new StackedBarChart<String, Number>(xAxis, yAxis);
    
    private PoleDao poleDao;
    private EquipementDao equipementDao;
    private List<Pole> lstPole;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.poleDao = new PoleDao();
		this.equipementDao = new EquipementDao();
		this.lstPole = poleDao.findByAttributesLike(null);
		ArrayList<String> lstPoleName = new ArrayList<String>();
		
		xAxis.setLabel("Pole");
		ObservableList<XYChart.Series> s = FXCollections.observableArrayList();
		
		XYChart.Series<String, Number> nbEquip = new XYChart.Series<String, Number>();
		nbEquip.setName("Equipement(s)");
		for(int i = 0; i< this.lstPole.size(); i++){
			
			lstPoleName.add(this.lstPole.get(i).getNom());
						
			int count = equipementDao.getEquipementByPole(this.lstPole.get(i)).size();
			if(count > 0){
				nbEquip.getData().add(new XYChart.Data<String, Number>(this.lstPole.get(i).getNom(), count));			
				
			}
		}
		s.add(nbEquip);	
		xAxis.setCategories(FXCollections.<String>observableArrayList(FXCollections.observableArrayList(lstPoleName)));
        yAxis.setLabel("Quantité");
        
        this.SBchart.setData(s);			
	}
	
	@FXML
	private void exportGraph() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
		fileChooser.setInitialFileName("Nombre d'équipement par pôle");
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
    
   /* private AgentDao agentDao;
    private PoleDao poleDao;
    private EquipementDao equipementDao;
    private List<Agent> lstAgent;
    private List<Pole> lstPole;
    private List<TypeEquipement> lstTypeEquipement;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.agentDao = new AgentDao();
		this.poleDao = new PoleDao();
		this.equipementDao = new EquipementDao();
		this.lstAgent = agentDao.findByAttributesLike(null);
		this.lstPole = poleDao.findByAttributesLike(null);
		ArrayList<String> lstPoleName = new ArrayList<String>();
		
		xAxis.setLabel("Pôle");
		ObservableList<XYChart.Series> s = FXCollections.observableArrayList();
		
		XYChart.Series<String, Number> nbEquip = new XYChart.Series<String, Number>();
		nbEquip.setName("Equipement(s)");
		
		
		for(int i = 0; i< this.lstPole.size(); i++){
			this.lstAgent = agentDao.searchWithAttributes(this.lstPole.get(i).getNom());
			lstPoleName.add(this.lstPole.get(i).getNom());
			for(int j = 0; j< this.lstAgent.size(); j++){			
				int count = equipementDao.getEquipementByPole((this.lstTypeEquipement.get(i)).size());
				if(count > 0){
					nbEquip.getData().add(new XYChart.Data<String, Number>(this.lstTypeEquipement.get(i).getNom(), count));			
					
				}
			}
		}
		
		
		s.add(nbEquip);	
		xAxis.setCategories(FXCollections.<String>observableArrayList(FXCollections.observableArrayList(lstPoleName)));
        yAxis.setLabel("Quantité");
        
        this.SBchart.setData(s);
		
	}*/
	
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
