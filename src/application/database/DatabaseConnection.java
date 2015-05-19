package application.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {

	private static EntityManagerFactory emf;
    public static EntityManager em;
    
    public static EntityManager startConnection(){
        emf = Persistence.createEntityManagerFactory("lgpiPersistence");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }
    
    public static void refresh () {
    	/*em.getEntityManagerFactory().getCache().evictAll();
    	em.clear();*/
    }
 
    public static  void closeConnection(){
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
	
}
