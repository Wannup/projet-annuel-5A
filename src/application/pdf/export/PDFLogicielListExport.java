package application.pdf.export;

import java.util.List;

import javafx.scene.control.TableView;
import model.Logiciel;

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
public class PDFLogicielListExport extends PDFDataExport {
	
	private List<Logiciel> logiciels;
	private TableView<Logiciel> tableViewLogiciels;
	
	public PDFLogicielListExport (List<Logiciel> logicielsParam) {
		logiciels = logicielsParam;
	}

	public PDFLogicielListExport (TableView<Logiciel> tableViewLogicielsParam) {
		tableViewLogiciels = tableViewLogicielsParam;
	}
	
	@Override
	public void write (Document document, PdfWriter writer) throws DocumentException {
		
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		
		Paragraph title = new Paragraph("Liste des logiciels", catFont);
		title.setAlignment(Element.ALIGN_CENTER);
		
		document.add(title);
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		PdfPTable table = new PdfPTable(3);

	    PdfPCell c1 = new PdfPCell(new Phrase("Nom logiciel"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("N° licence"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Prix licence (€)"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);
	    
	    if (logiciels != null)
	    	insertFromList(table, logiciels);
	    else if (tableViewLogiciels != null)
	    	insertFromTable(table, tableViewLogiciels);
	        
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);
	    document.add(table);
	}
	
	private void insertFromList (PdfPTable table, List<Logiciel> logiciels) {
		for (Logiciel logiciel : logiciels) {
	    	table.addCell(logiciel.getNom());
		    table.addCell(String.valueOf(logiciel.getLicenceNumber()));
		    table.addCell(String.valueOf(logiciel.getPrix()));
	    }
	}
	
	private void insertFromTable (PdfPTable table, TableView<Logiciel> logiciels) {
		List<Logiciel> list = logiciels.getItems();
		insertFromList(table, list);
	}

}