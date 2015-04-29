package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public static void init(){
		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:database/lgpi", "lgpi",  "lgpi");
			Statement statement = connexion.createStatement() ;
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS equipements (idEquipement INT IDENTITY, numPoste VARCHAR(64), prix INT, numCPAgent int)");
			statement.executeQuery("SHUTDOWN");
			statement.close();
			connexion.close() ;
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public static void doRequest(String request){
		try {
			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:database/lgpi", "lgpi",  "lgpi");
			
			Statement statement;
			statement = connexion.createStatement();
			statement.executeUpdate(request);
			statement.executeQuery("SHUTDOWN");
			
			statement.close();			
			connexion.close() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
