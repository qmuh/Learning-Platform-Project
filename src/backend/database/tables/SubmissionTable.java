package backend.database.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import shared.objects.Submission;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class SubmissionTable extends Table<Submission>
{

	public SubmissionTable(Connection connectionToDB, String tableName)
	{
		super(connectionToDB, tableName);
	}

	@Override
	public void add(Submission toAdd)
	{
		String sql = "INSERT INTO " + tableName +
				" VALUES" + "(?,?,?,?,?,?,?,?)";
		try{
			
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1,toAdd.getId());
			preparedStatement.setInt(2, toAdd.getAssign_id());
			preparedStatement.setInt(3, toAdd.getStudent_id());
			preparedStatement.setString(4, toAdd.getPath());
			preparedStatement.setString(5, toAdd.getTitle());
			preparedStatement.setInt(6, toAdd.getGrade());
			preparedStatement.setString(7, toAdd.getComment());
			preparedStatement.setString(8, toAdd.getTimestamp());
			preparedStatement.executeUpdate();
			
			System.out.println("Added Grade ");
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
			     "ASSIGNID INT(8) NOT NULL, " + 
			     "STUDENTID INT(8) NOT NULL, " + 
			     "PATH VARCHAR(100) NOT NULL, " + 
			     "TITLE VARCHAR(50) NOT NULL, " + 
			     "SUBMISSION_GRADE INT(3) NOT NULL," +
			     "COMMENT VARCHAR(140) NOT NULL, " + 
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
		String sql = "SELECT * FROM " + tableName + " WHERE STUDENTID= ? ";
		ResultSet submission;
		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, studentID);
			submission = preparedStatement.executeQuery();
			if(submission.next())
			{
				
					studentSubmissons.add(new Submission(submission.getInt("ID"),
								submission.getInt("ASSIGNID"), 
								submission.getInt("STUDENTID"),
								submission.getString("PATH"),
								submission.getInt("SUBMISSION_GRADE"),
								submission.getString("COMMENT"),
								submission.getString("TITLE"),
								submission.getString("TIMESTAMP")));	
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return studentSubmissons;
	
		
	}

	/** Used by professor to see all submissions for a specific
	 *  assignment
	 * @param assignID
	 * @return
	 */
	public Vector<Submission> searchByAssignID(int assignID)
	{
		Vector<Submission> assignSubmissons = new Vector<Submission>();
		String sql = "SELECT * FROM " + tableName + " WHERE ASSIGNID= ? ";
		ResultSet submission;
		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, assignID);
			submission = preparedStatement.executeQuery();
			if(submission.next())
			{
				
					assignSubmissons.add(new Submission(submission.getInt("ID"),
								submission.getInt("ASSIGNID"), 
								submission.getInt("STUDENTID"),
								submission.getString("PATH"),
								submission.getInt("SUBMISSION_GRADE"),
								submission.getString("COMMENT"),
								submission.getString("TITLE"),
								submission.getString("TIMESTAMP")));	
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return assignSubmissons;
		
	}

}
