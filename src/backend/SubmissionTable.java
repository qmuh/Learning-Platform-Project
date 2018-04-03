package backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import sharedobjects.Assignment;
import sharedobjects.Submission;

public class SubmissionTable extends Table
{

	public SubmissionTable(Connection connectionToDB, String tableName)
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
			     "PATH VARCHAR(100) NOT NULL, " + 
			     "TITLE VARCHAR(50) NOT NULL, " + 
			     "SUBMISSION_GRADE INT(3) NOT NULL," +
			     "COMMENTS VARCHAR(140) NOT NULL, " + 
			     "TIMESTAMP VARCHAR(16) NOT NULL, " + 
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

	/** Used by professor to look at submission by a specific student
	 * @param studentID
	 * @return
	 */
	public Vector<Submission> searchByStudentID(int studentID)
	{
		Vector<Submission> studentSubmissons = new Vector<Submission>();
		
		return studentSubmissons;
		
	}

	/** Used by professor to see all submissions for a specific
	 *  assignment
	 * @param assignID
	 * @return
	 */
	public Vector<Submission> searchByAssignID(int assignID)
	{
		Vector<Submission> studentSubmissons = new Vector<Submission>();
		
		return studentSubmissons;
		
	}

}
