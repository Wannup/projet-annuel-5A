package application.excel.importer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class ExcelImport {
	
	/**
	 * import excel file
	 * @param file
	 *     out file
	 * @param dataImport
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @see File
	 * @see ExcelDataImport
	 */
	public void importFile (File file, ExcelDataImport dataImport) throws IOException {
		POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(file));
		HSSFWorkbook wb = new HSSFWorkbook(fileSystem);
		dataImport.read(wb);
	}

}
