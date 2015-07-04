package application.excel.export;

import java.util.List;

import javafx.scene.control.TableView;
import model.Equipement;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 * ExcelEquipementListExport est la classe permettant d'exporter une liste d'equipement au format excel.
 * @author: Mike FIARI
 * @version 1.0
 */
public class ExcelEquipementListExport extends ExcelDataExport {
	
	private List<Equipement> equipements;
	private TableView<Equipement> tableViewEquipement;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param equipements
	 *     La liste des equipements
	 * @see List
	 * @see Equipement
	 */
	public ExcelEquipementListExport (List<Equipement> equipements) {
		this.equipements = equipements;
	}

	/**
	 * Constructeur de la classe
	 *
	 * @param tableViewEquipement
	 *     La liste des equipements
	 * @see TableView
	 * @see Equipement
	 */
	public ExcelEquipementListExport (TableView<Equipement> tableViewEquipement) {
		this.tableViewEquipement = tableViewEquipement;
	}
	
	/**
	 * Ecrit le fichier excel
	 *
	 * @param wb
	 *     FIchier excel
	 * @see HSSFWorkbook
	 */
	@Override
	public void write (HSSFWorkbook wb) {
		
		HSSFSheet sheet = wb.createSheet("equipements");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("Nom Calife");
		row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("Type");
		row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("Valeur (€)");
		row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("Pole");
		row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("N° CP Agent");
	    
		if (this.equipements != null) {
	    	this.insertFromList(sheet, row, equipements);
	    } else if (this.tableViewEquipement != null) {
	    	this.insertFromTable(sheet, row, tableViewEquipement);
	    }
	    
	}
	
	private void insertFromList (HSSFSheet sheet, HSSFRow row, List<Equipement> equipements) {
		int ligne = 1;
		for (Equipement equipement : equipements) {
	    	row = sheet.createRow(ligne);
			row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(equipement.getNomCalife());
			row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(equipement.getTypeEquipement().getNom());
			row.createCell(2, Cell.CELL_TYPE_NUMERIC).setCellValue(equipement.getPrix());
			row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(equipement.getPole().getNom());
			if (equipement.getAgent() != null) {
				row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue(equipement.getAgent().getNumCP());
			} else {
				row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("");
			}
			ligne++;
	    }
	}
	
	private void insertFromTable (HSSFSheet sheet, HSSFRow row, TableView<Equipement> equipements) {
		List<Equipement> list = equipements.getItems();
		this.insertFromList(sheet, row, list);
	}
	
}