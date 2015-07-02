package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.TypeEquipement;
import dao.EquipementDao;
import dao.TypeEquipementDao;

/**
 * class controller for the interface GraphType.fxml
 * */
public class GraphType implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
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
	
	@FXML
	private void exportGraph(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
		fileChooser.setInitialFileName("type d'equipement");
        fileChooser.setTitle("Enregister graphique");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	PieChart chart = new PieChart(getChartData());
            Scene scene = new Scene(chart);
            WritableImage snapShot = scene.snapshot(null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", file);
        }
		
	}

}
