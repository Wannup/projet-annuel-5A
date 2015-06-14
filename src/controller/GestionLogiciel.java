package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Logiciel;
import tools.Config;
import tools.ManipInterface;
import application.excel.export.ExcelGenerator;
import application.excel.export.ExcelLogicielListExport;
import application.excel.importer.ExcelImport;
import application.excel.importer.ExcelLogicielImport;
import application.pdf.export.PDFGenerator;
import application.pdf.export.PDFLogicielListExport;
import dao.LogicielDao;

public class GestionLogiciel implements Initializable {

	@FXML
	private AnchorPane bodyPanel;

	@FXML
	private CheckBox checkBoxExportTable;

	@FXML
	private TableView<Logiciel> tableLogiciel = new TableView<Logiciel>();

	@FXML
	private TableColumn<Logiciel, String> columnLibelle;

	@FXML
	private TableColumn<Logiciel, Double> columnPrix;

	@FXML
	private TableColumn<Logiciel, String> columnLicenceNumber;

	@FXML
	private TableColumn<Logiciel, Logiciel> columnModifier;

	@FXML
	private TableColumn<Logiciel, Logiciel> columnSupprimer;

	@FXML
	private Button buttonNext;

	@FXML
	private TextField searchBar = new TextField();

	private FXMLLoader loader;

	private List<Logiciel> list;
	private LogicielDao logicielDao;
	private int maxResult;
	private int limit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		list = new ArrayList<>();
		columnLibelle.setCellValueFactory(new PropertyValueFactory<Logiciel, String>("nom"));
		columnPrix.setCellValueFactory(new PropertyValueFactory<Logiciel, Double>("prix"));
		columnLicenceNumber.setCellValueFactory(new PropertyValueFactory<Logiciel, String>("number"));

		columnModifier.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Logiciel, Logiciel>, ObservableValue<Logiciel>>() {
			@Override
			public ObservableValue<Logiciel> call(TableColumn.CellDataFeatures<Logiciel, Logiciel> features) {
				return new ReadOnlyObjectWrapper<Logiciel>(features.getValue());
			}
		});

		columnModifier.setCellFactory(new Callback<TableColumn<Logiciel, Logiciel>, TableCell<Logiciel, Logiciel>>() {
			@Override
			public TableCell<Logiciel, Logiciel> call(TableColumn<Logiciel, Logiciel> personBooleanTableColumn) {
				return new TableCell<Logiciel, Logiciel>() {
					final Button button = new Button();
						{	
							button.setMinWidth(70);
						}

						public void updateItem(Logiciel logiciel, boolean empty) {
								super.updateItem(logiciel, empty);
								if (logiciel != null) {
									button.setText("Modifier");

									setGraphic(button);
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {

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
												getListLogiciel();
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

		logicielDao = new LogicielDao();
		getListLogiciel();
		refreshTable();
	}

	@FXML
	private void displayAddLogiciel(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource(
				"/view/AjoutLogiciel.fxml"));
		ManipInterface.chargementBodyPanel(bodyPanel, loader);
	}

	@FXML
	private void searchLogiciel(ActionEvent event) {
		if (!searchBar.getText().isEmpty()) {
			logicielDao = new LogicielDao();
			list = logicielDao.searchWithAttributes(searchBar.getText());
			refreshTable();
		}
	}

	@FXML
	private void exportTablePDF(ActionEvent event) throws IOException {
		PDFGenerator pdfGenerator = new PDFGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pdf", "*.pdf"));
		fileChooser.setInitialFileName("logiciels");
		fileChooser.setTitle("Save PDF");
		File file;
		file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
		if (file != null) {
			if (checkBoxExportTable.isSelected()) {
				pdfGenerator.generate(file, new PDFLogicielListExport(list));
			} else if (maxResult == list.size()) {
				pdfGenerator.generate(file, new PDFLogicielListExport(list));
			} else {
				List<Logiciel> results = logicielDao.findByAttributesLike(null);
				pdfGenerator.generate(file, new PDFLogicielListExport(results));
			}
		}
	}

	@FXML
	private void exportTableExcel(ActionEvent event) throws IOException {
		ExcelGenerator excelGenerator = new ExcelGenerator();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setInitialFileName("logiciels");
		fileChooser.setTitle("Save Excel");
		File file;
		file = fileChooser.showSaveDialog(bodyPanel.getParent().getScene().getWindow());
		if (file != null) {
			if (checkBoxExportTable.isSelected()) {
				excelGenerator.generate(file, new ExcelLogicielListExport(list));
			} else if (maxResult == list.size()) {
				excelGenerator.generate(file, new ExcelLogicielListExport(list));
			} else {
				List<Logiciel> results = logicielDao.findByAttributesLike(null);
				excelGenerator.generate(file, new ExcelLogicielListExport(results));
			}
		}
	}

	@FXML
	private void importExcel(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(
				new ExtensionFilter("Excel", "*.xls"));
		fileChooser.setTitle("Load Excel");
		File file;
		file = fileChooser.showOpenDialog(bodyPanel.getParent().getScene().getWindow());
		if (file != null) {
			ExcelImport excelImport = new ExcelImport();
			excelImport.importFile(file, new ExcelLogicielImport(list));
			for (Logiciel logiciel : list) {
				if (logicielDao.find(logiciel.getId()) == null) {
					logicielDao.save(logiciel);
				}
			}
			refreshTable();
		}
	}

	private void refreshTable() {
		ObservableList<Logiciel> items = FXCollections
				.observableArrayList(list);
		tableLogiciel.setItems(items);
		if (maxResult > list.size()) {
			buttonNext.setDisable(false);
		} else {
			buttonNext.setDisable(true);
		}
	}

	private void getListLogiciel() {
		boolean isLimit = Config.getPropertie("tableau_limite").equals("yes");
		if (isLimit) {
			maxResult = logicielDao.getNbResultLike(null);
			limit = Integer.parseInt(Config.getPropertie("tableau_nb_ligne"));
			if (maxResult < limit) {
				list = logicielDao.findByAttributesLike(null);
			} else {
				list = logicielDao.findByAttributesLikeWithLimits(null, 0,
						limit);
			}
		} else {
			list = logicielDao.findByAttributesLike(null);
			maxResult = list.size();
		}
	}

	@FXML
	private void viewMore(ActionEvent event) throws IOException {

		List<Logiciel> results = logicielDao.findByAttributesLikeWithLimits(null, list.size(), limit);
		for (Logiciel logiciel : results)
			list.add(logiciel);
		
		refreshTable();
	}
}
