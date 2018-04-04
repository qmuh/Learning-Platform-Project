package backend.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import backend.interfaces.DatabaseProperties;
import backend.table.*;

public class Database implements DatabaseProperties
{

	/**
	 * Used to connect to the database
	 */
	private Connection dbConnection;

	/**
	 * Used to create DB
	 */
	private Statement statement;

	/**
	 * Used for many different aspects for the table
	 */
	private PreparedStatement preparedStatement;

	AssignmentTable assignmentTable;
	CourseTable courseTable;
	GradeTable gradeTable;
	StudentEnrollmentTable studentEnrollmentTable;
	UserTable userTable;
	SubmissionTable submissionTable;

	/**
	 * Constructor for the DB to initialize the connection
	 */
	public Database()
	{
		try
		{
			// If this throws an error, make sure you have added the mySQL
			// connector JAR to the project
			Class.forName("com.mysql.jdbc.Driver");

			// If this fails make sure your connectionInfo and login/password
			// are correct

			Properties connectionProperties = createDatabaseProperties();

			dbConnection = DriverManager.getConnection(CONNECTION_URL,
					connectionProperties);
			System.out.println("Connected to: " + CONNECTION_URL + "\n");
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates a properties object to be used by a DriverManager to log into a
	 * database.
	 * 
	 * @return the login properties
	 */
	private Properties createDatabaseProperties()
	{
		Properties databaseProperties = new Properties();
		databaseProperties.put("user", USERNAME);
		databaseProperties.put("password", PASSWORD);

		return databaseProperties;
	}

	/**
	 * Used to create the database itself
	 */
	public void createDB()
	{
		try
		{
			statement = dbConnection.createStatement();
			statement.executeUpdate("CREATE DATABASE " + DATABASE_NAME);
			System.out.println("Created Database " + DATABASE_NAME);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addAllTables()
	{
		assignmentTable = new AssignmentTable(dbConnection, "AssignmentTable");
		userTable = new UserTable(dbConnection, "UserTable");
		courseTable = new CourseTable(dbConnection, "CourseTable");
		studentEnrollmentTable = new StudentEnrollmentTable(dbConnection,
				"StudentEnrollmentTable");
		submissionTable = new SubmissionTable(dbConnection, "SubmissionTable");
		gradeTable = new GradeTable(dbConnection, "GradeTable");

	}

	public void removeAllTables()
	{
		assignmentTable.removeTable();
		userTable.removeTable();
		courseTable.removeTable();
		studentEnrollmentTable.removeTable();
		submissionTable.removeTable();
		gradeTable.removeTable();

	}

	public void createAllTables()
	{
		assignmentTable.createTable();
		userTable.createTable();
		courseTable.createTable();
		studentEnrollmentTable.createTable();
		submissionTable.createTable();
		gradeTable.createTable();

	}

	public static void main(String[] args)
	{
		Database myDatabase = new Database();
		// myDatabase.createDB();
		myDatabase.addAllTables();
		// myDatabase.createAllTables();
		// myDatabase.removeAllTables();

		// myDatabase.userTable.add(new Student(30016415, "Qasim", "Muhammad",
		// "qasim.muhammad@ucalgary.ca", "S", "qazxsw"));
		// myDatabase.userTable.add(new Student(39817100, "Jimmy", "Truong",
		// "jimmy.truong@ucalgary.ca", "P", "qazxsw"));

		// THIS IS USED TO TEST USER VALIDATION

		// User myUser = myDatabase.userTable.validateUser(39817100, "qazxsw");
		/*
		 * if(myUser == null) {
		 * System.out.println("Incorrect password or user id"); }
		 * 
		 * else if(myUser.getUserType().equals("S")) {
		 * System.out.println("The user is a student with the name of " +
		 * myUser.getFirstName()); }
		 * 
		 * else if (myUser.getUserType().equals("P")) {
		 * System.out.println("The user is a professor with the name of " +
		 * myUser.getFirstName()); }
		 * 
		 */

		// myDatabase.assignmentTable.add(new Assignment(1000, 1234, "Darin
		// Sucks", "a pathway", true, "January 3, 12pm"));

	}

}
