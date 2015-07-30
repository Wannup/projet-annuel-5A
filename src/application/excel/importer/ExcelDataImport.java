package application.excel.importer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
abstract public class ExcelDataImport {
	
	/**
	 * Read the excel file
	 *
	 * @param wb
	 *     excel file
	 * @see HSSFWorkbook
	 */
	public abstract void read (HSSFWorkbook wb);

}
