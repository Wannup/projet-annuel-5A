package application.pdf.export;

import java.util.List;

import model.Agent;
import model.Equipement;
import model.Logiciel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * class PDFEquipementExport
 * @author: Mike FIARI
 * @version 1.0
 */

public class PDFEquipementExport extends PDFDataExport {
	
	private Equipement equipement;
	
	public PDFEquipementExport (Equipement equipement) {
		this.equipement = equipement;
	}

	@Override
	public void write(Document document, PdfWriter writer) throws DocumentException {
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		
		Paragraph title = new Paragraph("Information", catFont);
		title.setAlignment(Element.ALIGN_CENTER);
		
		document.add(title);
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		ColumnText ct = new ColumnText(writer.getDirectContent());
		ct.setAlignment(Element.ALIGN_LEFT);
		ct.setSimpleColumn(50, 36, 296, 700);
		ct.addText(new Phrase("Equipement"));
		ct.go();
		ct.setSimpleColumn(60, 36, 296, 680);
		ct.addText(new Phrase("Type : "));
		ct.go();
		ct.setSimpleColumn(100, 36, 296, 680);
		ct.addText(new Phrase(this.equipement.getTypeEquipement().getNom()));
		ct.go();
		ct.setSimpleColumn(60, 36, 296, 660);
		ct.addText(new Phrase("Marque : "));
		ct.go();
		ct.setSimpleColumn(115, 36, 296, 660);
		ct.addText(new Phrase(this.equipement.getMarque()));
		ct.go();
		ct.setSimpleColumn(60, 36, 296, 640);
		ct.addText(new Phrase("Modèle : "));
		ct.go();
		ct.setSimpleColumn(110, 36, 296, 640);
		ct.addText(new Phrase(this.equipement.getModele()));
		ct.go();
		ct.setSimpleColumn(60, 36, 296, 620);
		ct.addText(new Phrase("Nom Calife : "));
		ct.go();
		ct.setSimpleColumn(130, 36, 296, 620);
		ct.addText(new Phrase(this.equipement.getNomCalife()));
		ct.go();
		ct.setSimpleColumn(60, 36, 296, 600);
		ct.addText(new Phrase("Date livraison : "));
		ct.go();
		ct.setSimpleColumn(145, 36, 296, 600);
		ct.addText(new Phrase(this.equipement.getDateLivraison()));
		ct.go();
		ct.setSimpleColumn(350, 36, 559, 700);
		ct.addText(new Phrase("Autres informations : "));
		ct.go();
		ct.setSimpleColumn(350, 36, 559, 680);
		ct.addText(new Phrase(equipement.getInfo()));
		ct.go();
		
		Agent agent = this.equipement.getAgent();
		ct.setSimpleColumn(50, 36, 296, 550);
		ct.addText(new Phrase("Agent"));
		ct.go();
		ct.setSimpleColumn(60, 36, 296, 530);
		ct.addText(new Phrase("Nom : "));
		ct.go();
		if (agent != null) {
			ct.setSimpleColumn(100, 36, 296, 530);
			ct.addText(new Phrase(agent.getNom()));
			ct.go();
		}
		ct.setSimpleColumn(60, 36, 296, 510);
		ct.addText(new Phrase("Prénom : "));
		ct.go();
		if (agent != null) {
			ct.setSimpleColumn(115, 36, 296, 510);
			ct.addText(new Phrase(agent.getPrenom()));
			ct.go();
		}
		ct.setSimpleColumn(60, 36, 296, 490);
		ct.addText(new Phrase("N° CP : "));
		ct.go();
		if (agent != null) {
			ct.setSimpleColumn(105, 36, 296, 490);
			ct.addText(new Phrase(agent.getNumCP()));
			ct.go();
		}
		
		List<Logiciel> logiciels = equipement.getLogiciels();
		ct.setSimpleColumn(50, 36, 296, 450);
		ct.addText(new Phrase("Logiciels installés : "));
		ct.go();

		ct.setSimpleColumn(50, 36, 296, 430);
		PdfPTable table = new PdfPTable(1);
		for (Logiciel logiciel : logiciels) {
			table.addCell(logiciel.getNom());
		}
		ct.addElement(table);
		ct.go();
	}

}
