package frontend.view.pages;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import frontend.view.pages.items.AssignSubItemProfessor;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Student;

final public class SubmissionPageProfessor extends SubmissionPage
{


	private static final long serialVersionUID = 1L;


	public SubmissionPageProfessor(Course course)
	{
		super(course);
	}

	public void addAssignment(Assignment assignment, Vector<Student> classList)
	{
		AssignSubItemProfessor assignmentSubItemProfessor = new AssignSubItemProfessor(
				assignment, classList);
		this.assignmentMap.put(assignment.getId(), assignmentSubItemProfessor);
		this.itemDisplay.add(assignmentSubItemProfessor);
	}

//	@Override
//	public void addSubmission(SubmitItem submitItem)
//	{
//		Submission submission = submitItem.getSubmission();
//		AssignSubItemProfessor assignSubItemProfessor = this.assignmentMap
//				.get(submission.getAssign_id());
//
//		assignSubItemProfessor.addSubmission(submitItem);
//	}
}
