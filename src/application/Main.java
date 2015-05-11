package application;

import application.database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Agent;
import dao.AgentDao;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			// GUI
			Parent root = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("LGPI SNCF");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../res/icon-sncf.jpg")));
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		/*AgentDao agentDao = new AgentDao();
		 
		DatabaseConnection.startConnection();
 
        try {
            Agent agent = new Agent();
            agent.setNom("Toto");;
            agent.setNumCP("123456");;
 
            agentDao.save(agent);
 
            // It was the first saved dog, so its id is 1
            Agent persistedAgent = agentDao.find(1);
 
            System.out.println("Name: " + persistedAgent.getNom());
            System.out.println("Weight: " + persistedAgent.getNumCP());
        } catch (Exception e) {
            System.out.println("Ops, something happen: " + e.getMessage());
            e.printStackTrace();
        }finally{
        	DatabaseConnection.closeConnection();
        }*/
	}
}