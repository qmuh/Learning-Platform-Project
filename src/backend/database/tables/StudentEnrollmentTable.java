package backend.database.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import shared.objects.StudentEnrollment;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class StudentEnrollmentTable extends Table<StudentEnrollment>
{
	/** Constructor for the student enrollment table
	 * @param connectionToDB Connection to SQL
	 * @param tableName Name of the table
	 */
	public StudentEnrollmentTable(Connection connectionToDB, String tableName)
	{
		super(connectionToDB, tableName);
	}

	@Override
	public void add(StudentEnrollment toAdd)
	{
		String sql = "INSERT INTO " + tableName + " VALUES" + "(?,?,?)";
		try
		{

			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, toAdd.getId());
			preparedStatement.setInt(2, toAdd.getStudent_id());
			preparedStatement.setInt(3, toAdd.getCourse_id());
			preparedStatement.executeUpdate();

			System.out
					.println("Added Student Enrollment status where course is "
							+ toAdd.getCourse_id() + " student is "
							+ toAdd.getStudent_id());
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" + "ID INT(8) NOT NULL, "
				+ "STUDENTID INT(8) NOT NULL, " + "COURSEID INT(8) NOT NULL, "
				+ "PRIMARY KEY ( id ) )";

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
	 * Searches for the list of course IDs from a specific student
	 *
	 * @param studentID
	 * @return
	 */
	public Vector<Integer> getCourseIDs(int studentID)
	{
		Vector<Integer> myCourseIDs = new Vector<Integer>();
		String sql = "SELECT * FROM " + tableName + " WHERE STUDENTID= ? ";
		ResultSet courseInfo;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, studentID);
			courseInfo = preparedStatement.executeQuery();
			while (courseInfo.next())
			{

				myCourseIDs.add(courseInfo.getInt("COURSEID"));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return myCourseIDs;
	}

	/**
	 * Searches for the list of student IDs who are enrolled in a specific class
	 * Used by the professor
	 *
	 * @param courseID
	 * @return
	 */
	public Vector<Integer> getStudentsbyID(int courseID, int studentID)
	{
		Vector<Integer> listOfStudentIDs = new Vector<Integer>();
		String sql = "SELECT * FROM " + tableName
				+ " WHERE COURSEID= ? AND STUDENTID= ?";
		ResultSet studentsInfo;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, courseID);
			preparedStatement.setInt(2, studentID);
			studentsInfo = preparedStatement.executeQuery();
			if (studentsInfo.next())
			{

				listOfStudentIDs.add(studentsInfo.getInt("STUDENTID"));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return listOfStudentIDs;
	}

	/** Removes the student from a class they are enrolled in
	 * @param getmessageObject Used course and student information
	 */
	public void remove(StudentEnrollment getmessageObject)
	{
		String sql = "DELETE FROM " + tableName
				+ " WHERE COURSEID= ? AND STUDENTID= ?";
		ResultSet client;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, getmessageObject.getCourse_id());
			preparedStatement.setInt(2, getmessageObject.getStudent_id());
			preparedStatement.executeUpdate();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Searches for the list of student IDs who are enrolled in a specific class
	 * Used by the professor
	 *
	 * @param courseID
	 * @return
	 */
	public Vector<Integer> getAllEnrolledStudent(int courseID)
	{
		Vector<Integer> listOfStudentIDs = new Vector<Integer>();
		String sql = "SELECT * FROM " + tableName + " WHERE COURSEID= ?";
		ResultSet studentsInfo;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, courseID);
			studentsInfo = preparedStatement.executeQuery();
			while (studentsInfo.next())
			{
				listOfStudentIDs.add(studentsInfo.getInt("STUDENTID"));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return listOfStudentIDs;
	}

	/** Checks whether a student is enrolled in a specific class
	 * @param studentID The ID of the student
	 * @param courseID The OD of the course
	 * @return True is student is enrolled, false otherwise
	 */
	public boolean isStudentEnrolled(int studentID, int courseID)
	{
		String sql = "SELECT * FROM " + tableName
				+ " WHERE COURSEID= ? AND STUDENTID= ?";

		ResultSet studentsInfo;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, courseID);
			preparedStatement.setInt(2, studentID);
			studentsInfo = preparedStatement.executeQuery();

			if (studentsInfo.next())
			{
				return true;
			} else
			{
				return false;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}



}
