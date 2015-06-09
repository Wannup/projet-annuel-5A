package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.TypeEquipement;
import application.database.DatabaseConnection;

public class TypeEquipementDao extends AbstractDao<TypeEquipement> {

	public TypeEquipementDao() {
		super(TypeEquipement.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TypeEquipement> searchWithAttributes(String search) {

		List<TypeEquipement> results;
		// set up the Criteria query
		CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<TypeEquipement> table = cq.from(TypeEquipement.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(
				cb.or(cb.like(cb.lower((Expression) table.get("nom")), "%"+ search.toLowerCase() + "%")));

		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery q = DatabaseConnection.em.createQuery(cq);
        
		results = q.getResultList();
		
		return results;
	}
}
