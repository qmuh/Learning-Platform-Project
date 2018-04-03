package backend;

import java.sql.SQLException;
import java.util.Vector;
import java.sql.Connection;
import java.sql.PreparedStatement;

import sharedobjects.Assignment;

public class AssignmentTable extends Table
{
	String tableName = "AssignmentTable";
	
	public AssignmentTable(Connection connectionToDB, String tableName)
	{
		super(connectionToDB, tableName);
	}
	
	@Override
	public void add(Object toAdd)
	{
		
	}

	@Override
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "COURSEID INT(8) NOT NULL, " + 
			     "TITLE VARCHAR(50) NOT NULL, " + 
			     "PATH VARCHAR(100) NOT NULL, " + 
			     "ACTIVE BIT(1) NOT NULL, " + 
			     "DUEDATE VARCHAR(16) NOT NULL," +
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

	public Vector<Assignment> searchByCourseID(int courseID)
	{
		Vector<Assignment> myAssignments = new Vector<Assignment>();
		
		return myAssignments;
		
	}
	
}
