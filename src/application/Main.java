package application;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import application.database.DatabaseConnection;

/**
 * @author: Erwan LE GUYADER
 * @version 1.0
 */
public class Main extends Application {
	
	public static final String SPLASH_IMAGE = "/res/logo-sncf.jpg";
	private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage primaryStage;
    private static final int SPLASH_WIDTH = 472;
    private static final int SPLASH_HEIGHT = 298;
	
	@Override
    public void init() {
        ImageView splash = new ImageView(new Image(SPLASH_IMAGE));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH);
        progressText = new Label("Chargement . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setEffect(new DropShadow());
    }
	
	@Override
	public void start(Stage initStage) {
		 
		final Task<Boolean> task = new Task<Boolean>() {
			@Override
	        protected Boolean call() throws InterruptedException {
	            updateMessage("Chargement des données . . .");
	            DatabaseConnection.startConnection();
	            return true;
	        }
	    };
		
		showSplash(initStage, task, () -> showMainStage());
        new Thread(task).start();
	}
	
	private void showSplash(final Stage initStage, Task<?> task, InitCompletionHandler initCompletionHandler) {
	        
		progressText.textProperty().bind(task.messageProperty());
	    loadProgress.progressProperty().bind(task.progressProperty());
	   
	    task.stateProperty().addListener((observableValue, oldState, newState) -> {
	    
		    if (newState == Worker.State.SUCCEEDED) {
		    	loadProgress.progressProperty().unbind();
		        loadProgress.setProgress(1);
		        initStage.toFront();
		        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
		        fadeSplash.setFromValue(1.0);
		        fadeSplash.setToValue(0.0);
		        fadeSplash.setOnFinished(actionEvent -> initStage.hide());
		        fadeSplash.play();	 
		        initCompletionHandler.complete();
		    }
	     });
	 
	     Scene splashScene = new Scene(splashLayout);
	     initStage.initStyle(StageStyle.UNDECORATED);
	     final Rectangle2D bounds = Screen.getPrimary().getBounds();
	     initStage.setScene(splashScene);
	     initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
	     initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
	     initStage.show();
	}
	 
	private void showMainStage() {
		try {	
			// GUI
			Parent root = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
			Scene scene = new Scene(root);
			primaryStage = new Stage();
			primaryStage.setTitle("LGPI SNCF");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/res/icon-sncf.jpg")));
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		        @Override
				public void handle(WindowEvent we) {
		              DatabaseConnection.closeConnection();
		        }
			}); 
		} catch(Exception e) {
			throw new RuntimeException(e);
		}	
		
	}
	
	public static void main(String[] args) {		
		launch(args);
	}
	
	public interface InitCompletionHandler {
        public void complete();
    }
}