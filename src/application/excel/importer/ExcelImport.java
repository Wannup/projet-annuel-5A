package application.excel.importer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelImport {
	
	public void importFile (File file, ExcelDataImport dataImport) {
		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fileSystem);
			dataImport.read(wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
