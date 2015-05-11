package dao;

import model.Equipement;
import application.database.DatabaseConnection;

public class EquipementDao {

	public void save(Equipement equipement){
        DatabaseConnection.em.persist(equipement);
    }
 
    public Equipement find(int equipementId){
        return DatabaseConnection.em.find(Equipement.class, equipementId);
    }
 
    public void remove(Equipement equipement){
        DatabaseConnection.em.remove(equipement);
    }
}
