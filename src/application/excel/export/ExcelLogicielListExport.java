package application.excel.export;

import java.util.List;

import model.Logiciel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * ExcelLogicielListExport est la classe permettant d'exporter une liste de logiciel au format excel.
 * 
 * @version 1.0
 */
public class ExcelLogicielListExport extends ExcelDataExport {
	
	private List<Logiciel> logiciels;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param logiciels
	 *     La liste des logiciels
	 * @see List
	 * @see Logiciel
	 */
	public ExcelLogicielListExport (List<Logiciel> logiciels) {
		this.logiciels = logiciels;
	}
	
	/**
	 * Ecrit le fichier excel
	 *
	 * @param wb
	 *     FIchier excel
	 * @see HSSFWorkbook
	 */
	public void write (HSSFWorkbook wb) {
		
		HSSFSheet sheet = wb.createSheet("logiciels");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue("Libelle logiciel");
		row.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue("Valeur licence (€)");
		row.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue("Durée licence (en Jour)");
	    
		int ligne = 1;
	    for (Logiciel logiciel : logiciels) {
	    	row = sheet.createRow(ligne);
	    	row.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(logiciel.getNom());
			row.createCell(1, HSSFCell.CELL_TYPE_NUMERIC).setCellValue(logiciel.getPrix());
			row.createCell(2, HSSFCell.CELL_TYPE_NUMERIC).setCellValue(logiciel.getNbJourLicence());
			ligne++;
	    }
	}
	
}