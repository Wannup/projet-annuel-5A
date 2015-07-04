package application.excel.importer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * ExcelDataImport est la classe abstraite permettant d'importer des fichiers excel.
 * @author: Mike FIARI
 * @version 1.0
 */
abstract public class ExcelDataImport {
	
	/**
	 * Lit le fichier excel
	 *
	 * @param wb
	 *     FIchier excel
	 * @see HSSFWorkbook
	 */
	public abstract void read (HSSFWorkbook wb);

}
