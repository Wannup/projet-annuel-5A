package application.pdf.export;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
abstract public class PDFDataExport {
	
	/**
	 * Write the PDF file
	 *
	 * @param document
	 *    PDF File
	 * @see Document
	 * 
	 * @throws DocumentException if the file is incorrect
	 */
	public abstract void write (Document document, PdfWriter writer) throws DocumentException;

}
