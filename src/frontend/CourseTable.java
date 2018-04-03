package frontend;

import java.util.Vector;

import sharedobjects.Course;
import sharedobjects.Submission;

public class CourseTable extends Table
{

	@Override
	public void add(Object toAdd)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTable()
	{
		// TODO Auto-generated method stub
		
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
