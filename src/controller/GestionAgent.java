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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Agent;
import tools.ManipInterface;
import application.excel.export.ExcelAgentListExport;
import application.excel.export.ExcelGenerator;
import application.excel.importer.ExcelAgentImport;
import application.excel.importer.ExcelImport;
import application.pdf.export.PDFAgentListExport;
import application.pdf.export.PDFGenerator;
import dao.AgentDao;

public class GestionAgent implements Initializable {

	@FXML
	private AnchorPane bodyPanel;

	@FXML
	private CheckBox checkBoxExportTable;

	@FXML
	private TableView<Agent> tableViewAgent;

	@FXML
	private TextField searchBar = new TextField();

	@FXML
	private TableColumn<Agent, String> nomCol;

	@FXML
	private TableColumn<Agent, String> prenomCol;

	@FXML
	private TableColumn<Agent, String> poleCol;

	@FXML
	private TableColumn<Agent, String> numCPCol;

	@FXML
	private TableColumn<Agent, String> telCol;

	@FXML
	private TableColumn<Agent, String> numPosteCol;

	@FXML
	private TableColumn<Agent, Agent> columnModifier;

	@FXML
	private TableColumn<Agent, Agent> columnSupprimer;


	private FXMLLoader loader;

	private List<Agent> listAgent;
	private AgentDao agentDao;
	
	private ObservableList<Agent> itemsAgent;
	private  FilteredList<Agent> filteredData;
	private  SortedList<Agent> sortedData;
	/*private int maxResult;
	private int limit;*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listAgent = new ArrayList<Agent>();
		agentDao = new AgentDao();
		
		nomCol.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getNom());
			}
		});
		
		prenomCol.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getPrenom());
			}
		});
		
		poleCol.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getPole().getNom());
			}
		});
		
		numCPCol.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getNumCP());
			}
		});
		
		telCol.setCellValueFactory(new Callback<CellDataFeatures<Agent, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Agent, String> agent) {
				return new SimpleStringProperty(agent.getValue().getTel());
			}
		});

		columnModifier.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Agent, Agent>, ObservableValue<Agent>>() {
			@Override
			public ObservableValue<Agent> call(TableColumn.CellDataFeatures<Agent, Agent> features) {
						return new ReadOnlyObjectWrapper<Agent>(features.getValue());
			}
		});

		columnModifier.setCellFactory(new Callback<TableColumn<Agent, Agent>, TableCell<Agent, Agent>>() {
					@Override
					public TableCell<Agent, Agent> call(TableColumn<Agent, Agent> personBooleanTableColumn) {
						return new TableCell<Agent, Agent>() {
							final Button button = new Button();
				
							@Override
							public void updateItem(Agent agent, boolean empty) {
								super.updateItem(agent, empty);
								if (agent != null) {
									button.setMinWidth(70);
									button.setText("Modifier");
									setGraphic(button);
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											try {
					                    		Stage stage = new Stage();
					                    		FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/EditAgent.fxml"));
					                    		Parent root = (Parent)fxmlLoader.load(); 
					                    		EditAgent controller = fxmlLoader.<EditAgent>getController();
					                    		controller.setValues(agent.getId());
					                    		stage.getIcons().add(new Image("/res/icon-sncf.jpg"));
					                    		stage.setTitle("Modifier agent");
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

		columnSupprimer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Agent, Agent>, ObservableValue<Agent>>() {
			@Override
			public ObservableValue<Agent> call(TableColumn.CellDataFeatures<Agent, Agent> features) {
				return new ReadOnlyObjectWrapper<Agent>(features.getValue());
			}
		});

		columnSupprimer.setCellFactory(new Callback<TableColumn<Agent, Agent>, TableCell<Agent, Agent>>() {
			@Override
			public TableCell<Agent, Agent> call(TableColumn<Agent, Agent> personBooleanTableColumn) {
				return new TableCell<Agent, Agent>() {
					final Button button = new Button();
					@Override
					public void updateItem(Agent agent, boolean empty) {
						super.updateItem(agent, empty);
							if (agent != null) {
								button.setMinWidth(70);
								button.setText("X");

								setGraphic(button);
								button.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										Alert alert = new Alert(AlertType.CONFIRMATION);
										alert.setTitle("Suppression agent");
										alert.setHeaderText("Confirmation");
										alert.setContentText("Voulez-vous vraiment supprimer cet agent ?");

										Optional<ButtonType> result = alert.showAndWait();
											if (result.get() == ButtonType.OK) {
												agentDao.remove(agent);
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
	        filteredData.setPredicate(agent -> {
	            if (newValue == null || newValue.trim().isEmpty()) {
	                return true;
	            }
	
	            String lowerCaseFilter = newValue.trim().toLowerCase();
	            // filtrage N°CP, Nom, Prenom, pole
	            if (agent.getNumCP().toLowerCase().contains(lowerCaseFilter)) 
	                return true; 
	             else if (agent.getNom().toLowerCase().contains(lowerCaseFilter)) 
	                return true;
	             else if(agent.getPrenom().toLowerCase().contains(lowerCaseFilter))
	            	 return true;
	             else if(agent.getPole().getNom().toLowerCase().contains(lowerCaseFilter))
	            	 return true;
	            return false; // pas de résultat au critère de recherche
	        });
	    });

	    refreshTable();

	}

	@FXML
	private void displayAddAgent(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("/view/AjoutAgent.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}

	@FXML
	private void exportTablePDF(ActionEvent event) throws IOException {
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("agents");
		fileChooser.setTitle("Save PDF");
		File file;
		file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
		if (file != null) {
			/*if (checkBoxExportTable.isSelected()) {
				pdfGenerator.generate(file, new PDFAgentListExport(listAgent));
			} else if (maxResult == listAgent.size()) {
				pdfGenerator.generate(file, new PDFAgentListExport(listAgent));
			} else {*/
				List<Agent> results = agentDao.findByAttributesLike(null);
				pdfGenerator.generate(file, new PDFAgentListExport(results));
			//}
		}
	}

	@FXML
	private void exportTableExcel(ActionEvent event) throws IOException {
		ExcelGenerator excelGenerator = new ExcelGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setInitialFileName("agents");
		fileChooser.setTitle("Save Excel");
		File file;
		file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene()
				.getWindow());
		if (file != null) {
			/*if (checkBoxExportTable.isSelected()) {
				excelGenerator.generate(file, new ExcelAgentListExport(listAgent));
			} else if (maxResult == listAgent.size()) {
				excelGenerator.generate(file, new ExcelAgentListExport(listAgent));
			} else {*/
				List<Agent> results = agentDao.findByAttributesLike(null);
				excelGenerator.generate(file, new ExcelAgentListExport(results));
			//}
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
			excelImport.importFile(file, new ExcelAgentImport(listAgent));
			for (Agent agent : listAgent) {
				if (agentDao.find(agent.getId()) == null) {
					agentDao.save(agent);
				}
			}
			refreshTable();
		}
	}

