package application.excel.export;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
abstract public class ExcelDataExport {
	
	/**
	 * Write the excel file
	 *
	 * @param wb
	 *     File excel
	 * @see HSSFWorkbook
	 */
	public abstract void write (HSSFWorkbook wb);

}
