package application.pdf.export;

import java.util.List;

import model.Equipement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFEquipementListExport extends PDFDataExport {
	
	private List<Equipement> equipements;
	
	public PDFEquipementListExport (List<Equipement> equipements) {
		this.equipements = equipements;
	}
	
	public void write (Document document) throws DocumentException {
		
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		
		Paragraph title = new Paragraph("Liste des équipements", catFont);
		title.setAlignment(Element.ALIGN_CENTER);
		
		document.add(title);
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		PdfPTable table = new PdfPTable(5);

	    PdfPCell c1 = new PdfPCell(new Phrase("Nom"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Numéro de poste"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Agent"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Logiciels"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Prix"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);
	    
	    for (Equipement equipement : equipements) {
	    	table.addCell(equipement.getNom());
		    table.addCell(String.valueOf(equipement.getNumeroPoste()));
		   // table.addCell(equipement.getAgent().getNom());
		    table.addCell(""); // temporaire car agent null
		    String logiciels = "";
		    table.addCell(logiciels);
		    table.addCell(String.valueOf(equipement.getPrix()));
	    }
	    
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);

	    document.add(table);
	}

}
