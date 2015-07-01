package application.excel.importer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Agent;
import model.Pole;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import dao.AgentDao;
import dao.PoleDao;

/**
 * ExcelAgentImport est la classe permettant d'importer des agents depuis un fichier excel.
 * 
 * @version 1.0
 */
public class ExcelAgentImport extends ExcelDataImport {
	
	private List<Agent> agents;
	private List<String> errors;
	
	private final int ID_CELL_NOM = 1;
	private final int ID_CELL_PRENOM = 2;
	private final int ID_CELL_TEL = 3;
	private final int ID_CELL_CP = 4;
	private final int ID_CELL_POLE = 5;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param agents
	 *     La liste des agents
	 * @see List
	 * @see Agent
	 */
	public ExcelAgentImport (List<Agent> agents, List<String> errors) {
		this.agents = agents;
		this.errors = errors;
	}

	/**
	 * Lit le fichier excel
	 *
	 * @param wb
	 *     FIchier excel
	 * @see HSSFWorkbook
	 */
	@Override
	public void read(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		int numLigne = 1;
		PoleDao poleDao = new PoleDao();
		AgentDao agentDao = new AgentDao();
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
						case ID_CELL_TEL :
							agent.setTel(cell.getStringCellValue());
							break;
						case ID_CELL_CP :
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								agent.setNumCP(String.valueOf(cell.getNumericCellValue()));
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								agent.setNumCP(cell.getStringCellValue());
							}
							break;
						case ID_CELL_POLE :
							String poleName = cell.getStringCellValue();
							Map<String, String> attributes = new HashMap<>();
							attributes.put("nom", poleName);
							List<Pole> poles = poleDao.findByAttributesEquals(attributes);
							Pole pole;
							if (poles.isEmpty()) {
								pole = new Pole(poleName);
								poleDao.save(pole);
							} else {
								pole = poles.get(0);
							}
							agent.setPole(pole);
							break;
					}
					numCell++;
				}
				Map<String, String> attributes = new HashMap<>();
				attributes.put("numCP", agent.getNumCP());
				List<Agent> results = agentDao.findByAttributesEquals(attributes);
				if (results.isEmpty()) {
					this.agents.add(agent);
				} else {
					errors.add("L'agent " + agent.getNumCP() + " (" + agent.getNom() + " " + agent.getPrenom() + ") existe déjà");
				}
			}
			numLigne++;
		}
	}

}
