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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Agent;
import model.Equipement;
import tools.LoadingFrame;
import tools.ManipInterface;
import application.excel.export.ExcelEquipementListExport;
import application.excel.export.ExcelGenerator;
import application.excel.importer.ExcelEquipementImport;
import application.excel.importer.ExcelImport;
import application.pdf.export.PDFEquipementListExport;
import application.pdf.export.PDFGenerator;
import dao.AgentDao;
import dao.EquipementDao;

/**
 * class controller for the interface GestionEquipement.fxml
 * @author: Charly FAROT
 * @version 1.0
 * */
public class GestionEquipement implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private CheckBox checkBoxExportTable;
	
	@FXML
	private TableView<Equipement> tableViewEquipement;
	
	@FXML
	private TableColumn<Equipement, String> columnPole;
	
	@FXML
	private TableColumn<Equipement, String> columnAgent;
	
	@FXML
	private TableColumn<Equipement, String> columnType;
	
	@FXML
	private TableColumn<Equipement, String> columnCalife;
	
	@FXML
	private TableColumn<Equipement, Equipement> columnOpen;
	
	@FXML
	private TableColumn<Equipement, Equipement> columnEdit;
	
	@FXML
	private TableColumn<Equipement, Equipement> columnDelete;
	
	@FXML
	public Button fieldRefresh;
	
	@FXML
	private TextField searchBar;
	
	private FXMLLoader loader;
	
	private List<Equipement> listEquipement;
	private EquipementDao equipementDao;
	private AgentDao agentDao;
	
	private ObservableList<Equipement> itemsEquipement;
	private  FilteredList<Equipement> filteredData;
	private  SortedList<Equipement> sortedData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listEquipement = new ArrayList<Equipement>();
		equipementDao = new EquipementDao();
		agentDao = new AgentDao();
		
		fieldRefresh.setVisible(false);
		
		columnPole.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> equipement) {
				return new SimpleStringProperty(equipement.getValue().getPole().getNom());
			}
		});
				
		columnAgent.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> p) {
				if (p.getValue().getAgent() == null) {
					return new SimpleStringProperty("");
				}
				return new SimpleStringProperty(p.getValue().getAgent().getNumCP());
			}
		});
		
		columnType.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> equipement) {
				return new SimpleStringProperty(equipement.getValue().getTypeEquipement().getNom());
			}
		});
		
		columnCalife.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> equipement) {
				return new SimpleStringProperty(equipement.getValue().getNomCalife());
			}
		});
		
		columnOpen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Equipement, Equipement>, ObservableValue<Equipement>>() {
		      @Override 
		      public ObservableValue<Equipement> call(TableColumn.CellDataFeatures<Equipement, Equipement> features) {
		    	  return new ReadOnlyObjectWrapper<Equipement>(features.getValue());
		      }
		});
		 
		    columnOpen.setCellFactory(new Callback<TableColumn<Equipement, Equipement>, TableCell<Equipement, Equipement>>() {
		      @Override 
		      public TableCell<Equipement, Equipement> call(TableColumn<Equipement, Equipement> personBooleanTableColumn) {
		    	  return new TableCell<Equipement, Equipement>() {
		             
		              final Button button = new Button("Voir"); 
		                
		              @Override
		              public void updateItem(Equipement equipement, boolean empty) {
			            	super.updateItem(equipement, empty);
			                if (equipement != null) {
			                	button.setMinWidth(70);
			                	setGraphic(button);
			                	button.setOnAction(new EventHandler<ActionEvent>() {
			                    @Override 
			                    public void handle(ActionEvent event){
			                    	try {
			                    		Stage stage = new Stage();
			                    		FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/InformationEquipementPopup.fxml"));
			                    		Parent root = (Parent)fxmlLoader.load(); 
			                    		InformationEquipement controller = fxmlLoader.<InformationEquipement>getController();
			                    		controller.setValues(equipement);
			                    		stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
			                    		stage.setTitle("Information equipement");
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
		    
		    columnEdit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Equipement, Equipement>, ObservableValue<Equipement>>() {
			      @Override 
			      public ObservableValue<Equipement> call(TableColumn.CellDataFeatures<Equipement, Equipement> features) {
			    	  return new ReadOnlyObjectWrapper<Equipement>(features.getValue());
			      }
			});
			 
			    columnEdit.setCellFactory(new Callback<TableColumn<Equipement, Equipement>, TableCell<Equipement, Equipement>>() {
			      @Override 
			      public TableCell<Equipement, Equipement> call(TableColumn<Equipement, Equipement> personBooleanTableColumn) {
			    	  return new TableCell<Equipement, Equipement>() {
			             
			              final Button button = new Button("Modifier"); 
			                
			              @Override
			              public void updateItem(Equipement equipement, boolean empty) {
				            	super.updateItem(equipement, empty);
				                if (equipement != null) {
				                	button.setMinWidth(70);
				                	setGraphic(button);
				                	button.setOnAction(new EventHandler<ActionEvent>() {
				                    @Override 
				                    public void handle(ActionEvent event){
				                    	try {
				                    		Stage stage = new Stage();
				                    		FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/EditEquipement.fxml"));
				                    		Parent root = (Parent)fxmlLoader.load(); 
				                    		EditEquipement controller = fxmlLoader.<EditEquipement>getController();
				                    		controller.setValues(equipement);
				                    		controller.fieldOnMainWindows=fieldRefresh;
				                    		stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
				                    		stage.setTitle("Modiciation equipement");
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
		    columnDelete.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Equipement, Equipement>, ObservableValue<Equipement>>() {
			      @Override 
			      public ObservableValue<Equipement> call(TableColumn.CellDataFeatures<Equipement, Equipement> features) {
			    	  return new ReadOnlyObjectWrapper<Equipement>(features.getValue());
			      }
			});
			 
		    columnDelete.setCellFactory(new Callback<TableColumn<Equipement, Equipement>, TableCell<Equipement, Equipement>>() {
			      @Override 
			      public TableCell<Equipement, Equipement> call(TableColumn<Equipement, Equipement> equipementTableColumn) {
			    	  return new TableCell<Equipement, Equipement>() {
			           
			              final Button button = new Button();
			           
			              @Override
			              public void updateItem(Equipement equipement, boolean empty) {
			            	  super.updateItem(equipement, empty);
			            	  if (equipement != null) {
			            		  button.setText("X");
			            		  button.setMinWidth(70);
				                  setGraphic(button);
				                  button.setOnAction(new EventHandler<ActionEvent>() {
				                    @Override 
				                    public void handle(ActionEvent event) {
				                    	Alert alert = new Alert(AlertType.CONFIRMATION);
				                    	alert.setTitle("Suppression equipement");
				                    	alert.setHeaderText("Confirmation");
				                    	alert.setContentText("Voulez-vous vraiment supprimer cet équipement ?");
	
				                    	Optional<ButtonType> result = alert.showAndWait();
				                    	if (result.get() == ButtonType.OK){
				                    		if(equipement.getAgent() != null){
				                    			Agent agent = equipement.getAgent();
				                    			agent.getEquipements().remove(equipement);
				                    			agentDao.update(agent);
				                    		}	
				                    		equipementDao.remove(equipement);	
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
		            filteredData.setPredicate(equipement -> {
		                if (newValue == null || newValue.trim().isEmpty()) {
		                    return true;
		                }
		
		                String lowerCaseFilter = newValue.trim().toLowerCase();
		                // filtrage calife, type, valeur, pole, cpAgent
		                if (equipement.getNomCalife().toLowerCase().contains(lowerCaseFilter)) 
		                    return true; 
		                 else if (equipement.getTypeEquipement().getNom().toLowerCase().contains(lowerCaseFilter)) 
		                    return true;
		                 else if(equipement.getAgent() != null && equipement.getAgent().getNumCP().toLowerCase().contains(lowerCaseFilter))
		                	 return true;
		                 else if(equipement.getPole().getNom().toLowerCase().contains(lowerCaseFilter))
		                	 return true;
		                return false; // pas de résultat au critère de recherche
		            });
		    	});
		    
        refreshTable();
	}
	
	@FXML
	private void displayAddEquipment() throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/AjoutEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void exportTablePDF() {
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("equipements");
        fileChooser.setTitle("Save PDF");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	if (checkBoxExportTable.isSelected()) {
        		pdfGenerator.generate(file, new PDFEquipementListExport(this.tableViewEquipement));
    		} else {			
    			pdfGenerator.generate(file, new PDFEquipementListExport(this.listEquipement));
    		}
        }
	}
	
	@FXML
	private void exportTableExcel(){
		ExcelGenerator excelGenerator = new ExcelGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setInitialFileName("equipements");
        fileChooser.setTitle("Save Excel");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	if (checkBoxExportTable.isSelected()) {
        		excelGenerator.generate(file, new ExcelEquipementListExport(tableViewEquipement));
    		} else {
    			excelGenerator.generate(file, new ExcelEquipementListExport(listEquipement));
    		}
        }
	}
	
	@FXML
	private void importExcel() {
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
	
	@FXML
	private void refreshTable() {
		
		listEquipement = equipementDao.findByAttributesLike(null);
		itemsEquipement = FXCollections.observableArrayList(listEquipement);
        filteredData = new FilteredList<>(itemsEquipement, p -> true);
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewEquipement.comparatorProperty());
        tableViewEquipement.setItems(null); 
        tableViewEquipement.layout(); 
        tableViewEquipement.setItems(sortedData);
		fieldRefresh.setVisible(false);
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
        	excelImport.importFile(file, new ExcelEquipementImport(listEquipement, errors, loadingFrame));
			for (Equipement equipement : listEquipement) {
				if (equipementDao.find(equipement.getIdEquipement()) == null) {
					equipementDao.save(equipement);
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
