package application.excel.export;

import java.util.List;

import javafx.scene.control.TableView;
import model.Agent;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 * ExcelAgentListExport est la classe permettant d'exporter une liste d'agent au format excel.
 * 
 * @version 1.0
 */
public class ExcelAgentListExport extends ExcelDataExport {
	
	private List<Agent> agents;
	private TableView<Agent> tableViewAgent;
	
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
	 * Constructeur de la classe
	 *
	 * @param tableViewAgent
	 *     La liste des agents
	 * @see TableView
	 * @see Agent
	 */
	public ExcelAgentListExport (TableView<Agent> tableViewAgent) {
		this.tableViewAgent = tableViewAgent;
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
		row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("Prénom");
		row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("Pôle/Service");
		row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("N° de CP");
		row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("Téléphone");
	    
		if (this.agents != null) {
	    	this.insertFromList(sheet, row, agents);
	    } else if (this.tableViewAgent != null) {
	    	this.insertFromTable(sheet, row, tableViewAgent);
	    }
	}
	
	private void insertFromList (HSSFSheet sheet, HSSFRow row, List<Agent> agents) {
		int ligne = 1;
		for (Agent agent : agents) {
	    	row = sheet.createRow(ligne);
	    	row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(agent.getNom());
			row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(agent.getPrenom());
			row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(agent.getPole().getNom());
			row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(agent.getNumCP());
			row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue(agent.getTel());
			ligne++;
	    }
	}
	
	private void insertFromTable (HSSFSheet sheet, HSSFRow row, TableView<Agent> agents) {
		List<Agent> list = agents.getItems();
		this.insertFromList(sheet, row, list);
	}
	
}