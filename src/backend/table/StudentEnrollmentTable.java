package backend.table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import sharedobjects.Assignment;
import sharedobjects.StudentEnrollment;
import sharedobjects.Submission;

public class StudentEnrollmentTable extends Table<StudentEnrollment>
{	
	public StudentEnrollmentTable(Connection connectionToDB, String tableName)
	{
		super(connectionToDB, tableName);
	}

	@Override
	public void add(StudentEnrollment toAdd)
	{
		String sql = "INSERT INTO " + tableName +
				" VALUES" + "(?,?,?)";
		try{
			
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1,toAdd.getId());
			preparedStatement.setInt(2, toAdd.getCourse_id());
			preparedStatement.setInt(3, toAdd.getStudent_id());
			preparedStatement.executeUpdate();
			
			System.out.println("Added Student Enrollment status ");
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
		String sql = "SELECT * FROM " + tableName + " WHERE STUDENTID= ? ";
		ResultSet courseInfo;
		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, studentID);
			courseInfo = preparedStatement.executeQuery();
			if(courseInfo.next())
			{
				
					myCourseIDs.add(courseInfo.getInt("COURSEID"));	
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
	
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
		String sql = "SELECT * FROM " + tableName + " WHERE COURSEID= ? ";
		ResultSet studentsInfo;
		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, courseID);
			studentsInfo = preparedStatement.executeQuery();
			if(studentsInfo.next())
			{
				
				listOfStudentIDs.add(studentsInfo.getInt("STUDENTID"));	
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
	
		return listOfStudentIDs;
	}

}
