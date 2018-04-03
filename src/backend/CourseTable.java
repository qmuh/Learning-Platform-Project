package backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import sharedobjects.Course;
import sharedobjects.Submission;

public class CourseTable extends Table
{
	public String tableName = "CourseTable";
	
	public CourseTable(Connection connectionToDB, String tableName)
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
			     "PROFID INT(8) NOT NULL, " + 
			     "NAME VARCHAR(50) NOT NULL, " + 
			     "ACTIVE BIT(1) NOT NULL," +
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

	/** Searches for a course by the courseID
	 * @param courseID
	 * @return
	 */
	public Course searchByCourseID(int courseID)
	{
		Course toFind = new Course();
		
		return toFind;
		
	}

	
	/** Searches for a list of courses from a specific professor
	 * @param professorId
	 * @return
	 */
	public Vector<Course> searchByProfId(int professorId)
	{
		Vector<Course> coursesFromProf = new Vector<Course>();
		
		return coursesFromProf;
		
	}
}
