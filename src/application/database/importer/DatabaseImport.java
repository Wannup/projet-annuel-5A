package application.database.importer;

import java.io.File;

import tools.Zip;

public class DatabaseImport {
	
	public void importDatabase (File file) {
		Zip zip = new Zip();
		zip.unzip(file, null);
	}

}
