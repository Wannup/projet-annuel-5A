package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import tools.ManipInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
	
	@FXML
	private void typeGraph(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GraphType.fxml"));
		ManipInterface.chargementBodyPanel(graphPanel, loader);
	}
	
	@FXML
	private void renewalGraph(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/GraphRenewal.fxml"));
		ManipInterface.chargementBodyPanel(graphPanel, loader);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
