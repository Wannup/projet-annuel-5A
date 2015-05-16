package application.excel.importer;

import java.util.Iterator;
import java.util.List;

import model.Logiciel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelLogicielImport extends ExcelDataImport {
	
	private List<Logiciel> logiciels;
	
	private final int ID_CELL_NOM = 1;
	private final int ID_CELL_PRIX = 2;
	private final int ID_CELL_JOUR = 3;
	
	public ExcelLogicielImport (List<Logiciel> logiciels) {
		this.logiciels = logiciels;
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
				Logiciel logiciel = new Logiciel ();
				int numCell = 1;
				for (Iterator<Cell> cellIt = row.cellIterator(); cellIt.hasNext();) {
					cell = (HSSFCell) cellIt.next();
					switch (numCell) {
						case ID_CELL_NOM :
							logiciel.setNom(cell.getStringCellValue());
							break;
						case ID_CELL_PRIX :
							if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								logiciel.setPrix(cell.getNumericCellValue());
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								logiciel.setPrix(Double.valueOf(cell.getStringCellValue()));
							}
							break;
						case ID_CELL_JOUR :
							if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								logiciel.setNbJourLicence((int)cell.getNumericCellValue());
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								logiciel.setNbJourLicence(Integer.valueOf(cell.getStringCellValue()));
							}
							break;
					}
					numCell++;
				}
				this.logiciels.add(logiciel);
			}
			numLigne++;
		}
	}
}