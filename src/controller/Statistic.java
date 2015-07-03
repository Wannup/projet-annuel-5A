package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import tools.ManipInterface;

/**
 * class controller for the interface StatEtPrevision.fxml
 * */
public class Statistic implements Initializable{

	@FXML 
	private Button typeEquipement;
	
	@FXML
	private Button renewal;
	
	@FXML
	private AnchorPane graphPanel;

	private FXMLLoader loader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loader = new FXMLLoader(getClass().getResource("/view/GraphType.fxml"));
		try {
			ManipInterface.chargementBodyPanel(graphPanel, loader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void typeGraph() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GraphType.fxml"));
		ManipInterface.chargementBodyPanel(graphPanel, loader);
	}
	
	@FXML
	private void renewalTypeGraph() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GraphRenewal.fxml"));
		ManipInterface.chargementBodyPanel(graphPanel, loader);
	}
	
	@FXML
	private void nbTypeGraph() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GraphNbType.fxml"));
		ManipInterface.chargementBodyPanel(graphPanel, loader);
	}
	
	@FXML
	private void nbEquipPoleGraph() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GraphNbEquipPole.fxml"));
		ManipInterface.chargementBodyPanel(graphPanel, loader);
	}
	
	@FXML
	private void renewalPoleGraph() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GraphRenewalPole.fxml"));
		ManipInterface.chargementBodyPanel(graphPanel, loader);
	}
}
