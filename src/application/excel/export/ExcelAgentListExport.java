package application.excel.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.Agent;

public class ExcelAgentListExport extends ExcelDataExport {
	
	private List<Agent> agents;
	
	public ExcelAgentListExport (List<Agent> agents) {
		this.agents = agents;
	}
	
	public void write (HSSFWorkbook wb) {
		
		HSSFSheet sheet = wb.createSheet("agents");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue("Nom");
		row.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue("Prénom");
		row.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue("Date de naissance");
		row.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue("N° de CP");
		row.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue("N° de poste");
	    
		int ligne = 1;
	    for (Agent agent : agents) {
	    	row = sheet.createRow(ligne);
	    	row.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(agent.getNom());
			row.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(agent.getPrenom());
			row.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(agent.getDateDeNaissance());
			row.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(agent.getNumCP());
			row.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue(agent.getNumPoste());
			ligne++;
	    }
	}
	
}