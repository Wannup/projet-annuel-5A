package application.database.importer;

import java.io.File;

import tools.Zip;

/**
 * DatabaseImport est la classe permettant d'importer la base de donn�es.
 * 
 * @version 1.0
 */
public class DatabaseImport {
	
	/**
	 * Importe la base de donn�es
	 *
	 * @param file
	 *     Le fichier zip contenant la base de donn�es
	 */
	public void importDatabase (File file) {
		Zip zip = new Zip();
		zip.unzip(file, null);
	}

}
