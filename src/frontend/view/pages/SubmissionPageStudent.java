package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.items.submission.AssignSubItem;
import frontend.view.pages.items.submission.AssignSubItemStudent;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Student;

final public class SubmissionPageStudent extends SubmissionPage
{

	private static final long serialVersionUID = 1L;

	private JTextField uploadField;

	private Student student;

	private WButton browseButton;

	private JTextArea commentsArea;

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
	}

}
