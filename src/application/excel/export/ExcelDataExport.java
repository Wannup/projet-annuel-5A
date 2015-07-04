package application.excel.export;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * ExcelDataExport est la classe abstraite permettant d'exporter un fichier excel.
 * @author: Mike FIARI
 * @version 1.0
 */
abstract public class ExcelDataExport {
	
	/**
	 * Ecrit le fichier excel
	 *
	 * @param wb
	 *     FIchier excel
	 * @see HSSFWorkbook
	 */
	public abstract void write (HSSFWorkbook wb);

}
