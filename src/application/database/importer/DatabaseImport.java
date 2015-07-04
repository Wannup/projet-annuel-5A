package application.database.importer;

import java.io.File;

import tools.Zip;

/**
 * DatabaseImport est la classe permettant d'importer la base de données.
 * @author: Mike FIARI
 * @version 1.0
 */
public class DatabaseImport {
	
	/**
	 * Importe la base de données
	 *
	 * @param file
	 *     Le fichier zip contenant la base de données
	 * @see File
	 */
	public void importDatabase (File file) {
		Zip zip = new Zip();
		zip.unzip(file, null);
	}

}
