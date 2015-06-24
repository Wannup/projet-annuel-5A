package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import tools.Config;
/**
 * class controller for the interface Configuration.fxml
 * */
public class Configuration implements Initializable {
	
	@FXML
	private CheckBox checkBoxTableauNbLigne;
	
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
	
	@FXML 
	private TextField textFieldType;
	
	@FXML
	private TextField textFieldNbMonth;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String limiteTableau = Config.getPropertie("tableau_limite");
		if ("yes".equals(limiteTableau)) {
			checkBoxTableauNbLigne.setSelected(true);
		} else {
			textFieldTableauNbLigne.setDisable(true);
		}
		textFieldTableauNbLigne.setText(Config.getPropertie("tableau_nb_ligne"));
		textFieldDatabaseDriver.setText(Config.getPropertie("db_driver"));
		textFieldDatabaseLocation.setText(Config.getPropertie("db_location"));
		textFieldDatabaseUser.setText(Config.getPropertie("db_user"));
		textFieldDatabasePassword.setText(Config.getPropertie("db_password"));
	}
	
	@FXML
	private void changeTableauLimite() throws IOException{		
		if (checkBoxTableauNbLigne.isSelected()) {
			textFieldTableauNbLigne.setDisable(false);
		} else {
			textFieldTableauNbLigne.setDisable(true);
		}
	}
	
	@FXML
	private void saveConfig() throws IOException {
		String tableau_limite = checkBoxTableauNbLigne.isSelected() ? "yes" : "no";
		Config.modifyProperties("tableau_limite", tableau_limite);
		Config.modifyProperties("tableau_nb_ligne", textFieldTableauNbLigne.getText());
		Config.modifyProperties("db_driver", textFieldDatabaseDriver.getText());
		Config.modifyProperties("db_location", textFieldDatabaseLocation.getText());
		Config.modifyProperties("db_user", textFieldDatabaseUser.getText());
		Config.modifyProperties("db_password", textFieldDatabasePassword.getText());
	}

}