/*	private void getListAgent() {
		/*boolean isLimit = Config.getPropertie("tableau_limite").equals("yes");
		if (isLimit) {
			maxResult = agentDao.getNbResultLike(null);
			limit = Integer.parseInt(Config.getPropertie("tableau_nb_ligne"));
			if (maxResult < limit) {
				listAgent = agentDao.findByAttributesLike(null);
			} else {
				listAgent = agentDao.findByAttributesLikeWithLimits(null, 0,
						limit);
			}
		} else {
			listAgent = agentDao.findByAttributesLike(null);
			//maxResult = listAgent.size();
		//}
	}*/

	private void refreshTable() {
		
		listAgent = agentDao.findByAttributesLike(null);
		itemsAgent = FXCollections.observableArrayList(listAgent);
        filteredData = new FilteredList<>(itemsAgent, p -> true);
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewAgent.comparatorProperty());
        tableViewAgent.setItems(sortedData);
        
		/*ObservableList<Agent> items = FXCollections.observableArrayList(listAgent);
		searchTab.setItems(items);*/
		/*if (maxResult > listAgent.size()) {
			buttonNext.setDisable(false);
		} else {
			buttonNext.setDisable(true);
		}*/
	}

	/*@FXML
	private void viewMore(ActionEvent event) throws IOException {

		List<Agent> results = agentDao.findByAttributesLikeWithLimits(null,listAgent.size(), limit);
		for (Agent agent : results)
			listAgent.add(agent);
		
		refreshTable();
	}*/

}
