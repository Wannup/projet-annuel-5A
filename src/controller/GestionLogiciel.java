package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Logiciel;
import tools.LoadingFrame;
import tools.ManipInterface;
import application.excel.export.ExcelGenerator;
import application.excel.export.ExcelLogicielListExport;
import application.excel.importer.ExcelImport;
import application.excel.importer.ExcelLogicielImport;
import application.pdf.export.PDFGenerator;
import application.pdf.export.PDFLogicielListExport;
import dao.LogicielDao;

/**
 * class controller for the interface GestionLogiciel.fxml
 * @author: Charly Farot
 * @version 1.0
 * */
public class GestionLogiciel implements Initializable {

	@FXML
	private AnchorPane bodyPanel;

	@FXML
	private CheckBox checkBoxExportTable;

	@FXML
	private TableView<Logiciel> tableViewLogiciel;

	@FXML
	private TableColumn<Logiciel, String> columnLibelle;

	@FXML
	private TableColumn<Logiciel, String> columnPrix;

	@FXML
	private TableColumn<Logiciel, String> columnLicenceNumber;

	@FXML
	private TableColumn<Logiciel, Logiciel> columnModifier;

	@FXML
	private TableColumn<Logiciel, Logiciel> columnSupprimer;

	@FXML
	private TextField searchBar;

	private FXMLLoader loader;

	private List<Logiciel> listLogiciel;
	private LogicielDao logicielDao;
	
	private ObservableList<Logiciel> itemsLogiciel;
	private  FilteredList<Logiciel> filteredData;
	private  SortedList<Logiciel> sortedData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listLogiciel = new ArrayList<Logiciel>();
		logicielDao = new LogicielDao();
		
