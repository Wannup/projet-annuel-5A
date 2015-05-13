package application.excel.importer;

import java.util.Iterator;
import java.util.List;

import model.Agent;
import model.Equipement;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelEquipementImport extends ExcelDataImport {
	
	private List<Equipement> equipements;
	
	private final int ID_CELL_NOM = 1;
	private final int ID_CELL_POSTE = 2;
	private final int ID_CELL_AGENT = 3;
	private final int ID_CELL_LOGICIEL = 4;
	private final int ID_CELL_PRIX = 5;
	
	public ExcelEquipementImport (List<Equipement> equipements) {
		this.equipements = equipements;
	}

	@Override
	public void read(HSSFWorkbook wb) {
		// TODO Auto-generated method stub
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		int totalLigne = 0;
		int totalGeneral = 0;
		int numLigne = 1;
		for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
			totalLigne = 0;
			row = (HSSFRow) rowIt.next();
			for (Iterator<Cell> cellIt = row.cellIterator(); cellIt.hasNext();) {
				cell = (HSSFCell) cellIt.next();
				//totalLigne += cell.getNumericCellValue();
				totalLigne++;
			}
			System.out.println("total ligne "+numLigne+" = "+totalLigne);
			totalGeneral += totalLigne;
			numLigne++;
		}
		System.out.println("total general "+totalGeneral);
	}
}
