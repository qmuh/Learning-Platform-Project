package backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import backend.database.*;
import backend.database.tables.AssignmentTable;
import backend.database.tables.CourseTable;
import backend.database.tables.GradeTable;
import backend.database.tables.StudentEnrollmentTable;
import backend.database.tables.SubmissionTable;
import backend.database.tables.UserTable;
import backend.interfaces.DatabaseProperties;
import sharedobjects.Professor;
import sharedobjects.Student;

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
			addAllTables();
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

	public AssignmentTable getAssignmentTable()
	{
		return assignmentTable;
	}

	public CourseTable getCourseTable()
	{
		return courseTable;
	}

	public GradeTable getGradeTable()
	{
		return gradeTable;
	}

	public StudentEnrollmentTable getStudentEnrollmentTable()
	{
		return studentEnrollmentTable;
	}

	public UserTable getUserTable()
	{
		return userTable;
	}

	public SubmissionTable getSubmissionTable()
	{
		return submissionTable;
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
		//myDatabase.addAllTables();
		// myDatabase.createAllTables();
		// myDatabase.removeAllTables();
		
		/*
		 myDatabase.userTable.add(new Student(30016415, "Qasim", "Muhammad",
		 "qasim.muhammad@ucalgary.ca", "S", "qazxsw"));
		 myDatabase.userTable.add(new Professor(39817100, "Jimmy", "Truong",
		"jimmy.truong@ucalgary.ca", "P", "qazxsw"));
		*/
		// myDatabase.userTable.add(new Professor(1, "First", "Last",
		//			"first.last@ucalgary.ca", "P", "1"));
	}

}
