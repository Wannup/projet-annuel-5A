package tools;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ManipInterface {
	
	public  static void chargementBodyPanel(AnchorPane bodyPanel, FXMLLoader loader) throws IOException{
		
		bodyPanel.getChildren().setAll(loader.load());
		AnchorPane.setTopAnchor(bodyPanel, (double) 0);
		AnchorPane.setTopAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setRightAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setLeftAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setBottomAnchor(bodyPanel.getChildren().get(0), (double) 0);
	}
}
