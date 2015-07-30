package application.excel.export;

import java.util.List;

import javafx.scene.control.TableView;
import model.Agent;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class ExcelAgentListExport extends ExcelDataExport {
	
	private List<Agent> agents;
	private TableView<Agent> tableViewAgent;
	
	public ExcelAgentListExport (List<Agent> agentsParam) {
		agents = agentsParam;
	}

	public ExcelAgentListExport (TableView<Agent> tableViewAgentParam) {
		tableViewAgent = tableViewAgentParam;
	}
	
	@Override
	public void write(HSSFWorkbook wb) {
		
		HSSFSheet sheet = wb.createSheet("agents");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("Nom");
		row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("Prénom");
		row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("Téléphone");
		row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("N° de CP");
		row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("Pôle/Service");
			    
		if (agents != null)
	    	insertFromList(sheet, row, agents);
	     else if (this.tableViewAgent != null) 
	    	insertFromTable(sheet, row, tableViewAgent);
	    
	}
	
	private void insertFromList (HSSFSheet sheet, HSSFRow row, List<Agent> agents) {
		int ligne = 1;
		for (Agent agent : agents) {
	    	row = sheet.createRow(ligne);
	    	row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(agent.getNom());
			row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(agent.getPrenom());
			row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(agent.getTel());
			row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(agent.getNumCP());
			row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue(agent.getPole().getNom());	
			ligne++;
	    }
	}
	
	private void insertFromTable (HSSFSheet sheet, HSSFRow row, TableView<Agent> agents) {
		List<Agent> list = agents.getItems();
		insertFromList(sheet, row, list);
	}
	
}