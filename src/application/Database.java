package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public static void init(){
		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:database/lgpi", "lgpi",  "lgpi");
			Statement statement = connexion.createStatement() ;
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS equipements (idEquipement INT IDENTITY, numPoste VARCHAR(64), prix INT, numCPAgent INT)");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS logiciels (idLogiciel INT IDENTITY, libelle VARCHAR(64), valeur INT, duree INT)");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS agents (idAgent INT IDENTITY, nom VARCHAR(64), prenom VARCHAR(64), dateDeNaissance DATE, numCPAgent INT, numPoste INT)");
			statement.executeQuery("SHUTDOWN");
			statement.close();
			connexion.close() ;
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public static ResultSet doRequest(String request){
		ResultSet rs = null;
		try {
			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:database/lgpi", "lgpi",  "lgpi");
			
		    Statement statement = connexion.createStatement();
			rs = statement.executeQuery(request);
			
			statement.executeQuery("SHUTDOWN");			
			statement.close();			
			connexion.close() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
}
