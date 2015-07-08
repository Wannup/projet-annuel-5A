package application.database;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tools.Config;

/**
 * DatabaseConnection est la classe permettant d'instancier et fermer une connexion à la base de données.
 * @author: Mike FIARI
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
    	
    	Map<String, String> configurationBdd = new HashMap<String, String>();
    	configurationBdd.put("javax.persistence.jdbc.driver", Config.getProperty("javax.persistence.jdbc.driver"));
    	configurationBdd.put("javax.persistence.jdbc.url", Config.getProperty("javax.persistence.jdbc.url"));
    	configurationBdd.put("javax.persistence.jdbc.user", Config.getProperty("javax.persistence.jdbc.user"));
    	configurationBdd.put("javax.persistence.jdbc.password", Config.getProperty("javax.persistence.jdbc.password"));
    	
    	emf = Persistence.createEntityManagerFactory("lgpiPersistence", configurationBdd);
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
