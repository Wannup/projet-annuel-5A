package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import tools.ManipInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class GestionLogiciel implements Initializable {

	@FXML
	private AnchorPane bodyPanel;
	
	private FXMLLoader loader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	private void displayAddLogiciel(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/AjoutLogiciel.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
}
