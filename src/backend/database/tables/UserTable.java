package backend.database.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sharedobjects.Professor;
import sharedobjects.Student;
import sharedobjects.User;

public class UserTable extends Table<User>
{

	public UserTable(Connection connectionToDB, String tableName)
	{
		super(connectionToDB, tableName);
	}

	@Override
	public void add(User toAdd)
	{
		String sql = "INSERT INTO " + tableName +
				" VALUES" + "(?,?,?,?,?,?)";;
		try{
			
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1,toAdd.getId());
			preparedStatement.setString(2, toAdd.getPassword());
			preparedStatement.setString(3, toAdd.getEmail());
			preparedStatement.setString(4, toAdd.getFirstName());
			preparedStatement.setString(5, toAdd.getLastName());
			preparedStatement.setString(6, toAdd.getUserType());
			preparedStatement.executeUpdate();
			
			System.out.println("Added user " + toAdd.getFirstName() + " who is a " + toAdd.getUserType());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "PASSWORD VARCHAR(20) NOT NULL, " + 
			     "EMAIL VARCHAR(50) NOT NULL, " + 
			     "FIRSTNAME VARCHAR(30) NOT NULL, " + 
			     "LASTNAME VARCHAR(30) NOT NULL, " + 
			     "TYPE VARCHAR(1) NOT NULL," +
			     "PRIMARY KEY ( id ) )";
		
		try{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Created Table " + tableName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/** Used to validate a user for the LoginGUI
	 * @param user_ID
	 * @param password
	 * @return
	 */
	public User validateUser(int user_ID, String password) 
	{
		String sql = "SELECT * FROM " + tableName + " WHERE ID= ? " + "AND PASSWORD =?";
		ResultSet user;
		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, user_ID);
			preparedStatement.setString(2, password);
			user = preparedStatement.executeQuery();
			if(user.next())
			{
				if(user.getString("TYPE").equals("S"))
				{	return new Student(user.getInt("ID"),
								user.getString("FIRSTNAME"), 
								user.getString("LASTNAME"), 
								user.getString("EMAIL"),
								user.getString("TYPE"),
								user.getString("PASSWORD"));
				}
				
				else
				{
					return new Professor(user.getInt("ID"),
							user.getString("FIRSTNAME"), 
							user.getString("LASTNAME"), 
							user.getString("EMAIL"),
							user.getString("TYPE"),
							user.getString("PASSWORD"));
					
				}
				
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
		
	}
	


}
