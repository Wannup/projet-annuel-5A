package application.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * DatabaseConnection est la classe permettant d'instancier et fermer une connexion à la base de données.
 * 
 * @version 1.0
 */
public class DatabaseConnection {

	private static EntityManagerFactory emf;
    public static EntityManager em;
    
    /**
     * Retourne un manager de la base de données.
     * 
     * @return EntityManager.
     * 
     * @see EntityManager
     */
    public static EntityManager startConnection(){
        emf = Persistence.createEntityManagerFactory("lgpiPersistence");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }
    
    /**
     * Rafraichi la base de données.
     * 
     */
    public static void refresh () {
    	/*em.getEntityManagerFactory().getCache().evictAll();
    	em.clear();*/
    }
 
    /**
     * ferme la connection.
     */
    public static void closeConnection(){
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
	
}
