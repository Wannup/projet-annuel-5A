package application.excel.export;

import java.util.List;

import model.Equipement;
import model.Logiciel;

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
		
		row.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue("N° d'équipement");
		row.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue("Valeur (€)");
		row.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue("N° CP Agent");
		row.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue("Logiciel(s) installé(s)");
	    
		int ligne = 1;
	    for (Equipement equipement : equipements) {
	    	row = sheet.createRow(ligne);
			row.createCell(0, HSSFCell.CELL_TYPE_NUMERIC).setCellValue(equipement.getNumeroPoste());
			row.createCell(1, HSSFCell.CELL_TYPE_NUMERIC).setCellValue(equipement.getPrix());
			if (equipement.getAgent() != null) {
				row.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(equipement.getAgent().getNumCP());
			} else {
				row.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue("");
			}
			String logiciels = "";
		    for (Logiciel logiciel : equipement.getLogiciels()) {
		    	logiciels += ","+logiciel.getNom();
		    }
			row.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(logiciels);
			ligne++;
	    }
	}
	
}