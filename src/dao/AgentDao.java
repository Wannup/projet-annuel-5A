package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Agent;
import application.database.DatabaseConnection;

/**
 * class AgentDao
 * @author Charly FAROT
 * @version 1.0
 */
public class AgentDao extends AbstractDao<Agent> {

	public AgentDao() {
		super(Agent.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Agent> searchWithAttributes(String search) {

		List<Agent> results;
		// set up the Criteria query
		CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<Agent> table = cq.from(Agent.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(
				cb.or(cb.like(cb.lower((Expression) table.get("nom")), "%"+ search.toLowerCase() + "%"), 
				cb.like(cb.lower((Expression) table.get("prenom")), "%"+ search.toLowerCase() + "%"), 
				cb.like(cb.lower((Expression) table.get("pole")), "%"+ search.toLowerCase() + "%"),
				cb.like(cb.lower((Expression) table.get("tel")), "%"+ search.toLowerCase() + "%"),
				cb.like(cb.lower((Expression) table.get("numPoste")), "%"+ search.toLowerCase() + "%"),
				cb.like(cb.lower((Expression)  table.get("numCP")), "%"+ search.toLowerCase() + "%")));

		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery q = DatabaseConnection.em.createQuery(cq);
        
		results = q.getResultList();
		
		return results;
	}
}
