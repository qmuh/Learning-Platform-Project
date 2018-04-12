package backend.database.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import shared.objects.Grade;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class GradeTable extends Table<Grade>
{

	/**
	 * The constructor for the grade table
	 * 
	 * @param connectionToDB
	 *            Connection to the SQL database
	 * @param tableName
	 *            name of the database
	 */
	public GradeTable(Connection connectionToDB, String tableName)
	{
		super(connectionToDB, tableName);
	}

	@Override
	public void add(Grade toAdd)
	{
		String sql = "INSERT INTO " + tableName + " VALUES" + "(?,?,?,?,?)";
		try
		{

			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, toAdd.getId());
			preparedStatement.setInt(2, toAdd.getAssignID());
			preparedStatement.setInt(3, toAdd.getStudent_id());
			preparedStatement.setInt(4, toAdd.getCourseID());
			preparedStatement.setInt(5, toAdd.getGrade());
			preparedStatement.executeUpdate();

			System.out.println("Added Grade ");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" + "ID INT(8) NOT NULL, "
				+ "ASSIGNID INT(8) NOT NULL, " + "STUDENTID INT(8) NOT NULL, "
				+ "COURSEID INT(8) NOT NULL, "
				+ "ASSIGN_GRADE INT(3) NOT NULL, " + "PRIMARY KEY ( id ) )";

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
	 * Search for a grade by using assignment ID
	 * 
	 * @param assign_id
	 * @return
	 */
	public Vector<Grade> searchByAssignmentId(int assign_id)
	{
		Vector<Grade> gradeList = new Vector<Grade>();

		String sql = "SELECT * FROM " + tableName + " WHERE ASSIGNID= ?";
		ResultSet grade;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, assign_id);
			grade = preparedStatement.executeQuery();
			while (grade.next())
			{

				gradeList.add(new Grade(grade.getInt("ID"),
						grade.getInt("STUDENTID"), grade.getInt("ASSIGN_GRADE"),
						grade.getInt("ASSIGNID"), grade.getInt("COURSEID")));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return gradeList;

	}

	/**
	 * Searches the tables by using a course ID and student ID
	 * 
	 * @param courseID
	 *            Course to be searched
	 * @param studentID
	 *            Student to be searched
	 * @return vector of grade objects
	 */
	public Vector<Grade> studentGradesForCourse(int courseID, int studentID)
	{
		Vector<Grade> gradeList = new Vector<Grade>();

		String sql = "SELECT * FROM " + tableName
				+ " WHERE COURSEID= ? AND STUDENTID= ?";
		ResultSet grade;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, courseID);
			preparedStatement.setInt(2, studentID);
			grade = preparedStatement.executeQuery();
			while (grade.next())
			{

				gradeList.add(new Grade(grade.getInt("ID"),
						grade.getInt("STUDENTID"), grade.getInt("ASSIGN_GRADE"),
						grade.getInt("ASSIGNID"), grade.getInt("COURSEID")));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return gradeList;
	}
}
