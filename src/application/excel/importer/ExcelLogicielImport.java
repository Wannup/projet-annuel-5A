package application.excel.importer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Logiciel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import tools.LoadingFrame;
import dao.LogicielDao;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class ExcelLogicielImport extends ExcelDataImport {
	
	private List<Logiciel> logiciels;
	private List<String> errors;
	private LoadingFrame loadingFrame;
	
	private final int ID_CELL_NOM = 1;
	private final int ID_CELL_PRIX = 2;
	private final int ID_CELL_NUMBER = 3;
	private final int ID_CELL_VALIDITY = 4;
	
	
	public ExcelLogicielImport (List<Logiciel> logicielsParam, List<String> errorsParam, LoadingFrame loadingFrameParam) {
		logiciels = logicielsParam;
		errors = errorsParam;
		loadingFrame = loadingFrameParam;
	}

	@Override
	public void read(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		int numLigne = 1;
		LogicielDao logicielDao = new LogicielDao();
		int maxRow = sheet.getLastRowNum();
		for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
			loadingFrame.setProgress((double)numLigne / (double)maxRow);
			row = (HSSFRow) rowIt.next();
			if (numLigne > 1) {
				Logiciel logiciel = new Logiciel ();
				int numCell = 1;
				for (Iterator<Cell> cellIt = row.cellIterator(); cellIt.hasNext();) {
					cell = (HSSFCell) cellIt.next();
					switch (numCell) {
						case ID_CELL_NOM :
							logiciel.setNom(cell.getStringCellValue());
							break;
						case ID_CELL_PRIX :
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								logiciel.setPrix(cell.getNumericCellValue());
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								logiciel.setPrix(Double.valueOf(cell.getStringCellValue()));
							}
							break;
						case ID_CELL_NUMBER :
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								logiciel.setLicenceNumber(cell.getStringCellValue());
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								logiciel.setLicenceNumber(cell.getStringCellValue());
							}
							break;
						case ID_CELL_VALIDITY :
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								logiciel.setDateEndValidityLicence(String.valueOf(cell.getNumericCellValue()));
							} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								logiciel.setDateEndValidityLicence(cell.getStringCellValue());
							}
							break;
					}
					numCell++;
				}
				Map<String, String> attributes = new HashMap<>();
				attributes.put("nom", logiciel.getNom());
				attributes.put("licenceNumber", logiciel.getLicenceNumber());
				List<Logiciel> results = logicielDao.findByAttributesEquals(attributes);
				if (results.isEmpty()) {
					logiciels.add(logiciel);
				} else {
					errors.add("le logiciel " + logiciel.getNom() + " (" + logiciel.getLicenceNumber() + ") existe déjà");
				}
				
			}
			numLigne++;
		}
	}
}
