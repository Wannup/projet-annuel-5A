package application.excel.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * ExcelGenerator est la classe permettant de générer un fichier excel.
 * 
 * @version 1.0
 */
public class ExcelGenerator {

	/**
	 * genere le fichier excel
	 *
	 * @param file
	 *     Le fichier de sortie
	 * @param dataExport
	 *     Class export
	 * @see File
	 * @see ExcelDataExport
	 */
	public void generate (File file, ExcelDataExport dataExport) {
		HSSFWorkbook wb = new HSSFWorkbook();
		dataExport.write(wb);
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
