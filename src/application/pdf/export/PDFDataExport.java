package application.pdf.export;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

/**
 * PDFDataExport est la classe abstraite permettant d'exporter un fichier PDF.
 * 
 * @version 1.0
 */
abstract public class PDFDataExport {
	
	/**
	 * Ecrit le fichier PDF
	 *
	 * @param document
	 *     Fichier PDF
	 * @see Document
	 * 
	 * @throws DocumentException  Si jamais le document est incorect
	 */
	public abstract void write (Document document) throws DocumentException;

}
