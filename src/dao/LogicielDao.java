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
import model.Logiciel;

public class LogicielDao extends AbstractDao<Logiciel>{

	public LogicielDao(){
		super(Logiciel.class);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Logiciel> searchWithAttributes(String search) {

		List<Logiciel> results;
		// set up the Criteria query
		CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<Logiciel> table = cq.from(Logiciel.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(
				cb.or(cb.like(cb.lower((Expression) table.get("nom")), "%"+ search.toLowerCase() + "%"), 
				cb.like(cb.lower((Expression) table.get("prix")), "%"+ search.toLowerCase() + "%"), 
				cb.like(cb.lower((Expression) table.get("licenceNumber")), "%"+ search.toLowerCase() + "%")));

		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery q = DatabaseConnection.em.createQuery(cq);
        
		results = q.getResultList();
		
		return results;
	}
}
