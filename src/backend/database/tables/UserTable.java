package backend.database.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import shared.interfaces.UserInfo;
import shared.objects.Professor;
import shared.objects.Student;
import shared.objects.User;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class UserTable extends Table<User> implements UserInfo
{

	/** Initializes the table using the super
	 * @param connectionToDB The connection to connect with
	 * @param tableName The name of the table
	 */
	public UserTable(Connection connectionToDB, String tableName)
	{
		super(connectionToDB, tableName);
	}

	@Override
	public void add(User toAdd)
	{
		String sql = "INSERT INTO " + tableName + " VALUES" + "(?,?,?,?,?,?)";
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, toAdd.getId());
			preparedStatement.setString(2, toAdd.getPassword());
			preparedStatement.setString(3, toAdd.getEmail());
			preparedStatement.setString(4, toAdd.getFirstName());
			preparedStatement.setString(5, toAdd.getLastName());
			preparedStatement.setString(6, toAdd.getUserType().toString());
			preparedStatement.executeUpdate();

			System.out.println("Added user " + toAdd.getFirstName()
					+ " who is a " + toAdd.getUserType());
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" + "ID INT(8) NOT NULL, "
				+ "PASSWORD VARCHAR(20) NOT NULL, "
				+ "EMAIL VARCHAR(50) NOT NULL, "
				+ "FIRSTNAME VARCHAR(30) NOT NULL, "
				+ "LASTNAME VARCHAR(30) NOT NULL, "
				+ "TYPE VARCHAR(1) NOT NULL," + "PRIMARY KEY ( id ) )";

		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Created Table " + tableName);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Used to validate a user for the LoginGUI
	 * 
	 * @param user_ID
	 * @param password
	 * @return
	 */
	public User validateUser(int user_ID, String password)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE ID= ? "
				+ "AND PASSWORD =?";
		ResultSet user;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, user_ID);
			preparedStatement.setString(2, password);
			user = preparedStatement.executeQuery();
			if (user.next())
			{
				if (user.getString("TYPE").equals("S"))
				{
					return new Student(user.getInt("ID"),
							user.getString("FIRSTNAME"),
							user.getString("LASTNAME"), user.getString("EMAIL"),
							user.getString("PASSWORD"));
				} else
				{
					return new Professor(user.getInt("ID"),
							user.getString("FIRSTNAME"),
							user.getString("LASTNAME"), user.getString("EMAIL"),
							user.getString("PASSWORD"));
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/** Searches for a user given their ID and then returns said user
	 * @param userID The user which is being searched for
	 * @return The User object, contains null if none are found
	 */
	public User getUserByID(int userID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE ID= ? ";

		ResultSet user;

		try
		{

			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			user = preparedStatement.executeQuery();
			if (user.next())
			{
				if(user.getString("TYPE").equals("S"))
				{
				return new Student(user.getInt("ID"),
						user.getString("FIRSTNAME"), user.getString("LASTNAME"),
						user.getString("EMAIL"), user.getString("PASSWORD"));
				}
			
				else {
					return new Professor(user.getInt("ID"),
							user.getString("FIRSTNAME"), user.getString("LASTNAME"),
							user.getString("EMAIL"), user.getString("PASSWORD"));
<<<<<<< HEAD
					
				}
				
			
=======
				}
>>>>>>> origin/StudentGUI
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/** Searches for users given their last name for the query key
	 * @param string The last name that off being searched for 
	 * @return Vector of student object 
	 */
	public Vector<Student> searchLastName(String string)
	{
		String sql = "SELECT * FROM " + tableName
				+ " WHERE LASTNAME= ? AND TYPE= ?";
		Vector<Student> userList = new Vector<Student>();
		ResultSet user;

		try
		{

			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, string);
			preparedStatement.setString(2, "S");
			user = preparedStatement.executeQuery();
			while (user.next())
			{
				userList.add(new Student(user.getInt("ID"),
						user.getString("FIRSTNAME"), user.getString("LASTNAME"),
						user.getString("EMAIL"), user.getString("PASSWORD")));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return userList;
	}

	public Vector<Student> allStudents()
	{
		String sql = "SELECT * FROM " + tableName + " WHERE TYPE= ? ";
		Vector<Student> userList = new Vector<Student>();
		ResultSet user;

		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, IS_STUDENT);
			user = preparedStatement.executeQuery();
			while (user.next())
			{
				userList.add(new Student(user.getInt("ID"),
						user.getString("FIRSTNAME"), user.getString("LASTNAME"),
						user.getString("EMAIL"), user.getString("PASSWORD")));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return userList;
	}
}
