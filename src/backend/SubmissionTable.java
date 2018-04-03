package backend;

import java.util.Vector;

import sharedobjects.Assignment;
import sharedobjects.Submission;

public class SubmissionTable extends Table
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
