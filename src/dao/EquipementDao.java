package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Equipement;
import model.TypeEquipement;
import application.database.DatabaseConnection;

public class EquipementDao extends AbstractDao<Equipement>{
	public EquipementDao() {
		super(Equipement.class);
	}
	
	
	
	public List<Equipement> getEquipementByType(TypeEquipement type ) {
		  /*Query query = DatabaseConnection.em.createQuery( "SELECT * FROM equipement WHERE typeEquipement = ?1" );
		   query.setParameter( 1, idType);
		   return new ArrayList<Equipement>( query.getResultList() );*/
		   
		   CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		   CriteriaQuery cq = cb.createQuery();
			Root<Equipement> table = cq.from(Equipement.class);
			cq.where(cb.equal(table.get("typeEquipement"), type));
			TypedQuery q = DatabaseConnection.em.createQuery(cq);
	        
			List<Equipement> results = q.getResultList();
			
			return results;
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
				cb.or(cb.like(cb.lower((Expression) table.get("nomCalife")), "%"+ search.toLowerCase() + "%"), 
				cb.like(cb.lower((Expression)  table.get("typeEquipement")), "%"+ search.toLowerCase() + "%")));
		else if(isInteger)
			predicates.add(cb.or(cb.equal(table.get("prix"), nombre)));
		else{
			predicates.add(cb.equal(table.get("prix"), doubleValue));
		}
		
		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery q = DatabaseConnection.em.createQuery(cq);
        
		results = q.getResultList();
		
		return results;
	}
}
