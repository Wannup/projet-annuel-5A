package application.pdf.export;

import java.util.List;

import javafx.scene.control.TableView;
import model.Agent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class PDFAgentListExport extends PDFDataExport {
	
	private List<Agent> agents;
	private TableView<Agent> tableViewAgent;
	
	public PDFAgentListExport (List<Agent> agentsParam) {
		agents = agentsParam;
	}

	public PDFAgentListExport (TableView<Agent> tableViewAgentParam) {
		tableViewAgent = tableViewAgentParam;
	}
	
	@Override
	public void write (Document document, PdfWriter writer) throws DocumentException {
		
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

	    c1 = new PdfPCell(new Phrase("Pôle/Service"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("N° de CP"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Téléphone"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);
	    
	    if (agents != null)
	    	insertFromList(table, agents);
	     else if (tableViewAgent != null) 
	    	insertFromTable(table, tableViewAgent);
	    
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);

	    document.add(table);
	}
	
	private void insertFromList (PdfPTable table, List<Agent> agents) {
		for (Agent agent : agents) {
	    	table.addCell(agent.getNom());
		    table.addCell(agent.getPrenom());
		    table.addCell(agent.getPole().getNom());
		    table.addCell(agent.getNumCP());
		    table.addCell(agent.getTel());
	    }
	}
	
	private void insertFromTable (PdfPTable table, TableView<Agent> agents) {
		List<Agent> list = agents.getItems();
		insertFromList(table, list);
	}

}