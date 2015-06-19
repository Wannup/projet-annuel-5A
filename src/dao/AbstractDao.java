package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import application.database.DatabaseConnection;

public abstract class AbstractDao<T> {

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.entityClass = (Class<T>) getClass();
	}
	
	public AbstractDao(Class<T> clazz) {
		this.entityClass = clazz;
	}

	public void save(T element) {
		if (element != null)
			DatabaseConnection.em.persist(element);
	}

	public T find(int elementId) {
		return DatabaseConnection.em.find(this.entityClass, elementId);
	}

	public void remove(T element) {
		if (element != null)
			DatabaseConnection.em.remove(element);
	}

	public void update (T element) {
		if(element != null)
			DatabaseConnection.em.merge(element);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByAttributesLike(Map<String, String> attributes) {

		List<T> results;
		// set up the Criteria query
		CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery();
		Root<T> foo = cq.from(entityClass);
		if (attributes != null) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (String s : attributes.keySet()) {
				if (foo.get(s) != null) {
					predicates.add(cb.like((Expression) foo.get(s), "%"
							+ attributes.get(s) + "%"));
				}
			}
			cq.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<T> q = DatabaseConnection.em.createQuery(cq);

		results = q.getResultList();
		return results;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByAttributesEquals(Map<String, String> attributes) {

		List<T> results;
		// set up the Criteria query
		CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery();
		Root<T> foo = cq.from(entityClass);
		if (attributes != null) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (String s : attributes.keySet()) {
				if (foo.get(s) != null) {
					predicates.add(cb.equal((Expression) foo.get(s), attributes.get(s)));
				}
			}
			cq.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<T> q = DatabaseConnection.em.createQuery(cq);

		results = q.getResultList();
		return results;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByAttributesLikeWithLimits(Map<String, String> attributes, int debut, int nbLigne) {

		List<T> results;
		// set up the Criteria query
		CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery();
		Root<T> foo = cq.from(entityClass);
		if (attributes != null) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (String s : attributes.keySet()) {
				if (foo.get(s) != null) {
					predicates.add(cb.like((Expression) foo.get(s), "%"
							+ attributes.get(s) + "%"));
				}
			}
			cq.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<T> q = DatabaseConnection.em.createQuery(cq);
		q.setFirstResult(debut);
		q.setMaxResults(nbLigne);

		results = q.getResultList();
		return results;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getNbResultLike(Map<String, String> attributes) {
		// set up the Criteria query
		CriteriaBuilder cb = DatabaseConnection.em.getCriteriaBuilder();
		CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery();
		Root<T> foo = cq.from(entityClass);
		if (attributes != null) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (String s : attributes.keySet()) {
				if (foo.get(s) != null) {
					predicates.add(cb.like((Expression) foo.get(s), "%"
							+ attributes.get(s) + "%"));
				}
			}
			cq.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<T> q = DatabaseConnection.em.createQuery(cq);
		return q.getResultList().size();
	}
}
