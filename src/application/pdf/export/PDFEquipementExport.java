package application.pdf.export;

import model.Equipement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

public class PDFEquipementExport extends PDFDataExport {
	
	private Equipement equipement;
	
	public PDFEquipementExport (Equipement equipement) {
		this.equipement = equipement;
	}

	@Override
	public void write(Document document) throws DocumentException {
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		
		Paragraph title = new Paragraph("Information", catFont);
		title.setAlignment(Element.ALIGN_CENTER);
		
		document.add(title);
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
	}

}
