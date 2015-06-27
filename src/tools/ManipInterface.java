package tools;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManipInterface {
	
	public static void chargementBodyPanel(AnchorPane bodyPanel, FXMLLoader loader) throws IOException{
		
		bodyPanel.getChildren().setAll((Node)loader.load());
		AnchorPane.setTopAnchor(bodyPanel, (double) 0);
		AnchorPane.setTopAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setRightAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setLeftAnchor(bodyPanel.getChildren().get(0), (double) 0);
		AnchorPane.setBottomAnchor(bodyPanel.getChildren().get(0), (double) 0);
	}
	
	public static void newWindow(String title, Parent root){
		Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
        stage.setScene(new Scene(root));
        stage.show();
	}
}
