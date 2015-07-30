package application.pdf.export;

import java.util.List;

import javafx.scene.control.TableView;
import model.Equipement;

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
public class PDFEquipementListExport extends PDFDataExport {
	
	private List<Equipement> equipements;
	private TableView<Equipement> tableViewEquipement;
	
	public PDFEquipementListExport (List<Equipement> equipementsParam) {
		equipements = equipementsParam;
	}

	public PDFEquipementListExport (TableView<Equipement> tableViewEquipementParam) {
		tableViewEquipement = tableViewEquipementParam;
	}
	
	@Override
	public void write (Document document, PdfWriter writer) throws DocumentException {
		
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		
		Paragraph title = new Paragraph("Liste des équipements", catFont);
		title.setAlignment(Element.ALIGN_CENTER);
		
		document.add(title);
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		PdfPTable table = new PdfPTable(5);

	    PdfPCell c1 = new PdfPCell(new Phrase("Nom Calife"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Type"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Valeur (€)"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Pole"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("N° CP Agent"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);
	    
	    if (equipements != null)
	    	insertFromList(table, equipements);
	    else if (tableViewEquipement != null) 
	    	insertFromTable(table, tableViewEquipement);
	    
	    
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);

	    document.add(table);
	}
	
	private void insertFromList (PdfPTable table, List<Equipement> equipements) {
		for (Equipement equipement : equipements) {
		    table.addCell(String.valueOf(equipement.getNomCalife()));
		    table.addCell(String.valueOf(equipement.getTypeEquipement().getNom()));
		    table.addCell(String.valueOf(equipement.getPrix()));
		    table.addCell(String.valueOf(equipement.getPole().getNom()));
		    if (equipement.getAgent() != null) {
		    	table.addCell(equipement.getAgent().getNumCP());
		    } else {
		    	table.addCell("");
		    }
	    }
	}
	
	private void insertFromTable (PdfPTable table, TableView<Equipement> equipements) {
		List<Equipement> list = equipements.getItems();
		insertFromList(table, list);
	}

}
