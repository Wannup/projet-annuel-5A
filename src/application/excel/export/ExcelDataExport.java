package application.excel.export;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

abstract public class ExcelDataExport {
	
	public abstract void write (HSSFWorkbook wb);

}
