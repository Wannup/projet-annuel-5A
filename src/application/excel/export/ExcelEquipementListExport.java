package application.excel.export;

import java.util.List;

import javafx.scene.control.TableView;
import model.Equipement;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class ExcelEquipementListExport extends ExcelDataExport {
	
	private List<Equipement> equipements;
	private TableView<Equipement> tableViewEquipement;
	
	/**
	 * Constructor
	 *
	 * @param equipementsParam
	 *     list of equipements
	 */
	public ExcelEquipementListExport (List<Equipement> equipementsParam) {
		equipements = equipementsParam;
	}

	/**
	 * Constructor
	 *
	 * @param tableViewEquipementParam
	 *      list of equipements
	 */
	public ExcelEquipementListExport (TableView<Equipement> tableViewEquipementParam) {
		tableViewEquipement = tableViewEquipementParam;
	}

	@Override
	public void write (HSSFWorkbook wb) {
		
		HSSFSheet sheet = wb.createSheet("equipements");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("Nom Calife");
		row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("Type");
		row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("Valeur (€)");
		row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("Pole");
		row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("N° CP Agent");
	    
		if (equipements != null)
	    	insertFromList(sheet, row, equipements);
	    else if (tableViewEquipement != null)
	    	insertFromTable(sheet, row, tableViewEquipement);
	        
	}
	
	private void insertFromList (HSSFSheet sheet, HSSFRow row, List<Equipement> equipements) {
		int ligne = 1;
		for (Equipement equipement : equipements) {
	    	row = sheet.createRow(ligne);
			row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(equipement.getNomCalife());
			row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(equipement.getTypeEquipement().getNom());
			row.createCell(2, Cell.CELL_TYPE_NUMERIC).setCellValue(equipement.getPrix());
			row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(equipement.getPole().getNom());
			if (equipement.getAgent() != null) 
				row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue(equipement.getAgent().getNumCP());
			else
				row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("");
			ligne++;
	    }
	}
	
	private void insertFromTable (HSSFSheet sheet, HSSFRow row, TableView<Equipement> equipements) {
		List<Equipement> list = equipements.getItems();
		insertFromList(sheet, row, list);
	}
	
}