		columnLibelle.setCellValueFactory(new Callback<CellDataFeatures<Logiciel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Logiciel, String> logiciel) {
				return new SimpleStringProperty(logiciel.getValue().getNom());
			}
		});
		
		columnPrix.setCellValueFactory(new Callback<CellDataFeatures<Logiciel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Logiciel, String> logiciel) {
				return new SimpleStringProperty(String.valueOf(logiciel.getValue().getPrix()));
			}
		});
		
		columnLicenceNumber.setCellValueFactory(new Callback<CellDataFeatures<Logiciel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Logiciel, String> logiciel) {
				return new SimpleStringProperty(String.valueOf(logiciel.getValue().getLicenceNumber()));
			}
		});

		columnModifier.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Logiciel, Logiciel>, ObservableValue<Logiciel>>() {
			@Override
			public ObservableValue<Logiciel> call(TableColumn.CellDataFeatures<Logiciel, Logiciel> features) {
				return new ReadOnlyObjectWrapper<Logiciel>(features.getValue());
			}
		});

		columnModifier.setCellFactory(new Callback<TableColumn<Logiciel, Logiciel>, TableCell<Logiciel, Logiciel>>() {
			@Override
			public TableCell<Logiciel, Logiciel> call(TableColumn<Logiciel, Logiciel> logicielTableColumn) {
				return new TableCell<Logiciel, Logiciel>() {
					final Button button = new Button();

						@Override
						public void updateItem(Logiciel logiciel, boolean empty) {
								super.updateItem(logiciel, empty);
								if (logiciel != null) {
									button.setText("Modifier");
									button.setMinWidth(70);
									setGraphic(button);
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											try {
					                    		Stage stage = new Stage();
					                    		FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/EditLogiciel.fxml"));
					                    		Parent root = (Parent)fxmlLoader.load(); 
					                    		EditLogiciel controller = fxmlLoader.<EditLogiciel>getController();
					                    		controller.setValues(logiciel);
					                    		stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
					                    		stage.setTitle("Modifier logiciel");
					                    		Scene scene = new Scene(root); 
							                    stage.setScene(scene);    
							                    stage.show();
						                    } catch (IOException e) {
												e.printStackTrace();
											}	 
										}
									});
								} else {
									setGraphic(null);
								}
							}
						};
					}
				});

		columnSupprimer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Logiciel, Logiciel>, ObservableValue<Logiciel>>() {
			@Override
			public ObservableValue<Logiciel> call(TableColumn.CellDataFeatures<Logiciel, Logiciel> features) {
				return new ReadOnlyObjectWrapper<Logiciel>(features.getValue());
			}
		});

		columnSupprimer.setCellFactory(new Callback<TableColumn<Logiciel, Logiciel>, TableCell<Logiciel, Logiciel>>() {
					@Override
					public TableCell<Logiciel, Logiciel> call(TableColumn<Logiciel, Logiciel> logicielBooleanTableColumn) {
						return new TableCell<Logiciel, Logiciel>() {
							final Button button = new Button();
							{
								button.setMinWidth(70);
							}

							@Override
							public void updateItem(Logiciel logiciel, boolean empty) {
								super.updateItem(logiciel, empty);
								if (logiciel != null) {
									button.setText("X");

									setGraphic(button);
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											Alert alert = new Alert(AlertType.CONFIRMATION);
											alert.setTitle("Suppression logiciel");
											alert.setHeaderText("Confirmation");
											alert.setContentText("Voulez-vous vraiment supprimer ce logiciel ?");

											Optional<ButtonType> result = alert.showAndWait();
											if (result.get() == ButtonType.OK) {
												logicielDao.remove(logiciel);
												
												refreshTable();
											}
										}
									});
								} else {
									setGraphic(null);
								}
							}
						};
					}
				});

		searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
	        filteredData.setPredicate(logiciel -> {
	            if (newValue == null || newValue.trim().isEmpty()) {
	                return true;
	            }
	
	            String lowerCaseFilter = newValue.trim().toLowerCase();
	            // filtrage Nom, Num Licence, prix licence
	            if (logiciel.getNom().toLowerCase().contains(lowerCaseFilter)) 
	                return true; 
	             else if (logiciel.getLicenceNumber().toLowerCase().contains(lowerCaseFilter)) 
	                return true;
	             else if(String.valueOf(logiciel.getPrix()).toLowerCase().contains(lowerCaseFilter))
	            	 return true;
	            return false; // pas de résultat au critère de recherche
	        });
	    });
		
		refreshTable();
	}

	@FXML
	private void displayAddLogiciel() throws IOException {
		loader = new FXMLLoader(getClass().getResource("/view/AjoutLogiciel.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}

	@FXML
	private void searchLogiciel() {
		if (!searchBar.getText().isEmpty()) {
			logicielDao = new LogicielDao();
			listLogiciel = logicielDao.searchWithAttributes(searchBar.getText());
			refreshTable();
		}
	}

	@FXML
	private void exportTablePDF(){
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("logiciels");
		fileChooser.setTitle("Save PDF");
		File file;
		file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
		if (file != null) {
			if (checkBoxExportTable.isSelected()) {
				pdfGenerator.generate(file, new PDFLogicielListExport(this.tableViewLogiciel));
			} else {
				pdfGenerator.generate(file, new PDFLogicielListExport(listLogiciel));	
			}
		}
	}

	@FXML
	private void exportTableExcel(){
		ExcelGenerator excelGenerator = new ExcelGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setInitialFileName("logiciels");
		fileChooser.setTitle("Save Excel");
		File file;
		file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
		if (file != null) {
			if (checkBoxExportTable.isSelected()) {
				excelGenerator.generate(file, new ExcelLogicielListExport(tableViewLogiciel));
			} else {
				excelGenerator.generate(file, new ExcelLogicielListExport(listLogiciel));
			}
		}
	}

	@FXML
	private void importExcel(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setTitle("Load Excel");
		File file;
		file = fileChooser.showOpenDialog(bodyPanel.getParent().getScene().getWindow());
		if (file != null) {
			LoadingFrame loadingFrame = new LoadingFrame(bodyPanel.getParent().getScene().getWindow(), LoadingFrame.PROGRESS_INDICATOR);
			loadingFrame.setText("Import en cours, veuillez patientez...");
			loadingFrame.show();
			ImportExcelAction importExcelAction = new ImportExcelAction(file, loadingFrame);
			importExcelAction.start();
		}
	}

	private void refreshTable() {
		
		listLogiciel = logicielDao.findByAttributesLike(null);
		itemsLogiciel = FXCollections.observableArrayList(listLogiciel);
        filteredData = new FilteredList<>(itemsLogiciel, p -> true);
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewLogiciel.comparatorProperty());
        tableViewLogiciel.setItems(null); 
        tableViewLogiciel.layout(); 
        tableViewLogiciel.setItems(sortedData);
	}
	

	private void showAlert (List<String> errors) {
		if (errors.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Import Excel");
			alert.setHeaderText(null);
			alert.setContentText("L'import a été éffectué avec succès.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Import Excel");
			alert.setHeaderText("L'import a été éffectué avec succès.");
			alert.setContentText("Certaine ligne n'ont pas été correctement importer");

			String newline = System.getProperty("line.separator");
			String message = "";
			for (String string : errors) {
				message += string + newline;
			}

			TextArea textArea = new TextArea(message);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(textArea, 0, 0);

			alert.getDialogPane().setExpandableContent(expContent);

			alert.showAndWait();
			
		}
	}
	
	public class ImportExcelAction extends Thread {
		
		private File file;
		private LoadingFrame loadingFrame;
		
		public ImportExcelAction (File file, LoadingFrame loadingFrame) {
			this.file = file;
			this.loadingFrame = loadingFrame;
		}

		@Override
		public void run() {
			List<String> errors = new ArrayList<>();
			ExcelImport excelImport = new ExcelImport();
			excelImport.importFile(file, new ExcelLogicielImport(listLogiciel, errors, loadingFrame));
			for (Logiciel logiciel : listLogiciel) {
				if (logicielDao.find(logiciel.getId()) == null) {
					logicielDao.save(logiciel);
				}
			}
			refreshTable();
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					showAlert(errors);
					loadingFrame.close();
				}
			});
			
		}
		
	}
}
