package application.pdf.export;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

abstract public class PDFDataExport {
	
	public abstract void write (Document document) throws DocumentException;

}
