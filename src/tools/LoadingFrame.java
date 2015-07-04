package tools;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * Class LoadingFrame.
 * @author Mike FIARI
 * @version 1.0
 */
public class LoadingFrame {
	
	public final static int PROGRESS_INDICATOR = 1;
	
	private Stage stage;
	private ProgressIndicator progressIndicator;
	private HBox hb;
	
	public LoadingFrame (Window parent, int type) {
		progressIndicator = new ProgressIndicator();
		stage = new Stage();
		stage.centerOnScreen();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(parent);
		hb = new HBox();
	    hb.setSpacing(5);
	    hb.setAlignment(Pos.CENTER);
		stage.initStyle(StageStyle.UNDECORATED);
		
	}
	
	public void setText (String message) {
		Text text = new Text(message);
		hb.getChildren().add(text);
	}
	
	public void setProgress (double value) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				progressIndicator.setProgress(value);
			}
		});
		
	}
	
	public void show () {
		hb.getChildren().add(progressIndicator);
		stage.setScene(new Scene(hb));
		stage.show();
	}
	
	public void close () {
		stage.close();
	}

}
