package dao;

import model.Agent;
import application.database.DatabaseConnection;

public class AgentDao {
 
    public void save(Agent agent){
        DatabaseConnection.em.persist(agent);
    }
 
    public Agent find(int agentId){
        return DatabaseConnection.em.find(Agent.class, agentId);
    }
 
    public void remove(Agent agent){
        DatabaseConnection.em.remove(agent);
    }
 
}
