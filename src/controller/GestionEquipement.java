package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Equipement;
import tools.Config;
import tools.ManipInterface;
import application.excel.export.ExcelEquipementListExport;
import application.excel.export.ExcelGenerator;
import application.excel.importer.ExcelEquipementImport;
import application.excel.importer.ExcelImport;
import application.pdf.export.PDFEquipementListExport;
import application.pdf.export.PDFGenerator;
import dao.EquipementDao;

public class GestionEquipement implements Initializable{

	@FXML
	private AnchorPane bodyPanel;
	
	@FXML
	private CheckBox checkBoxExportTable;
	
	@FXML
	private TableView<Equipement> tableViewEquipement;
	
	@FXML
	private TableColumn<Equipement, String> columnDateGarantie;
	
	@FXML
	private TableColumn<Equipement, String> columnPrix;
	
	@FXML
	private TableColumn<Equipement, String> columnAgent;
	
	@FXML
	private TableColumn<Equipement, String> columnType;
	
	@FXML
	private TableColumn<Equipement, String> columnCalife;
	
	@FXML
	private TableColumn<Equipement, Equipement> columnModifier;
	
	@FXML
	private TableColumn<Equipement, Equipement> columnSupprimer;
	
	@FXML
	private Button buttonNext;
	
	@FXML
	private TextField searchBar = new TextField();
	
	private FXMLLoader loader;
	
	private List<Equipement> listEquipement;
	private EquipementDao equipementDao;
	private int maxResult;
	private int limit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listEquipement = new ArrayList<Equipement>();
		
