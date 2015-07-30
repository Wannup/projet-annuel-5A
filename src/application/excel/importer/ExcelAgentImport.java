package application.excel.importer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Agent;
import model.Pole;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import tools.LoadingFrame;
import dao.AgentDao;
import dao.PoleDao;

/**
 * @author: Mike FIARI
 * @version 1.0
 */
public class ExcelAgentImport extends ExcelDataImport {
	
	private List<Agent> agents;
	private List<String> errors;
	private LoadingFrame loadingFrame;
	
	private final int ID_CELL_NOM = 0;
	private final int ID_CELL_PRENOM = 1;
	private final int ID_CELL_TEL = 2;
	private final int ID_CELL_CP = 3;
	private final int ID_CELL_POLE = 4;
	
	public ExcelAgentImport (List<Agent> agentsParam, List<String> errorsParam, LoadingFrame loadingFrameParam) {
		agents = agentsParam;
		errors = errorsParam;
		loadingFrame = loadingFrameParam;
	}
	
	@Override
	public void read(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		
		PoleDao poleDao = new PoleDao();
		AgentDao agentDao = new AgentDao();
		
		int minRow = sheet.getFirstRowNum();
		int maxRow = sheet.getLastRowNum();
		int numLigne = minRow;

		for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext(); row = (HSSFRow) rowIt.next()) {
			loadingFrame.setProgress((double)numLigne / (double)maxRow);
			
			if (numLigne > minRow && !isRowEmpty(row)) {
				Agent agent = new Agent ();

				for(int numCell = 0; numCell<5; numCell++){
					cell = row.getCell(numCell);
					
					if(cell != null && cell.getCellType()!= Cell.CELL_TYPE_BLANK){
						switch (numCell) {
							case ID_CELL_NOM :
								agent.setNom(cell.getStringCellValue());
								break;
							case ID_CELL_PRENOM :
								agent.setPrenom(cell.getStringCellValue());
								break;
							case ID_CELL_TEL :
								agent.setTel(cell.getStringCellValue());
								break;
							case ID_CELL_CP :
								agent.setNumCP(cell.getStringCellValue());	
								break;
							case ID_CELL_POLE :
								String poleName = cell.getStringCellValue();
								Map<String, String> attributes = new HashMap<>();
								attributes.put("nom", poleName);
								List<Pole> poles = poleDao.findByAttributesEquals(attributes);
								Pole pole;
								if (poles.isEmpty()) {
									pole = new Pole(poleName);
									poleDao.save(pole);
								} 
								else
									pole = poles.get(0);
								
								agent.setPole(pole);
								break;
						}
					}
				}
				
				// vérification champs obligatoires
				if(agent.isValidToSave()){
					// vérification existence agent
					Map<String, String> attributes = new HashMap<>();
					attributes.put("numCP", agent.getNumCP());
					List<Agent> results = agentDao.findByAttributesEquals(attributes);
					if (results.isEmpty())
						agents.add(agent);
					else
						errors.add("Un agent avec le N°CP : " +agent.getNumCP()+ " existe déjà.");
				}
				else
					errors.add("La ligne n°"+(numLigne+1)+" du fichier excel à importer ne possède pas toutes les informations obligatoires pour un agent.");
			}
			if(row != null)
				numLigne++;
		}
	}

	private boolean isRowEmpty(HSSFRow row) {
	    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	            return false;
	    }
	    return true;
	}
}
