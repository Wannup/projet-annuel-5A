package application.excel.export;

import java.util.List;

import model.Equipement;
import model.Logiciel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 * ExcelEquipementListExport est la classe permettant d'exporter une liste d'equipement au format excel.
 * 
 * @version 1.0
 */
public class ExcelEquipementListExport extends ExcelDataExport {
	
	private List<Equipement> equipements;
	
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
		
		row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("N� d'�quipement");
		row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("Valeur (�)");
		row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("N� CP Agent");
		row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("Logiciel(s) install�(s)");
	    
		int ligne = 1;
	    for (Equipement equipement : equipements) {
	    	row = sheet.createRow(ligne);
			row.createCell(0, Cell.CELL_TYPE_NUMERIC).setCellValue(equipement.getNomCalife());
			row.createCell(1, Cell.CELL_TYPE_NUMERIC).setCellValue(equipement.getPrix());
			if (equipement.getAgent() != null) {
				row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(equipement.getAgent().getNumCP());
			} else {
				row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("");
			}
			String logiciels = "";
		    for (Logiciel logiciel : equipement.getLogiciels()) {
		    	logiciels += ","+logiciel.getNom();
		    }
			row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(logiciels);
			ligne++;
	    }
	}
	
}