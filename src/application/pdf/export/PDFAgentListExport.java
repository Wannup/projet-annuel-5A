package application.pdf.export;

import java.util.List;

import model.Agent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * PDFAgentListExport est la classe permettant d'exporter une liste d'agent au format PDF.
 * 
 * @version 1.0
 */
public class PDFAgentListExport extends PDFDataExport {
	
	private List<Agent> agents;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param agents
	 *     La liste des agents
	 * @see List
	 * @see Agent
	 */
	public PDFAgentListExport (List<Agent> agents) {
		this.agents = agents;
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
		
		Paragraph title = new Paragraph("Liste des agents", catFont);
		title.setAlignment(Element.ALIGN_CENTER);
		
		document.add(title);
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		PdfPTable table = new PdfPTable(5);

	    PdfPCell c1 = new PdfPCell(new Phrase("Nom"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Prénom"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Date de naissance"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("N° de CP"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("N° de poste"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);
	    
	    for (Agent agent : agents) {
	    	table.addCell(agent.getNom());
		    table.addCell(agent.getPrenom());
		    table.addCell(agent.getDateDeNaissance());
		    table.addCell(agent.getNumCP());
		    table.addCell(agent.getNumPoste());
	    }
	    
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);

	    document.add(table);
	}

}