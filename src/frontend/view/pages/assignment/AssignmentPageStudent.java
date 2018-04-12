package frontend.view.pages.assignment;

import javax.swing.JScrollPane;

import shared.objects.Course;
import shared.objects.Student;

final public class AssignmentPageStudent extends AssignmentPage
{
	private static final long serialVersionUID = 1L;

	public AssignmentPageStudent(Course course, Student student)
	{
		super(course, student);
	}

	@Override
	protected JScrollPane createAssignmentPanel()
	{
		JScrollPane scrollPane = new JScrollPane(itemDisplay);
		return scrollPane;
	}

}
