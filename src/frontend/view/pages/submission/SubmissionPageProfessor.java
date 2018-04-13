package frontend.view.pages.submission;

import java.util.Vector;

import frontend.view.pages.items.submission.AssignSubItemProfessor;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Professor;
import shared.objects.Student;

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
	
	
	// @Override
	// public void addSubmission(SubmitItem submitItem)
	// {
	// Submission submission = submitItem.getSubmission();
	// AssignSubItemProfessor assignSubItemProfessor = this.assignmentMap
	// .get(submission.getAssign_id());
	//
	// assignSubItemProfessor.addSubmission(submitItem);
	// }
}
