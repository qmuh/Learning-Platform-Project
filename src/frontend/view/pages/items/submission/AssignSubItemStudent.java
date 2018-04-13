package frontend.view.pages.items.submission;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import shared.objects.Assignment;
import shared.objects.Student;

/**
 * Provides a class that stores assignment submissions inside of a container on
 * a page from a student's perspective.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class AssignSubItemStudent extends AssignSubItem
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The student's submission that is displayed for submission confirmation.
	 */
	private StudentSubItem studentSubItem;

	public AssignSubItemStudent(Assignment assignment, Student student)
	{
		super(assignment);

		this.studentSubItem = new StudentSubItem(student);

		JPanel wrapper = new JPanel(new GridLayout(1, 1));
		wrapper.add(studentSubItem);
		this.add(wrapper);
	}

	@Override
	public void addSubmission(SubmitItem submitItem)
	{
		this.studentSubItem.addSubmissionItem(submitItem);
	}
}
