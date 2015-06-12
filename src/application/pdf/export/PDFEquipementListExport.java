package application.pdf.export;

import java.util.List;

import model.Equipement;
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
 * PDFEquipementListExport est la classe permettant d'exporter une liste �quipement au format PDF.
 * 
 * @version 1.0
 */
public class PDFEquipementListExport extends PDFDataExport {
	
	private List<Equipement> equipements;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param equipements
	 *     La liste des equipements
	 * @see List
	 * @see Equipement
	 */
	public PDFEquipementListExport (List<Equipement> equipements) {
		this.equipements = equipements;
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
		
		Paragraph title = new Paragraph("Liste des �quipements", catFont);
		title.setAlignment(Element.ALIGN_CENTER);
		
		document.add(title);
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		PdfPTable table = new PdfPTable(4);

	    PdfPCell c1 = new PdfPCell(new Phrase("N� d'�quipement"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Valeur (�)"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("N� CP Agent"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Logiciel(s) install�(s)"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);
	    
	    for (Equipement equipement : equipements) {
		    table.addCell(String.valueOf(equipement.getNomCalife()));
		    table.addCell(String.valueOf(equipement.getPrix()));
		    if (equipement.getAgent() != null) {
		    	table.addCell(equipement.getAgent().getNumCP());
		    } else {
		    	table.addCell("");
		    }
		    String logiciels = "";
		    for (Logiciel logiciel : equipement.getLogiciels()) {
		    	logiciels += ","+logiciel.getNom();
		    }
		    table.addCell(logiciels);
	    }
	    
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);

	    document.add(table);
	}

}
