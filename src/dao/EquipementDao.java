package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import application.database.DatabaseConnection;
import model.Equipement;

public class EquipementDao extends AbstractDao<Equipement>{
	public EquipementDao() {
		super(Equipement.class);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Equipement> searchWithAttributes(String search) {

		List<Equipement> results;
		// set up the Criteria query
		CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<Equipement> table = cq.from(Equipement.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		int nombre = 0;
		boolean isInteger = false;
		try{
			nombre = Integer.parseInt(search);
			isInteger = true;
		}
		catch(NumberFormatException e){
			
		}
		
		double doubleValue = 0;
		boolean isDouble = false;
		try{
			doubleValue = Double.parseDouble(search);
			System.out.println(doubleValue);
		}
		catch(NumberFormatException e){
			
		}
		
		if(!isInteger && !isDouble)
		predicates.add(
				cb.or(cb.like(cb.lower((Expression) table.get("dateAchat")), "%"+ search.toLowerCase() + "%"), 
				cb.like(cb.lower((Expression)  table.get("typeEquipement")), "%"+ search.toLowerCase() + "%")));
		else if(isInteger)
			predicates.add(cb.or(cb.equal((Expression) table.get("numeroEquipement"), nombre), 
					cb.equal((Expression) table.get("nbJoursPrevUtilisation"), nombre),  cb.equal((Expression)  table.get("prix"), nombre)));
		else{
			predicates.add(cb.equal((Expression)  table.get("prix"), doubleValue));
		}
		
		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery q = DatabaseConnection.em.createQuery(cq);
        
		results = q.getResultList();
		
		return results;
	}
}
