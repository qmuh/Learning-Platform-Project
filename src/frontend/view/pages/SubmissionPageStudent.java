package frontend.view.pages;

import java.util.Vector;

import frontend.view.pages.items.submission.AssignSubItem;
import frontend.view.pages.items.submission.AssignSubItemStudent;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Student;

public class SubmissionPageStudent extends SubmissionPage
{

	private static final long serialVersionUID = 1L;

	private Student student;

	public SubmissionPageStudent(Course course, Student student)
	{
		super(course);
		this.student = student;
	}

	public void addAssignment(Assignment assignment)
	{
		AssignSubItemStudent assignmentItem = new AssignSubItemStudent(
				assignment, student);
		this.assignmentMap.put(assignment.getId(), assignmentItem);
		this.itemDisplay.add(assignmentItem);
	}
}
