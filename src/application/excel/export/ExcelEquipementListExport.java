package application.excel.export;

import java.util.List;

import model.Equipement;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelEquipementListExport extends ExcelDataExport {
	
	private List<Equipement> equipements;
	
	public ExcelEquipementListExport (List<Equipement> equipements) {
		this.equipements = equipements;
	}
	
	public void write (HSSFWorkbook wb) {
		
		HSSFSheet sheet = wb.createSheet("equipements");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue("Nom");
		row.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue("Numéro de poste");
		row.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue("Agent");
		row.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue("Logiciels");
		row.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue("Prix");
	    
		int ligne = 1;
	    for (Equipement equipement : equipements) {
	    	row = sheet.createRow(ligne);
	    	row.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(equipement.getNom());
			row.createCell(1, HSSFCell.CELL_TYPE_NUMERIC).setCellValue(equipement.getNumeroPoste());
			//row.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(equipement.getAgent().getNom());
			row.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(""); // temporaire car agent null
			row.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue("");
			row.createCell(4, HSSFCell.CELL_TYPE_NUMERIC).setCellValue(equipement.getPrix());
			ligne++;
	    }
	}
	
}