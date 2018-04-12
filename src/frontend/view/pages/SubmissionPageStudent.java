package frontend.view.pages;

import java.util.HashMap;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.AssignSubItem;
import frontend.view.pages.items.AssignSubItemProfessor;
import frontend.view.pages.items.AssignSubItemStudent;
import frontend.view.pages.items.SubmitItem;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Student;

final public class SubmissionPageStudent extends SubmissionPage
{

	private static final long serialVersionUID = 1L;

	private Student student;

	public SubmissionPageStudent(Course course, Student student)
	{
		super(course);
		this.student = student;
	}

	/**
	 * Adds a new assignment submission area into the assignment map for the
	 * student for later submission adding.
	 *
	 * @param assignment
	 */

	public BoxList<AssignSubItem> getBoxList()
	{
		return itemDisplay;
	}

	public void addAssignment(Assignment assignment)
	{
		AssignSubItemStudent assignmentItemStudent = new AssignSubItemStudent(
				assignment, student);
		this.assignmentMap.put(assignment.getId(), assignmentItemStudent);
		this.itemDisplay.add(assignmentItemStudent);
		this.itemDisplay.repaint();
		this.itemDisplay.revalidate();
		this.itemDisplay.repaint();
	}


}
