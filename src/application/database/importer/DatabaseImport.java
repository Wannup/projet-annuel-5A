package application.database.importer;

import java.io.File;

import tools.Zip;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class DatabaseImport {
	
	/**
	 * @param file
	 *    The zip file
	 */
	public void importDatabase(File file) {
		Zip zip = new Zip();
		zip.unzip(file, null);
	}

}
