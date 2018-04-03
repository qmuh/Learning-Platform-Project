package frontend;

import java.util.Vector;

import sharedobjects.Assignment;

public class StudentEnrollmentTable extends Table
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
