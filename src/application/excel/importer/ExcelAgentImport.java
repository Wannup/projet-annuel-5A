package application.excel.importer;

import java.util.Iterator;
import java.util.List;

import model.Agent;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelAgentImport extends ExcelDataImport {
	
	private List<Agent> agents;
	
	private final int ID_CELL_NOM = 1;
	private final int ID_CELL_PRENOM = 2;
	private final int ID_CELL_DATE = 3;
	private final int ID_CELL_CP = 4;
	private final int ID_CELL_POSTE = 5;
	
	public ExcelAgentImport (List<Agent> agents) {
		this.agents = agents;
	}

	@Override
	public void read(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		int numLigne = 1;
		for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
			row = (HSSFRow) rowIt.next();
			if (numLigne > 1) {
				Agent agent = new Agent ();
				int numCell = 1;
				for (Iterator<Cell> cellIt = row.cellIterator(); cellIt.hasNext();) {
					cell = (HSSFCell) cellIt.next();
					switch (numCell) {
						case ID_CELL_NOM :
							agent.setNom(cell.getStringCellValue());
							break;
						case ID_CELL_PRENOM :
							agent.setPrenom(cell.getStringCellValue());
							break;
						case ID_CELL_DATE :
							agent.setDateDeNaissance(cell.getStringCellValue());
							break;
						case ID_CELL_CP :
							agent.setNumCP(cell.getStringCellValue());
							break;
						case ID_CELL_POSTE :
							agent.setNumPoste(cell.getStringCellValue());
							break;
					}
					numCell++;
				}
				this.agents.add(agent);
			}
			numLigne++;
		}
		for (Agent agent : this.agents) {
			System.out.println(agent.getNom());
		}
	}

}
