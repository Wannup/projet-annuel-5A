package controller;

import java.net.URL;
import java.util.ResourceBundle;

import tools.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Configuration implements Initializable {
	
	@FXML
	private TextField textFieldTableauNbLigne;

	@FXML
	private TextField textFieldDatabaseDriver;

	@FXML
	private TextField textFieldDatabaseLocation;

	@FXML
	private TextField textFieldDatabaseUser;

	@FXML
	private TextField textFieldDatabasePassword;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textFieldTableauNbLigne.setText(Config.getPropertie("tableau_nb_ligne"));
		textFieldDatabaseDriver.setText(Config.getPropertie("db_driver"));
		textFieldDatabaseLocation.setText(Config.getPropertie("db_location"));
		textFieldDatabaseUser.setText(Config.getPropertie("db_user"));
		textFieldDatabasePassword.setText(Config.getPropertie("db_password"));
	}

}
