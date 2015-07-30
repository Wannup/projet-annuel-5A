package application.excel.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class ExcelGenerator {

	/**
	 * generate the excel file
	 * @param file
	 *     out file
	 * @param dataExport
	 *     Class export
	 * @throws IOException 
	 * @see ExcelDataExport
	 */
	public void generate (File file, ExcelDataExport dataExport) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		dataExport.write(wb);
		FileOutputStream fileOut;
		fileOut = new FileOutputStream(file);
		wb.write(fileOut);
		fileOut.close();
		wb.close();
	}
	
}
