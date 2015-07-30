package application.excel.export;

import java.util.List;

import javafx.scene.control.TableView;
import model.Logiciel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class ExcelLogicielListExport extends ExcelDataExport {
	
	private List<Logiciel> logiciels;
	private TableView<Logiciel> tableViewLogiciels;
	
	public ExcelLogicielListExport (List<Logiciel> logicielsParam) {
		logiciels = logicielsParam;
	}

	public ExcelLogicielListExport (TableView<Logiciel> tableViewLogiciels) {
		this.tableViewLogiciels = tableViewLogiciels;
	}
	
	@Override
	public void write (HSSFWorkbook wb) {
		
		HSSFSheet sheet = wb.createSheet("logiciels");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("Nom logiciel");
		row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("N° licence");
		row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("Prix licence (€)");
	    
		if (this.logiciels != null) {
	    	this.insertFromList(sheet, row, logiciels);
	    } else if (this.tableViewLogiciels != null) {
	    	this.insertFromTable(sheet, row, tableViewLogiciels);
	    }
	}
	
	private void insertFromList (HSSFSheet sheet, HSSFRow row, List<Logiciel> logiciels) {
		int ligne = 1;
		for (Logiciel logiciel : logiciels) {
	    	row = sheet.createRow(ligne);
	    	row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(logiciel.getNom());
			row.createCell(1, Cell.CELL_TYPE_NUMERIC).setCellValue(logiciel.getLicenceNumber());
			row.createCell(2, Cell.CELL_TYPE_NUMERIC).setCellValue(logiciel.getPrix());
			ligne++;
	    }
	}
	
	private void insertFromTable (HSSFSheet sheet, HSSFRow row, TableView<Logiciel> logiciels) {
		List<Logiciel> list = logiciels.getItems();
		insertFromList(sheet, row, list);
	}
	
}