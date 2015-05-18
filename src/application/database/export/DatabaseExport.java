package application.database.export;

import java.io.File;

import tools.Zip;

public class DatabaseExport {
	
	public void exportDatabase (File file) {
		Zip zip = new Zip();
		try {
			zip.zipFolder("database", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
