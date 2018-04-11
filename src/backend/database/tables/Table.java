package backend.database.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public abstract class Table<E>
{

	/**
	 * Used to create connection with a SQL database
	 */
	public Connection dbConnection;

	/**
	 * The name for a table
	 */
	public String tableName;

	/**
	 * Statement used for querying a database
	 */
	public PreparedStatement preparedStatement;

	/** Constructor for a database
	 * @param connectionToDB The connection to allow connection with SQL
	 * @param table The name of the table
	 */
	public Table(Connection connectionToDB, String table)
	{
		dbConnection = connectionToDB;
		tableName = table;
	}

	/**
	 * Implementation needed for all tables
	 * 
	 * @param toAdd
	 *            Object being added to the table
	 */
	abstract public void add(E toAdd);

	/**
	 * Creates a table which is then later used by the table
	 */
	abstract public void createTable();

	/**
	 * Removes table from the database
	 */
	public void removeTable()
	{
		String sql = "DROP TABLE " + tableName;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Removed Table " + tableName);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