		columnDateGarantie.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> equipement) {
				return new SimpleStringProperty(equipement.getValue().getDateGarantie());
			}
		});
		 
		columnPrix.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> equipement) {
				return new SimpleStringProperty(String.valueOf(equipement.getValue().getPrix()));
			}
		});
				
		columnAgent.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> p) {
				if (p.getValue().getAgent() == null) {
					return new SimpleStringProperty("");
				}
				return new SimpleStringProperty(p.getValue().getAgent().getNumCP());
			}
		});
		
		columnType.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> equipement) {
				return new SimpleStringProperty(equipement.getValue().getTypeEquipement().getNom());
			}
		});
		
		columnCalife.setCellValueFactory(new Callback<CellDataFeatures<Equipement, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Equipement, String> equipement) {
				return new SimpleStringProperty(equipement.getValue().getCalife());
			}
		});
		
		columnModifier.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Equipement, Equipement>, ObservableValue<Equipement>>() {
		      @Override 
		      public ObservableValue<Equipement> call(TableColumn.CellDataFeatures<Equipement, Equipement> features) {
		    	  return new ReadOnlyObjectWrapper<Equipement>(features.getValue());
		      }
		});
		 
		    columnModifier.setCellFactory(new Callback<TableColumn<Equipement, Equipement>, TableCell<Equipement, Equipement>>() {
		      @Override 
		      public TableCell<Equipement, Equipement> call(TableColumn<Equipement, Equipement> personBooleanTableColumn) {
		    	  return new TableCell<Equipement, Equipement>() {
		             
		              final Button button = new Button("Voir"); 
		                
		              public void updateItem(Equipement equipement, boolean empty) {
		                super.updateItem(equipement, empty);
		                if (equipement != null) {
		                	 button.setMinWidth(70);
		                	//buttonGraphic.setImage(Image);

		                  setGraphic(button);
		                  button.setOnAction(new EventHandler<ActionEvent>() {
		                    @Override 
		                    public void handle(ActionEvent event){
		                    	try {
		                    		Stage stage = new Stage();
		                    		FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/InformationEquipementPopup.fxml"));
		                    		Parent root = (Parent)fxmlLoader.load(); 
		                    		InformationEquipement controller = fxmlLoader.<InformationEquipement>getController();
		                    		controller.setValues(equipement.getId());
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
		    
		    columnSupprimer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Equipement, Equipement>, ObservableValue<Equipement>>() {
			      @Override 
			      public ObservableValue<Equipement> call(TableColumn.CellDataFeatures<Equipement, Equipement> features) {
			    	  return new ReadOnlyObjectWrapper<Equipement>(features.getValue());
			      }
			});
			 
		    columnSupprimer.setCellFactory(new Callback<TableColumn<Equipement, Equipement>, TableCell<Equipement, Equipement>>() {
			      @Override 
			      public TableCell<Equipement, Equipement> call(TableColumn<Equipement, Equipement> personBooleanTableColumn) {
			    	  return new TableCell<Equipement, Equipement>() {
			              //final ImageView buttonGraphic = new ImageView();
			              final Button button = new Button(); {
			             //   button.setGraphic(buttonGraphic);
			                button.setMinWidth(70);
			              }
			              public void updateItem(Equipement equipement, boolean empty) {
			                super.updateItem(equipement, empty);
			                if (equipement != null) {
			                	button.setText("X");
			                	//buttonGraphic.setImage(Image); 

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
			                    	    equipementDao.remove(equipement);
			                    	    getListEquipement();
			                    		refreshTable ();
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
		    
		equipementDao = new EquipementDao();
		getListEquipement();
		refreshTable ();
	}
	
	@FXML
	private void searchEquipement(ActionEvent event) {
		if (!searchBar.getText().isEmpty()) {
			equipementDao = new EquipementDao();
			listEquipement = equipementDao.searchWithAttributes(searchBar.getText());
			refreshTable ();
		}
	}
	
	@FXML
	private void displayAddEquipment(ActionEvent event) throws IOException{
		loader = new FXMLLoader(getClass().getResource("/view/AjoutEquipement.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}
	
	@FXML
	private void exportTablePDF(ActionEvent event) throws IOException {
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("equipements");
        fileChooser.setTitle("Save PDF");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	if (checkBoxExportTable.isSelected()) {
        		pdfGenerator.generate(file, new PDFEquipementListExport(listEquipement));
    		} else if (maxResult == listEquipement.size()) {
    			pdfGenerator.generate(file, new PDFEquipementListExport(listEquipement));
    		} else {			
    			List<Equipement> results = equipementDao.findByAttributesLike(null);
    			pdfGenerator.generate(file, new PDFEquipementListExport(results));
    		}
        }
	}
	
	@FXML
	private void exportTableExcel(ActionEvent event) throws IOException {
		ExcelGenerator excelGenerator = new ExcelGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setInitialFileName("equipements");
        fileChooser.setTitle("Save Excel");
        File file;
        file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	if (checkBoxExportTable.isSelected()) {
        		excelGenerator.generate(file, new ExcelEquipementListExport(listEquipement));
    		} else if (maxResult == listEquipement.size()) {
    			excelGenerator.generate(file, new ExcelEquipementListExport(listEquipement));
    		} else {
    			List<Equipement> results = equipementDao.findByAttributesLike(null);
    			excelGenerator.generate(file, new ExcelEquipementListExport(results));
    		}
        }
	}
	
	@FXML
	private void importExcel(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
        fileChooser.setTitle("Load Excel");
        File file;
        file = fileChooser.showOpenDialog(bodyPanel.getParent().getScene().getWindow());
        if (file != null) {
        	ExcelImport excelImport = new ExcelImport();
        	excelImport.importFile(file, new ExcelEquipementImport(listEquipement));
			for (Equipement equipement : listEquipement) {
				if (equipementDao.find(equipement.getId()) == null) {
					equipementDao.save(equipement);
				}
			}
        	refreshTable();
        }
	}
	
	private void getListEquipement(){
		
	    boolean isLimit = Config.getPropertie("tableau_limite").equals("yes");
	    if (isLimit) {
		    maxResult = equipementDao.getNbResultLike(null);
		    limit = Integer.parseInt(Config.getPropertie("tableau_nb_ligne"));
		    if (maxResult < limit) {
		    	listEquipement = equipementDao.findByAttributesLike(null);
		    } else {
		    	listEquipement = equipementDao.findByAttributesLikeWithLimits(null, 0, limit);
		    }
	    } else {
	    	listEquipement = equipementDao.findByAttributesLike(null);
	    	maxResult = listEquipement.size();
	    }
	}
	
	private void refreshTable () {
		ObservableList<Equipement> items = FXCollections.observableArrayList(listEquipement);
		tableViewEquipement.setItems(items);
		if (maxResult > listEquipement.size()) {
			buttonNext.setDisable(false);
		} else {
			buttonNext.setDisable(true);
		}
	}
	
	@FXML
	private void viewMore(ActionEvent event) throws IOException {
		List<Equipement> results = equipementDao.findByAttributesLikeWithLimits(null, listEquipement.size(), limit);
		for (Equipement equipement : results) {
			listEquipement.add(equipement);
		}
		refreshTable ();
	}

}
