package dao;

import model.Logiciel;
import application.database.DatabaseConnection;

public class LogicielDao {
	
	public void save(Logiciel logiciel){
        DatabaseConnection.em.persist(logiciel);
    }
 
    public Logiciel find(int logicielId){
        return DatabaseConnection.em.find(Logiciel.class, logicielId);
    }
 
    public void remove(Logiciel logiciel){
        DatabaseConnection.em.remove(logiciel);
    }
}
