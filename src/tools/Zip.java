package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Class Zip.
 * @author Mike FIARI
 * @version 1.0
 */
public class Zip {

	public void zipFolder(String srcFolder, File destZipFile) throws IOException {
		ZipOutputStream zip = null;
	    FileOutputStream fileWriter = null;
	    fileWriter = new FileOutputStream(destZipFile);
	    zip = new ZipOutputStream(fileWriter);
	    addFolderToZip("", srcFolder, zip);
	    zip.flush();
	    zip.close();
	}

	public void addFileToZip(String path, String srcFile, ZipOutputStream zip) throws IOException {
		File folder = new File(srcFile);
	    if (folder.isDirectory()) {
	    	addFolderToZip(path, srcFile, zip);
	    } else {
	    	byte[] buf = new byte[1024];
	    	int len;
	    	FileInputStream in = new FileInputStream(srcFile);
	    	zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
	    	while ((len = in.read(buf)) > 0) {
	    		zip.write(buf, 0, len);
	    	}
	    	in.close();
	    }
	}

	private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws IOException {
	    File folder = new File(srcFolder);
	    for (String fileName : folder.list()) {
	    	if (path.equals("")) {
	    		addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
	    	} else {
	    		addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
	    	}
	    }
	}
	
	public void unzip(File zipFile, String outputFolder) {
		
		byte[] buffer = new byte[1024];
	    try{
	    	if (outputFolder != null) {
		    	//create output directory is not exists
		    	File folder = new File(outputFolder);
		    	if(!folder.exists()){
		    		folder.mkdir();
		    	}
		 
		    	//get the zip file content
		    	ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
		    	//get the zipped file list entry
		    	ZipEntry ze = zis.getNextEntry();
		 
		    	while(ze != null) {
		 
		    		String fileName = ze.getName();
		    		File newFile = new File(outputFolder + File.separator + fileName);
		 
		    		System.out.println("file unzip : "+ newFile.getAbsoluteFile());
		 
		            //create all non exists folders
		            //else you will hit FileNotFoundException for compressed folder
		            new File(newFile.getParent()).mkdirs();
		 
		            FileOutputStream fos = new FileOutputStream(newFile);             
		 
		            int len;
		            while ((len = zis.read(buffer)) > 0) {
		            	fos.write(buffer, 0, len);
		            }
		 
		            fos.close();   
		            ze = zis.getNextEntry();
		    	}
		 
		        zis.closeEntry();
		    	zis.close();
		 
		    	System.out.println("Done");
	    	} else {
		 
		    	//get the zip file content
		    	ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
		    	//get the zipped file list entry
		    	ZipEntry ze = zis.getNextEntry();
		 
		    	while(ze != null) {
		 
		    		String fileName = ze.getName();
		    		File newFile = new File(fileName);
		 
		    		System.out.println("file unzip : "+ newFile.getAbsoluteFile());
		 
		            //create all non exists folders
		            //else you will hit FileNotFoundException for compressed folder
		            new File(newFile.getParent()).mkdirs();
		 
		            FileOutputStream fos = new FileOutputStream(newFile);             
		 
		            int len;
		            while ((len = zis.read(buffer)) > 0) {
		            	fos.write(buffer, 0, len);
		            }
		 
		            fos.close();   
		            ze = zis.getNextEntry();
		    	}
		 
		        zis.closeEntry();
		    	zis.close();
		 
		    	System.out.println("Done");
	    	}
	 
	    } catch(IOException ex){
	    	ex.printStackTrace(); 
	    }
	}    

}
