package application.excel.importer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Agent;
import model.Equipement;
import model.Pole;
import model.TypeEquipement;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import application.database.DatabaseConnection;
import dao.AgentDao;
import dao.EquipementDao;
import dao.LogicielDao;
import dao.PoleDao;
import dao.TypeEquipementDao;

/**
 * ExcelEquipementImport est la classe permettant d'importer des équipements depuis un fichier excel.
 * 
 * @version 1.0
 */
public class ExcelEquipementImport extends ExcelDataImport {
	
	private List<Equipement> equipements;
	private List<String> errors;
	
	private final int ID_CELL_TYPE = 1;
	private final int ID_CELL_GARANTIE = 2;
	private final int ID_CELL_LIVRAISON = 3;
	private final int ID_CELL_MARQUE = 4;
	private final int ID_CELL_MODELE = 5;
	private final int ID_CELL_CALIFE = 6;
	private final int ID_CELL_INFO = 7;
	private final int ID_CELL_AGENT = 8;
	private final int ID_CELL_POLE = 9;
	private final int ID_CELL_LOGICIELS = 10;
	private final int ID_CELL_PRIX = 11;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param equipements
	 *     La liste des equipements
	 * @see List
	 * @see Equipement
	 */
	public ExcelEquipementImport (List<Equipement> equipements, List<String> errors) {
		this.equipements = equipements;
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
		AgentDao agentDao = new AgentDao();
		TypeEquipementDao typeEquipementDao = new TypeEquipementDao();
		EquipementDao equipementDao = new EquipementDao();
		PoleDao poleDao = new PoleDao();
		LogicielDao logicielDao = new LogicielDao();
		DatabaseConnection.startConnection();
		for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
			row = (HSSFRow) rowIt.next();
			if (numLigne > 1) {
				Equipement equipement = new Equipement ();
				int numCell = 1;
				for (Iterator<Cell> cellIt = row.cellIterator(); cellIt.hasNext();) {
					cell = (HSSFCell) cellIt.next();
					switch (numCell) {
						case ID_CELL_TYPE :
							String typeEquipementName = cell.getStringCellValue();
							Map<String, String> attributesType = new HashMap<>();
							attributesType.put("nom", typeEquipementName);
							List<TypeEquipement> typeEquipements = typeEquipementDao.findByAttributesEquals(attributesType);
							TypeEquipement typeEquipement;
							if (typeEquipements.isEmpty()) {
								typeEquipement = new TypeEquipement();
								typeEquipement.setNom(typeEquipementName);
								typeEquipementDao.save(typeEquipement);
							} else {
								typeEquipement = typeEquipements.get(0);
							}
							equipement.setTypeEquipement(typeEquipement);
							break;
						case ID_CELL_GARANTIE :
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								equipement.setNomCalife(cell.getStringCellValue());
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								equipement.setNomCalife(cell.getStringCellValue());
							}
							break;
						case ID_CELL_LIVRAISON :
							//equipement.setNom(cell.getStringCellValue());
							break;
						case ID_CELL_MARQUE :
							//equipement.setNom(cell.getStringCellValue());
							break;
						case ID_CELL_MODELE :
							//equipement.setNom(cell.getStringCellValue());
							break;
						case ID_CELL_CALIFE :
							//equipement.setNom(cell.getStringCellValue());
							break;
						case ID_CELL_INFO :
							//equipement.setNom(cell.getStringCellValue());
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
						case ID_CELL_POLE :
							break;
						case ID_CELL_LOGICIELS :
							break;
						case ID_CELL_PRIX :
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								equipement.setPrix(cell.getNumericCellValue());
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
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
