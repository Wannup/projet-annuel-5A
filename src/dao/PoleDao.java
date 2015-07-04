package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Pole;
import application.database.DatabaseConnection;

/**
 * class PoleDao
 * @author Charly FAROT
 * @version 1.0
 */
public class PoleDao extends AbstractDao<Pole>{
	public PoleDao() {
		super(Pole.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Pole> searchWithAttributes(String search) {

		List<Pole> results;
		// set up the Criteria query
		CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<Pole> table = cq.from(Pole.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(
				cb.or(cb.like(cb.lower((Expression) table.get("nom")), "%"+ search.toLowerCase() + "%")));

		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery q = DatabaseConnection.em.createQuery(cq);
        
		results = q.getResultList();
		
		return results;
	}
}
