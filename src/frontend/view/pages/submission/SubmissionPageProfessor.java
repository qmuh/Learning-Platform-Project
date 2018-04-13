package frontend.view.pages.submission;

import java.util.Vector;

import frontend.view.pages.items.submission.AssignSubItemProfessor;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Professor;
import shared.objects.Student;

/**
 * The submission page from a professor's perspective.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
final public class SubmissionPageProfessor extends SubmissionPage
{

	private static final long serialVersionUID = 1L;

	public SubmissionPageProfessor(Course course, Professor professor)
	{
		super(course, professor);
	}

	public void addAssignment(Assignment assignment, Vector<Student> classList)
	{
		AssignSubItemProfessor assignmentSubItemProfessor = new AssignSubItemProfessor(
				assignment, classList);
		this.assignmentMap.put(assignment.getId(), assignmentSubItemProfessor);
		this.itemDisplay.add(assignmentSubItemProfessor);
	}
	
}
