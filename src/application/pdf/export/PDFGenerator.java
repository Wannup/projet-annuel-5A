package application.pdf.export;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class PDFGenerator {
	
	/**
	 * generate the PDF file
	 *
	 * @param file
	 *    out file
	 * @param dataExport
	 * @see File
	 * @see PDFDataExport
	 */
	public void generate (File file, PDFDataExport dataExport) {
		try {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			dataExport.write(document, writer);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
