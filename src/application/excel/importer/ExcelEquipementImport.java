package application.excel.importer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Agent;
import model.Equipement;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import application.database.DatabaseConnection;
import dao.AgentDao;

/**
 * ExcelEquipementImport est la classe permettant d'importer des �quipements depuis un fichier excel.
 * 
 * @version 1.0
 */
public class ExcelEquipementImport extends ExcelDataImport {
	
	private List<Equipement> equipements;
	
	private final int ID_CELL_NOM = 1;
	private final int ID_CELL_NUMERO = 2;
	private final int ID_CELL_AGENT = 3;
	private final int ID_CELL_LOGICIEL = 4;
	private final int ID_CELL_PRIX = 5;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param equipements
	 *     La liste des equipements
	 * @see List
	 * @see Equipement
	 */
	public ExcelEquipementImport (List<Equipement> equipements) {
		this.equipements = equipements;
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
		AgentDao agentDao = new AgentDao();
		DatabaseConnection.startConnection();
		for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
			row = (HSSFRow) rowIt.next();
			if (numLigne > 1) {
				Equipement equipement = new Equipement ();
				int numCell = 1;
				for (Iterator<Cell> cellIt = row.cellIterator(); cellIt.hasNext();) {
					cell = (HSSFCell) cellIt.next();
					switch (numCell) {
						case ID_CELL_NOM :
							equipement.setNom(cell.getStringCellValue());
							break;
						case ID_CELL_NUMERO :
							if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								equipement.setNomCalife(cell.getStringCellValue());
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								equipement.setNomCalife(cell.getStringCellValue());
							}
							break;
						case ID_CELL_AGENT :
							String cp = cell.getStringCellValue();
							Map<String, String> attributes = new HashMap<String, String>();
							attributes.put("numCP", cp);
							List<Agent> agents = agentDao.findByAttributesLike(attributes);
							if (agents.size() == 1) {
								equipement.setAgent(agents.get(0));
							}
							break;
						case ID_CELL_LOGICIEL :
							break;
						case ID_CELL_PRIX :
							if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								equipement.setPrix(cell.getNumericCellValue());
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								equipement.setPrix(Integer.valueOf(cell.getStringCellValue()));
							}
					}
					numCell++;
				}
				this.equipements.add(equipement);
			}
			numLigne++;
		}
		DatabaseConnection.closeConnection();
	}
}
