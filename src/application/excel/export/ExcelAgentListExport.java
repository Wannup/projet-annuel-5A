package application.excel.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import model.Agent;

/**
 * ExcelAgentListExport est la classe permettant d'exporter une liste d'agent au format excel.
 * 
 * @version 1.0
 */
public class ExcelAgentListExport extends ExcelDataExport {
	
	private List<Agent> agents;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param agents
	 *     La liste des agents
	 * @see List
	 * @see Agent
	 */
	public ExcelAgentListExport (List<Agent> agents) {
		this.agents = agents;
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
		
		HSSFSheet sheet = wb.createSheet("agents");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("Nom");
		row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("Pr�nom");
		row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("Tel");
		row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("N� de CP");
		row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("N� de poste");
	    
		int ligne = 1;
	    for (Agent agent : agents) {
	    	row = sheet.createRow(ligne);
	    	row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(agent.getNom());
			row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(agent.getPrenom());
			row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(agent.getTel());
			row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(agent.getNumCP());
			row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue(agent.getNumPoste());
			ligne++;
	    }
	}
	
}