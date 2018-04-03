package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Database
{

	/**
	 * Used to connect to the database
	 */
	Connection dbConnected;
	
	/**
	 * Used for many different aspects for the table
	 */
	PreparedStatement preparedStatement;
	
	
	/**
	 * Constructor for the DB to initialize the connection
	 */
	public Database()
	{
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Used to create the database itself
	 */
	public void createDB() {
		
	}
}
