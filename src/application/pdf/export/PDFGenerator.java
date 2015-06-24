package application.pdf.export;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * PDFGenerator est la classe permettant de générer un fichier PDF.
 * 
 * @version 1.0
 */
public class PDFGenerator {
	
	/**
	 * genere le fichier PDF
	 *
	 * @param file
	 *     Le fichier de sortie
	 * @param dataExport
	 *     Class export
	 * @see File
	 * @see PDFDataExport
	 */
	public void generate (File file, PDFDataExport dataExport) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			dataExport.write(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
