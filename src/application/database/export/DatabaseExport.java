package application.database.export;

import java.io.File;

import tools.Zip;

/**
 * DatabaseExport est la classe permettant d'exporter la base de données en zip.
 * 
 * @version 1.0
 */
public class DatabaseExport {
	
	/**
	 * Exporte la base de données
	 *
	 * @param file
	 *     Le dossier dans lequel la base de données sera exporter
	 */
	public void exportDatabase (File file) {
		Zip zip = new Zip();
		try {
			zip.zipFolder("database", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
