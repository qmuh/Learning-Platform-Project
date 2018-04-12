package frontend.view.pages;

import java.util.Vector;

import frontend.view.pages.items.AssignSubItem;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Student;

public class SubmissionPageProfessor extends SubmissionPage
{

	private static final long serialVersionUID = 1L;

	public SubmissionPageProfessor(Course course)
	{
		super(course);
	}

	public void addAssignment(Assignment assignment, Vector<Student> classList)
	{
		AssignSubItem assignmentItem = new AssignSubItem(assignment, classList);
		this.assignmentMap.put(assignment.getId(), assignmentItem);
		this.itemDisplay.add(assignmentItem);
	}
	
}
