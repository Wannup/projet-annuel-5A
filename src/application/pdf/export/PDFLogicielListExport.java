package application.pdf.export;

import java.util.List;

import model.Logiciel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * PDFLogicielListExport est la classe permettant d'exporter une liste de logiciel au format PDF.
 * 
 * @version 1.0
 */
public class PDFLogicielListExport extends PDFDataExport {
	
	private List<Logiciel> logiciels;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param logiciels
	 *     La liste des logiciels
	 * @see List
	 * @see Logiciel
	 */
	public PDFLogicielListExport (List<Logiciel> logiciels) {
		this.logiciels = logiciels;
	}
	
	/**
	 * Ecrit le fichier PDF
	 *
	 * @param document
	 *     Fichier PDF
	 * @see Document
	 * 
	 * @throws DocumentException  Si jamais le document est incorect
	 */
	public void write (Document document) throws DocumentException {
		
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		
		Paragraph title = new Paragraph("Liste des logiciels", catFont);
		title.setAlignment(Element.ALIGN_CENTER);
		
		document.add(title);
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		PdfPTable table = new PdfPTable(3);

	    PdfPCell c1 = new PdfPCell(new Phrase("Libelle logiciel"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Valeur licence (�)"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Numero licence"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);
	    
	    for (Logiciel logiciel : logiciels) {
	    	table.addCell(logiciel.getNom());
		    table.addCell(String.valueOf(logiciel.getPrix()));
		    table.addCell(String.valueOf(logiciel.getLicenceNumber()));
	    }
	    
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);

	    document.add(table);
	}

}