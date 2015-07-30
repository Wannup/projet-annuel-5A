package application.database.export;

import java.io.File;
import java.io.IOException;

import tools.Zip;

/**
 * @author: Mike FIARI
 * @version 1.0
 */

public class DatabaseExport {
	
	/**
	 * @param file
	 * 	   The destination directory
	 * @throws Exception 
	 */
	public void exportDatabase (File file) throws IOException {
		Zip zip = new Zip();
		zip.zipFolder("database", file);
	}

}
