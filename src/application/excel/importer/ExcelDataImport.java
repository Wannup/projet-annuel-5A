package application.excel.importer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

abstract public class ExcelDataImport {
	
	public abstract void read (HSSFWorkbook wb);

}
