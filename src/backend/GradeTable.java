package backend;

import java.sql.Connection;
import java.sql.SQLException;

import sharedobjects.Grade;

public class GradeTable extends Table
{
	public String tableName = "GradeTable";
	
	public GradeTable(Connection connectionToDB, String tableName)
	{
		super(connectionToDB, tableName);
	}

	@Override
	public void add(Object toAdd)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "ASSIGNID INT(8) NOT NULL, " + 
			     "STUDENTID INT(8) NOT NULL, " + 
			     "COURSEID INT(8) NOT NULL, " + 
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
	
	
	// ??? SEEMS WRONG GUYS
	/** Search for a grade by using assignment ID
	 * @param assign_id
	 * @return
	 */
	public Grade searchByAssignmentId(int assign_id) 
	{
		Grade myGrade= null;
		
		
		return myGrade;
		
	}
}
