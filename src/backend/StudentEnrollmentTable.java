package backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import sharedobjects.Assignment;

public class StudentEnrollmentTable extends Table
{

	public String tableName = "StudentEnrollmentTable";
	
	public StudentEnrollmentTable(Connection connectionToDB, String tableName)
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

	/** Searches for the list of course IDs from a specific student
	 * @param studentID
	 * @return
	 */
	public Vector<Integer> getCourseIDs(int studentID)
	{
		Vector<Integer> myCourseIDs = new Vector<Integer>();
		
		return myCourseIDs;
	}

	/** Searches for the list of student IDs who are enrolled in a specific class
	 * Used by the professor
	 * @param courseID
	 * @return
	 */
	public Vector<Integer> getStudentIDs(int courseID)
	{
		Vector<Integer> listOfStudentIDs = new Vector<Integer>();
		
		return listOfStudentIDs;
	}

}
