package application;

import application.database.DatabaseConnection;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main est la classe principal qui permet de lancer l'apllication.
 * 
 * @version 1.0
 */
public class Main extends Application {
	
	/**
	 * Lance l'application
	 *
	 * @param primaryStage
	 *     Stage
	 * @see Stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// GUI
			Parent root = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("LGPI SNCF");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../res/icon-sncf.jpg")));
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              System.out.println("Application is closing");
		              DatabaseConnection.closeConnection();
		          }
		      }); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		DatabaseConnection.startConnection();
		launch(args);
	}
}