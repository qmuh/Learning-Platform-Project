package backend.database.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import shared.objects.Course;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.01
 * @since April 6, 2018
 */
public class CourseTable extends Table<Course> implements Activable
{
	public String tableName = "CourseTable";

	public CourseTable(Connection connectionToDB, String tableName)
	{
		super(connectionToDB, tableName);
	}

	@Override
	public void add(Course toAdd)
	{
		String sql = "INSERT INTO " + tableName + " VALUES" + "(?,?,?,?)";
		;
		try
		{

			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, toAdd.getId());
			preparedStatement.setInt(2, toAdd.getProf_id());
			preparedStatement.setString(3, toAdd.getName());
			preparedStatement.setBoolean(4, toAdd.getActive());
			preparedStatement.executeUpdate();

			System.out.println("Added Course " + toAdd.getName()
					+ " made by professor " + toAdd.getProf_id());
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" + "ID INT(8) NOT NULL, "
				+ "PROFID INT(8) NOT NULL, " + "NAME VARCHAR(50) NOT NULL, "
				+ "ACTIVE BIT(1) NOT NULL," + "PRIMARY KEY ( id ) )";

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
	 * Searches for a course by the courseID
	 * 
	 * @param courseID
	 * @return
	 */
	public Course searchByCourseID(int courseID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE ID= ? ";
		ResultSet course;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, courseID);
			course = preparedStatement.executeQuery();
			if (course.next())
			{

				return new Course(course.getInt("ID"), course.getInt("PROFID"),
						course.getString("NAME"), course.getBoolean("ACTIVE"));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Searches for a list of courses from a specific professor
	 * 
	 * @param professorId
	 * @return
	 */
	public Vector<Course> searchByProfId(int professorId)
	{
		Vector<Course> coursesFromProf = new Vector<Course>();

		String sql = "SELECT * FROM " + tableName + " WHERE PROFID= ? ";
		ResultSet course;
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, professorId);
			course = preparedStatement.executeQuery();
			while (course.next())
			{

				coursesFromProf.add(new Course(course.getInt("ID"),
						course.getInt("PROFID"), course.getString("NAME"),
						course.getBoolean("ACTIVE")));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return coursesFromProf;

	}

	@Override
	public void setActive(int courseID, boolean isActive)
	{
		String sql = "UPDATE " + tableName + " SET ACTIVE="
				+ ((Boolean) isActive).toString().toUpperCase() + " WHERE ID=?";
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, courseID);
			preparedStatement.executeUpdate();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
