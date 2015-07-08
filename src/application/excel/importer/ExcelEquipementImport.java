package application.excel.importer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Agent;
import model.Equipement;
import model.Logiciel;
import model.Pole;
import model.TypeEquipement;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import tools.LoadingFrame;
import dao.AgentDao;
import dao.EquipementDao;
import dao.LogicielDao;
import dao.PoleDao;
import dao.TypeEquipementDao;

/**
 * ExcelEquipementImport est la classe permettant d'importer des équipements depuis un fichier excel.
 * @author: Mike FIARI
 * @version 1.0
 */
public class ExcelEquipementImport extends ExcelDataImport {
	
	private List<Equipement> equipements;
	private List<String> errors;
	private LoadingFrame loadingFrame;
	
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
	public ExcelEquipementImport (List<Equipement> equipements, List<String> errors, LoadingFrame loadingFrame) {
		this.equipements = equipements;
		this.errors = errors;
		this.loadingFrame = loadingFrame;
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
		int maxRow = sheet.getLastRowNum();
		for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
			this.loadingFrame.setProgress((double)numLigne / (double)maxRow);
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
								equipement.setDateGarantie(new SimpleDateFormat("dd/MM/yyyy").format(cell.getDateCellValue()));
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								equipement.setDateGarantie(cell.getStringCellValue());
							}
							break;
						case ID_CELL_LIVRAISON :
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								equipement.setDateLivraison(new SimpleDateFormat("dd/MM/yyyy").format(cell.getDateCellValue()));
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								equipement.setDateLivraison(cell.getStringCellValue());
							}
							break;
						case ID_CELL_MARQUE :
							equipement.setMarque(cell.getStringCellValue());
							break;
						case ID_CELL_MODELE :
							equipement.setModele(cell.getStringCellValue());
							break;
						case ID_CELL_CALIFE :
							equipement.setNomCalife(cell.getStringCellValue());
							break;
						case ID_CELL_INFO :
							equipement.setInfo(cell.getStringCellValue());
							break;
						case ID_CELL_AGENT :
							String cp = "";
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								Double dbl = cell.getNumericCellValue();
								cp = String.valueOf(dbl.intValue());
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								cp = cell.getStringCellValue();
							}
							Map<String, String> attributes = new HashMap<String, String>();
							attributes.put("numCP", cp);
							List<Agent> agents = agentDao.findByAttributesLike(attributes);
							if (!agents.isEmpty()) {
								equipement.setAgent(agents.get(0));
							}
							break;
						case ID_CELL_POLE :
							String poleName = cell.getStringCellValue();
							Map<String, String> attributesPole = new HashMap<>();
							attributesPole.put("nom", poleName);
							List<Pole> poles = poleDao.findByAttributesEquals(attributesPole);
							Pole pole;
							if (poles.isEmpty()) {
								pole = new Pole(poleName);
								poleDao.save(pole);
							} else {
								pole = poles.get(0);
							}
							equipement.setPole(pole);
							break;
						case ID_CELL_LOGICIELS :
							if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								String logiciels = cell.getStringCellValue();
								String[] array = logiciels.split(",");
								List<Logiciel> logicielsList = new ArrayList<>();
								for (String string : array) {
									string = string.trim();
									Map<String, String> attributesLogiciel = new HashMap<>();
									attributesLogiciel.put("nom", string);
									List<Logiciel> results = logicielDao.findByAttributesEquals(attributesLogiciel);
									Logiciel logiciel;
									if (!results.isEmpty()) {
										logiciel = results.get(0);
										logicielsList.add(logiciel);
									}
								}
								equipement.setLogiciels(logicielsList);
							}
							break;
						case ID_CELL_PRIX :
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								equipement.setPrix(cell.getNumericCellValue());
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								equipement.setPrix(Double.valueOf(cell.getStringCellValue()));
							}
							break;
					}
					numCell++;
				}
				Map<String, String> attributes = new HashMap<>();
				attributes.put("nomCalife", equipement.getNomCalife());
				List<Equipement> results = equipementDao.findByAttributesEquals(attributes);
				if (results.isEmpty()) {
					this.equipements.add(equipement);
				} else {
					errors.add("L'equipement " + equipement.getNomCalife() + " existe déjà");
				}
				
			}
			numLigne++;
		}
	}
}
